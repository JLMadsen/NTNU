import java.time.LocalDate;
import static javax.swing.JOptionPane.*;
import java.util.ArrayList;

class Klient{
    public static void main(String[] args) {
        Medlemsarkiv ma = new Medlemsarkiv();
        ArrayList<Integer> liste = new ArrayList<Integer>();
        
        Personalia persA = new Personalia("Per", "Banan", "123@gmail.com", "1234");
        Personalia persB = new Personalia("Bob", "Kaare", "1234@gmail.com", "12345");

        LocalDate date1 = LocalDate.of(2018, 1, 10);
        LocalDate date2 = LocalDate.of(2019, 2, 10);
        LocalDate date3 = LocalDate.of(2019, 4, 10);

        liste.add(liste.size(), ma.nyMedlem(persA, date2));
        liste.add(liste.size(), ma.nyMedlem(persB, date1));
        {
            int g = 0;
        }
        while(true){
            int poeng;
            int medlNr;
            String passord;
            LocalDate idk2;
            String read1 = showInputDialog("Hva vil du gjøre?\n1) Se alle medlemmer.\n2) Se poeng til medlem.\n3) Registrer poeng.\n4) Registrer nytt medlem.\n5) Sjekk medlemmer. (Oppgrader hvis mulig)\n6) Avslutt.");
            switch(Integer.parseInt(read1)){
                case 1:
                System.out.println(ma.toString());
                break;
                case 2:
                medlNr = -1;
                medlNr = Integer.parseInt(showInputDialog("Hvilket medlem vil du sjekke? (MedNr)"));
                passord = null;
                passord = showInputDialog("Hva er passordet?");
                poeng = 0;
                poeng = ma.getPoeng(medlNr, passord);
                if(poeng < 0){
                    System.out.println("Ugyldig medlemNr eller passord.");
                    break;
                }
                System.out.println(" Poeng for medlem "+ medlNr +": "+ poeng);
                break;
                case 3:
                medlNr = 0;
                medlNr = Integer.parseInt(showInputDialog("Hvilket medlem skal du registrere for? (MedNr)"));
                poeng = 0;
                poeng = Integer.parseInt(showInputDialog("Hvor mange poeng fikk medlemmet?"));
                if(ma.registrerPoeng(medlNr, poeng)){
                    System.out.println("Poeng registrert.");
                } else {
                    System.out.println("Fant ikke medlem.");
                }
                break;
                case 4:
                String fornavn = "";
                String etternavn = "";
                String[] navn = showInputDialog("Navn:").split(" ");
                if(navn.length == 1){
                    System.out.println("Oppgi fornavn og etternavn.");
                    navn = showInputDialog("Navn:").split(" ");                        
                }
                if(navn.length > 2){
                    fornavn = navn[0];
                    for(int j=1; j<navn.length; j++){
                        etternavn += navn[j];
                    }
                } else {
                    fornavn = navn[0];
                    etternavn = navn[1];
                }
                String epost = showInputDialog("Epost:");
                passord = null;
                passord = showInputDialog("Passord: (case teller ikke)");
                Personalia idk = new Personalia(fornavn, etternavn, epost, passord);
                int choice = showConfirmDialog(null, "Vil du bruke dags dato?", "test", YES_NO_OPTION);
                idk2 = null;
                if(choice == YES_OPTION){
                    idk2 = LocalDate.now();
                } else {
                    idk2 = getDato();
                }
                int temp = ma.nyMedlem(idk, idk2);
                liste.add(liste.size(), temp);
                break;
                case 5:
                int choice2 = showConfirmDialog(null, "Vil du bruke dags dato?", "test", YES_NO_OPTION);
                idk2 = null;
                if(choice2 == YES_OPTION){
                    idk2 = LocalDate.now();
                } else {
                    idk2 = getDato();
                }
                ma.sjekkMedlemmer(idk2);
                System.out.println("Medlemmer sjekket.");
                break;
                case 6:
                System.out.println("Avslutter");
                return;
                default:
                System.out.println("Feil input.");
                break;

            }
        }

    }
    public static LocalDate getDato(){
        int year = Integer.parseInt(showInputDialog("Hvilet år?"));
        int month = Integer.parseInt(showInputDialog("Hvilken måned?"));
        int day = Integer.parseInt(showInputDialog("Hvilken dag?"));
        LocalDate idk = LocalDate.of(year, month, day);
        return idk;
    }
}