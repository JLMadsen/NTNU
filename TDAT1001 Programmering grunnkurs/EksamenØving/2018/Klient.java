import static javax.swing.JOptionPane.*;

class Klient{
    public static void main(String[] args) {

        Utstilling a = new Utstilling("KunstErKult123");

 

        Navn egil = new Navn("Egil", "todd");
        Navn arnulf = new Navn("Arnulf", "bertil");

        Kunstverk x = new Kunstverk("a elg", "maleri", 4, 1, egil);
        Kunstverk y = new Kunstverk("a elg", "maleri", 4, 2, egil);
        Kunstverk z = new Kunstverk("b katt", "maleri", 5, 5, arnulf);
        Kunstverk g = new Kunstverk("c hund", "maleri", 6, 4, arnulf);
        Kunstverk h = new Kunstverk("d okse", "maleri", 7, 1, egil);
        Kunstverk j = new Kunstverk("e sau", "maleri", 8, 5, egil);
        Kunstverk k = new Kunstverk("f ku", "maleri", 9, 3, arnulf);

        Kunstverk[] l = {x, y, z, g, h, j, k};
        a.testing(l);
        
        while(true){
            String read1 = showInputDialog("Hva vil du gjøre?\n1) List alle Kunstverk.\n2) Legg til nytt kunstverk. \n3) Avslutt.");
            int choice = Integer.parseInt(read1);
            switch(choice){
                case 1:

                String b = a.toString();
                System.out.println(b);

                System.out.println(" x = x : "+ x.equals(x));
                System.out.println(" x = z : "+ x.equals(z));

                System.out.println(a.getKunstverk(egil));

                break;
                case 2:
                
                String read2 = showInputDialog("Fyll inn data: Tittesl");
                String read3 = showInputDialog("Fyll inn data: Type");
                String read4 = showInputDialog("Fyll inn data: Antall Kopier");
                int kopier = Integer.parseInt(read4);
                String read5 = showInputDialog("Fyll inn data: Kopi nummer");
                int kopiNummer = Integer.parseInt(read5);
                String read6 = showInputDialog("Fyll inn data: Kunstner");
                String[] n = read6.split(" ");
                Navn m = new Navn(n[0], n[1]);
                
                a.addKunstverk(read2, read3, kopier, kopiNummer, m);

                break;
                case 3:
                System.out.println("Avslutter.");
                return;
                default:
                System.out.println("Feil input, avslutter.");
                return;
            }
        }
    }
}