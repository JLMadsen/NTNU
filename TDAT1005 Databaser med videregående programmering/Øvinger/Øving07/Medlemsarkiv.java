import java.time.LocalDate;
import java.util.Random;

class Medlemsarkiv{
    private int antall = 0;

    BonusMedlem[] liste = new BonusMedlem[10];

    public Medlemsarkiv(){}

    public int getPoeng(int medlemNr, String passord){
        int indeks = index(medlemNr);
        if(indeks < 0){
            return -1;
        }
        if(liste[indeks].okPassord(passord)){
            return liste[indeks].getPoeng();
        }
        return -1;
    }
    private int index(int medlemNr) {
        for(int i=0; i<antall; i++){
            if(liste[i].getMedlnr() == medlemNr){
                return i;
            }
        }
        return -1;
    }
    public boolean registrerPoeng(int medlemNr, int poeng){
        int indeks = index(medlemNr);
        if(indeks == -1) return false;
        liste[indeks].registrerPoeng(poeng);
        return true;
    }
    public int nyMedlem(Personalia pers, LocalDate date){
        if(liste.length == getAntall()){
            utvid();
        }
        int medlemNr = finnLedigNr();
        if(medlemNr == 420){
            System.out.println("Feil med medlems nummer finneren");
        }
        BasicMedlem idk = new BasicMedlem(medlemNr, pers, date);
        liste[antall] = idk;
        antall++;
        return medlemNr;
    }
    public int getAntall(){
        antall = 0;
        for(int i=0; i<liste.length; i++){
            if(liste[i]!=null){
                antall++;
            }
        }
        return antall;
    }
    public void utvid(){
        BonusMedlem[] temp = new BonusMedlem[liste.length+5];
        for(int i=0; i<antall; i++){
            temp[i] = liste[i];
        }
        liste = temp;
    }
    public String toString(){
        String re = "";
        for(int i=0; i<antall; i++){
            re += liste[i].toString() +"\n";
        }
        return re;
    }
    private int finnLedigNr(){
        Random rand = new Random();
        while(true){
            int n = rand.nextInt(500)+1;
            if(index(n) == -1){
                return n;
            }
        }
        
    }
    public void sjekkMedlemmer(LocalDate date){
        for(int i=0; i<antall; i++){
            Object obj = liste[i];
            if(obj instanceof BasicMedlem){
                BasicMedlem basic = (BasicMedlem) obj;
                int poeng = basic.finnKvalPoeng(date);
                if(poeng > 25000 && poeng < 75000){
                    oppgrader(basic.getMedlnr(), "s");
                }
                if(poeng > 75000){
                    oppgrader(basic.getMedlnr(), "g");
                }
            }
            if(obj instanceof SoelvMedlem){
                SoelvMedlem soelv = (SoelvMedlem) obj;
                int poeng = soelv.finnKvalPoeng(date);
                if(poeng > 75000){
                    oppgrader(soelv.getMedlnr(), "g");
                }
            }
        }
        return;
    }
    private void oppgrader(int medlemNr, String to){
        int indeks = index(medlemNr);
        Object obj = liste[indeks];
        BonusMedlem o = null;
        if(obj instanceof BasicMedlem){
            o = (BasicMedlem) obj;
        } else if(obj instanceof SoelvMedlem){
            o = (SoelvMedlem) obj;
        } else if(obj instanceof GullMedlem){
            return;
        } else {
            System.out.println("Finner ikke klasse type.");
            return;
        }
        if(to.equals("s")){
            SoelvMedlem idk = new SoelvMedlem(o.getMedlnr(), o.getPers(), o.getInnmeldtDato());
            idk.setPoeng(o.getPoeng());
            liste[indeks] = idk;
            return;
        }
        if(to.equals("g")){
            GullMedlem idk = new GullMedlem(o.getMedlnr(), o.getPers(), o.getInnmeldtDato());
            idk.setPoeng(o.getPoeng());
            liste[indeks] = idk;
            return;
        }
        return;
        
    }
}
