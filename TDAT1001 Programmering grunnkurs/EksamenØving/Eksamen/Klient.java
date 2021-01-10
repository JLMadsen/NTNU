import static javax.swing.JOptionPane.*;
class Klient implements java.io.Serializable{
    public static void main(String[] args){
        String filnavn = "soppregister.ser";

        Soppart a = new Soppart("Rød", "Den er rød.", true);
        Soppart b = new Soppart("Blå", "Den er blå.", false);
        Soppart c = new Soppart("Gul", "Den er gul.", true);

        Soppart[] liste = {a, b, c};
        
        Soppregister register = new Soppregister(liste);

        String[] muligheter = {"List alle", "List matsopper", "Legg til ny", "Søk", "Avslutt"};
        final int LIST_ALLE = 0;
        final int LIST_MATSOPPER = 1;
        final int REG_SOPP = 2;
        final int SOK = 3;
        final int AVSLUTT = 4;

        int valg = showOptionDialog(null, "Velg", "Eksamen des 2018",  YES_NO_OPTION,              INFORMATION_MESSAGE, null, muligheter, muligheter[0]);
                while (valg != AVSLUTT){
                    switch (valg){
                        case LIST_ALLE:
                          System.out.println(register.getAlle());
                        break;

                        case LIST_MATSOPPER:
                        Soppart[] spis = register.getSpiseligSopp();
                        String spiselig = "Liste over spiselig sopp:\n";

                        for(int i=0; i<spis.length; i++){
                            spiselig += spis[i].toString() +"\n";
                        }
                        System.out.println(spiselig);

                        break;

                        case REG_SOPP: // Oppgave 4c
                        String navn = showInputDialog("Hva heter soppen?");
                        String beskrivelse = showInputDialog("Beskrivelse av soppen:");
                        int valg1 = showConfirmDialog(null, "Er soppen giftig?", "Sopp", YES_NO_OPTION);
                        boolean giftig = false;
                        if(valg1 == 1){ // antar at YES_OPTION returnerer 1
                            giftig = true;
                        }
                        Soppart s = new Soppart(navn, beskrivelse, giftig);
                        if(register.regNyArt(s)){
                            System.out.println("Ny art registrert!");
                        } else {
                            System.out.println("Registrering avbrutt, art finnes allerede.");
                        }
                        break;

                        case SOK: // Oppgave 4c
                        
                        System.out.println( register.search( showInputDialog("Hva vil du søke etter?") ) );
                        break;

                        default: break;
                    }
                    valg = showOptionDialog(null, "Velg", "Eksamen des 2018", YES_NO_OPTION, INFORMATION_MESSAGE, null, muligheter, muligheter[0]);
                }
        //skrivRegTilfil(filnavn,register);
    }

}/*
 //4a
public Object lesFraFil(String filnavn){
    try{
        FileInputSteam fis = new FileInputStream(filnavn);
        ObjectInputSteam ois = new ObjectInputSteam(fis);
        
        Object o = ois.readObject();
        ois.close();
        return o;
    }
    catch(Exception e){
        System.out.println("Feil i metoden les fra fil")
    }
    // bruker en generell Exception til å fange feil, kunne ha laget flere med IOException og FileNotFoundException.
}*/
