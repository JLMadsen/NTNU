import java.sql.*;

public class Database {

    private Connection kobling;
    private PreparedStatement setning;
    private ResultSet resultat;

    public Database(String url){
        try {
            kobling = DriverManager.getConnection(url);
            kobling.setAutoCommit(false);
            System.out.println("Kobling opprettet");
        }catch(Exception e){
            System.out.println("Feil i kontruktør");
            System.out.println(e.getMessage());;
        }
    }

    public void lukkForbindelse(){
        try{
            kobling.close();
        }catch (Exception e){
            System.out.println("Feil i lukkForbindelse");
        }
    }

    public boolean checkIfExists(String isbn){
        try{
            setning = kobling.prepareStatement("SELECT * FROM boktittel");
            ResultSet idk = setning.executeQuery();
            while(idk.next()){
                if(isbn.equals(idk.getString("isbn"))){
                    return true;
                }
            }
            return false;
        }catch (SQLException e){
            System.out.println("Feil i checkIfExists");
        }
        return false;
    }

    public boolean regNyBok(Bok bok){
        try{
            setning = kobling.prepareStatement("insert into boktittel(isbn, forfatter, tittel) values(?, ?, ?);");
            setning.setString(1, bok.getIsbn());
            setning.setString(2, bok.getForfatter());
            setning.setString(3, bok.getTittel());

            if(setning.execute()){

                System.out.println("Ny bok registrert");
                setning = kobling.prepareStatement("insert into eksemplar(isbn, eks_nr) values (?, 1);");
                setning.setString(1, bok.getIsbn());

                if(setning.execute()){

                    System.out.println("Eksemplar registrert");

                } else {

                    System.out.println("Eksemplar ikke registrert");
                    kobling.rollback();
                }
            } else {
                System.out.println("Bok ikke registrert");
            }
            kobling.commit();
            return true;
        }catch(Exception e){
            System.out.println("Feil i regNyBok");
            try{
                kobling.rollback();
            }catch(Exception f){
                System.out.println("Feil i rollback");
            }
        }
        return false;
    }

    public int regNyttEksemplar(String isbn){
        int antall = 0;
        try{
            int highest = 0;
            PreparedStatement read = kobling.prepareStatement("SELECT eks_nr FROM eksemplar WHERE isbn = ?");
            read.setString(1, isbn);
            ResultSet resultat = read.executeQuery();

            int counter = 1;
            while(resultat.next()){
                if(resultat.getInt(counter) > highest){
                    highest = resultat.getInt(counter);
                }
            }

            antall = highest;
            antall++;

            PreparedStatement insert = kobling.prepareStatement("insert into eksemplar(isbn, eks_nr) values (?, ?)");
            insert.setString(1, isbn);
            insert.setInt(2, antall);
            insert.execute();
            kobling.commit();

        }catch(Exception e){
            System.out.println("Feil i regNyttEksemplar");
            System.out.println(e.getMessage());
            try{
                kobling.rollback();
            }catch(Exception f){
                System.out.println("Feil i rollback");
            }
        }
        return antall;
    }

    public boolean utlaan(String isbn, String navn, int eksNr){
        try{
            PreparedStatement status = kobling.prepareStatement("SELECT laant_av FROM eksemplar WHERE isbn = ? AND eks_nr = ?");
            status.setString(1, isbn);
            status.setInt(2, eksNr);
            ResultSet idk = status.executeQuery();


            if(idk.next()){
                return false;
            }
            PreparedStatement laan = kobling.prepareStatement("UPDATE eksemplar SET laant_av = ? WHERE isbn = ? AND eks_nr = ?");
            laan.setString(1, navn);
            laan.setString(2, isbn);
            laan.setInt(3, eksNr);
            if(laan.execute()){
                kobling.commit();
                return true;
            } else {
                kobling.rollback();
                return false;
            }

        }catch (SQLException e){
            System.out.println("Feil i utlån");
            System.out.println(e.getMessage());
            try{
                kobling.rollback();
            }catch(Exception f){
                System.out.println("Feil i rollback");
            }
        }
        return false;
    }
}
