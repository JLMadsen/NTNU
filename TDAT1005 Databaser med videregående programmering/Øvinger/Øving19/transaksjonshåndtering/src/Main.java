public class Main {

    public static void main(String[] args){

        String url = "jdbc:mysql://mysql.stud.idi.ntnu.no:3306/jakoblm?user=jakoblm&password=YbBHG2Nc&useSSL=false";
        Database db = new Database(url);

        Bok nyBok = new Bok("jeg_vet_ikke_hva_isbn_er", "En god bok", "en kul forfatter");

        if(db.checkIfExists(nyBok.getIsbn())){

            System.out.println("Bok finnes allerede, lager nytt eksemplar");
            int antbøker = db.regNyttEksemplar(nyBok.getIsbn());
            System.out.println("Antall '"+ nyBok.getTittel() +"': "+ antbøker);

        } else {

            db.regNyBok(nyBok);
        }

        if(db.utlaan(nyBok.getIsbn(), "Arnulf", 4)){
            System.out.println("Bok lånt");
        } else {
            System.out.println("bok ikke lånt");
        }

        db.lukkForbindelse();
    }
}
