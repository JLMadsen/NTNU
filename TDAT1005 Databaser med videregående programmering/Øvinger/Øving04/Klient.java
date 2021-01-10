import static javax.swing.JOptionPane.*;

class Klient { // 20190319
    public static void main(String[] args) {
        KonferanseSenter ks = new KonferanseSenter();

        while(true){
            String read1 = showInputDialog("Hva vil du gjøre?\n1) Reserver rom.\n2) Se rom med bestillinger.\n3) hent rom info med indeks eller rom nummer \n4) Avslutt.");
            switch(Integer.parseInt(read1)){
                case 1:
                Kunde a = null;
                Tidspunkt b = null;
                Tidspunkt c = null;
                int n = 0;
                try{
                    a = new Kunde(showInputDialog("Hvilket navn skal rommet reserveres på?"), showInputDialog("Telefon nummer:"));
                    b = new Tidspunkt(Long.parseLong(showInputDialog("Når skal rommet reserveres fra? (aaaammddttmm)"),10));
                    c = new Tidspunkt(Long.parseLong(showInputDialog("Når skal rommet reserveres til? (aaaammddttmm)"),10));
                    n = Integer.parseInt(showInputDialog("Hvor mange personer kommer?"));
                } catch(Exception e){
                    System.out.println("Feil input.");
                    break;
                }
                if(ks.bookRoom(b, c, n, a)){
                    showMessageDialog(null, "Bord bestilt");
                } else {
                    showMessageDialog(null, "Bord ikke bestilt. Ingen ledige rom eller allerede reservert.");
                }
                break;
                case 2:
                System.out.println(ks.toString());
                break;
                case 3:
                String read2 = showInputDialog("Det er "+ ks.getRooms() +" rom, vil hente med:\n1) indeks. \n2) rom nummer.\n)3 Avslutt");
                switch(Integer.parseInt(read2)){
                    case 1:
                    System.out.println(ks.getRoomI(Integer.parseInt(showInputDialog("Indeks:"))).toString());
                    break;
                    case 2:
                    System.out.println(ks.getRoomN(Integer.parseInt(showInputDialog("Rom nummer:"))).toString());
                    break;
                    case 3:
                    break;
                    default:
                    System.out.println("Feil input.");
                    break;
                }
                break;
                case 4:
                System.out.println("Avslutter.");
                return;
                default:
                System.out.println("Feil input.");
                break;
            }
        }
/*
        Kunde a = new Kunde("Arnulf", "911");

        Tidspunkt q = new Tidspunkt(200903191500L); // 1500
        Tidspunkt e = new Tidspunkt(200903191600L); // 1600
        Tidspunkt w = new Tidspunkt(200903191700L); // 1700

        System.out.println(ks.getRooms());
        System.out.println(ks.getRoomI(0).toString());
        System.out.println(ks.getRoomN(3).toString());

        if(ks.bookRoom(q, w, 7, a)){
            System.out.println("Rom booket.");
        } else {
            System.out.println("Rom ikke booket.");
        }
        if(ks.bookRoom(q, e, 7, a)){
            System.out.println("Rom booket.");
        } else {
            System.out.println("Rom ikke booket.");
        }

        System.out.println(ks.toString());

        if(ks.bookRoom(q, w, 4, a)){
            System.out.println("Rom booket.");
        } else {
            System.out.println("Rom ikke booket.");
        }
        if(ks.bookRoom(q, w, 7, a)){
            System.out.println("Rom booket.");
        } else {
            System.out.println("Rom ikke booket.");
        }

        System.out.println(ks.toString());
*/

    }
}