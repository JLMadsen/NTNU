abstract class Tribune implements java.io.Serializable{
    private final String tribunenavn;
    private final int kapasitet;
    private final int pris;

    public Tribune(String tribunenavn, int kapasitet, int pris){
        this.tribunenavn = tribunenavn;
        this.kapasitet = kapasitet;
        this.pris = pris;
    }
    public abstract int finnAntallSolgteBilletter();
    public abstract int finnInntekt();
    public abstract Billett[] kjopBilletter(int n);
    public abstract Billett[] kjopBilletter(String[] navn);

    public String getTribuneNavn(){
        return tribunenavn;
    }
    public int getPris(){
        return pris;
    }
    public int getKapasitet(){
        return kapasitet;
    }
    public String toString(){
        return "Tribune: "+ tribunenavn +". antall plasser: "+ kapasitet +". pris: "+ pris;
    }
}



//__________________________________________________________________________________ Staa
class Staa extends Tribune{
    private int antSolgtBilleter;

    public Staa(String navn, int pris, int kapasitet){
        super(navn, kapasitet, pris);
    }
    @Override
    public int finnAntallSolgteBilletter(){
        return antSolgtBilleter;
    }
    @Override
    public int finnInntekt(){
        return finnAntallSolgteBilletter()*super.getPris();
    }
    @Override
    public Billett[] kjopBilletter(int n){
        StaaplassBillett[] re = new StaaplassBillett[n];
        if((antSolgtBilleter+n) < super.getKapasitet()){
            for(int i=0; i<n; i++){
                re[i] = new StaaplassBillett(super.getTribuneNavn(), super.getPris());
            }
            antSolgtBilleter += n;
            return re;
        }
        return null;
    }
    @Override
    public Billett[] kjopBilletter(String[] navn){
        int n = navn.length;
        return kjopBilletter(n);
    }
    public String toString(){
        return super.toString() +". Antall stå plasser solgt: "+ antSolgtBilleter +". inntekt: "+ finnInntekt();
    }
}




//__________________________________________________________________________________ Sitte
class Sitte extends Tribune{
    private int[] antOpptatt; // antall opptatte plasser per rad
    private int plasser; // per rad   

    public Sitte(String navn, int pris, int antRader, int antPlasser){
        super(navn, (antPlasser*antRader), pris);
        antOpptatt = new int[antRader];
        plasser = antPlasser;
        
    }
    @Override
    public int finnAntallSolgteBilletter(){
        int antall = 0;
        for(int i : antOpptatt){
            antall += i;
        }
        return antall;
    }
    @Override
    public int finnInntekt(){
        return finnAntallSolgteBilletter()*super.getPris();
    }
    @Override
    public Billett[] kjopBilletter(int n){
        if(n > plasser){
            return null;
        }
        SitteplassBillett[] re = new SitteplassBillett[n];
        for(int i=0; i<antOpptatt.length; i++){
            if( (antOpptatt[i]+n) < plasser){
                for(int j=0; j<n; j++){
                    re[j] = new SitteplassBillett(super.getTribuneNavn(), super.getPris(), i, antOpptatt[i]);
                    antOpptatt[i]++;
                }
                return re;
            }
        }
        return null;
    }
    @Override
    public Billett[] kjopBilletter(String[] navn){
        int n = navn.length;
        return kjopBilletter(n);
    }
    public String toString(){
        return super.toString() +". Tribunen har "+ plasser +" plasser på: "+ antOpptatt.length +" rader. "+ finnAntallSolgteBilletter() +" er opptatt. inntekt: "+ finnInntekt();
    }
}




//__________________________________________________________________________________ VIP
class VIP extends Sitte{
    private String[][] tilskuer;

    public VIP(String navn, int pris, int rader, int plasser){
        super(navn, pris, rader, plasser);
        tilskuer = new String[rader][plasser];
    }
    @Override
    public int finnAntallSolgteBilletter(){
        int antall = 0;
        for(int i=0; i<tilskuer.length; i++){
            for(int j=0; j<tilskuer[i].length; j++){
                if(tilskuer[i][j] != null){
                    antall++;
                }
            }
        }
        return antall;
    }
    @Override
    public int finnInntekt(){
        return finnAntallSolgteBilletter()*super.getPris();
    }
    @Override
    public Billett[] kjopBilletter(int n){
        System.out.println("Trenger navn for å reservere VIP seter.");
        return null;
    }
    @Override
    public Billett[] kjopBilletter(String[] navn){
        int n = navn.length;
        SitteplassBillett[] re = new SitteplassBillett[n];
        int ledige;
        int indeks;
        for(int i=0; i<tilskuer.length; i++){
            ledige = 0;
            indeks = 0;
            for(int j=0; j<tilskuer[i].length; j++){
                if(tilskuer[i][j] == null){
                    ledige++;
                    if(indeks == 0) { indeks = j; }
                }
            }
            if( n <= ledige){
                for(int p=0; p<n; p++){
                    tilskuer[i][(indeks+p)] = navn[p];
                    re[p] = new SitteplassBillett(super.getTribuneNavn(), super.getPris(), i, (indeks+p));
                }
                return re;
            }
        }
        return null;
    }
    public String toString(){
        return super.toString();
    }
}