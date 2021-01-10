
public class Main {

    public static void main(String[] args){

        Database db = new Database();

        db.getCost(10, "Faktura.txt");

        db.closeConnection();
    }
}