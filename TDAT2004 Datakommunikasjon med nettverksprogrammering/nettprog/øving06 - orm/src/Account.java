import javax.persistence.*;

@Entity
class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String name;
    private double balance;

    @Version
    private Integer version;

    public Account(String name, double balance){
        this.name = name;
        this.balance = balance;
    };

    public Account(String name){
        this.id = id;
        this.name = name;
    };

    public Account(){}

    public int getId(){return this.id;}
    public int setId(int n){this.id=n; return this.getId();}

    public String getName(){return this.name;}
    public Account setName(String n){this.name=n; return this;}

    public double getBalance(){return this.balance;}
    public Account setBalance(double n){this.balance=n; return this;}

    public double withdraw(double n){this.balance-=n; return this.getBalance();}
    public double deposit(double n){this.balance+=n; return this.getBalance();}

    @Override
    public String toString() {
        return "Account " + this.id + ", name = " + this.name + ", balance = " + this.balance;
    }
}