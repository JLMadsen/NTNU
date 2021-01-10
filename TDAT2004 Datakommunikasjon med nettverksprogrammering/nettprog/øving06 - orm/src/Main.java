import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.persistence.*;

class Main {
    private static final String PERSISTENCE_UNIT_NAME = "accounts";
    private static EntityManagerFactory factory;

    private static EntityManager getEm() {
        return factory.createEntityManager();
    }

    private static void printAccounts(List<Account> as) {
        for(Account a : as) {
            System.out.println("- " + a.toString());
        }
    }

    private static List<Account> getAllAccount() {
        EntityManager em = getEm();
        Query query = em.createQuery("SELECT a FROM Account a");
        List<Account> accounts = query.getResultList();
        em.close();
        return accounts;
    }

    private static void printAllDb() {
        printAccounts(getAllAccount());
    }

    private static void PrintAbove500() {
        EntityManager em = getEm();
        Query query = em.createQuery("SELECT a FROM Account a WHERE a.balance > 500 ");
        List<Account> accounts500 = query.getResultList();
        printAccounts(accounts500);
        em.close();
    }

    private static void renameAccount(int id, String name) {
        EntityManager em = getEm();
        Query query = em.createQuery("SELECT a FROM Account a");
        List<Account> allAccounts = query.getResultList();
        Account acc = allAccounts.get(id);

        acc.setName(name);

        em.getTransaction().begin();
        em.merge(acc);
        em.getTransaction().commit();
        em.close();
    }

    private static Lock lock = new ReentrantLock(true);
    private static void transaction(int fromid, int toid, double amount) {
        EntityManager em = getEm();
        try {
            List<Account> accounts = getAllAccount();
            Account fromAcc = accounts.get(fromid);
            Account toAcc = accounts.get(toid);

            fromAcc.withdraw(amount);
            toAcc.deposit(amount);

            // both threads wait here after reading and changing data
            Thread.sleep(1000);

            lock.lock();
            em.getTransaction().begin();

            em.merge(fromAcc);
            em.merge(toAcc);

            em.getTransaction().commit();
        }
        catch (OptimisticLockException e) {
            transaction(fromid, toid, amount);
        }
        catch (Exception ignored) {}
        finally {
            lock.unlock();
            em.close();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("start program");
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager em = getEm();

            System.out.println("\nTask 2\n");

            em.getTransaction().begin();
            em.createQuery("DELETE FROM Account a").executeUpdate();

            System.out.println("insert accounts");
            em.persist(new Account("Martorb", 420));
            em.persist(new Account("Madzen", 666));
            em.persist(new Account("bigboi6969", 69));
            em.getTransaction().commit();

            System.out.println("\nfetch all accounts from db");
            printAllDb();

            System.out.println("\nfetch accounts from db where balance is above 500");
            PrintAbove500();

            System.out.println("\nrename account 1 and store in db");
            renameAccount(0, "Sullizar");
            printAllDb();

            System.out.println("\nTask 3 & task 4\n");

            System.out.println("transactions without optimistic lock:");
            System.out.println("pay 400 from account 2 to 1");
            System.out.println("pay 30 from account 3 to 2");

            double oldsum = 0.0;
            for (Account a : getAllAccount()) {
                oldsum += a.getBalance();
            }

            Thread t1 = new Thread(() -> {
                transaction(1, 0, 400);
            });
            t1.start();

            Thread t2 = new Thread(() -> {
                transaction(2, 1, 30);
            });
            t2.start();

            t1.join();
            t2.join();

            printAllDb();

            double newsum = 0.0;
            for (Account a : getAllAccount()) {
                newsum += a.getBalance();
            }

            System.out.println("total money in database changed by: " + Math.abs(oldsum - newsum));

        } catch (Exception ignored) {}
    }
}