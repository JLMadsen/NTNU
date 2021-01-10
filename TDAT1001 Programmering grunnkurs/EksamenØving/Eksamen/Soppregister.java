//Siden oppgave 4a leser soppregister fra fil må klassen være serializable.
class Soppregister implements java.io.Serializable{
    //3a
    private Soppart[] liste;
    private final int LISTE_LENGDE = 10;
    private int antallReg;
    //3b
    public Soppregister(Soppart[] liste){
        this.liste = liste;
        for(int i=0; i<liste.length; i++){
            if(liste[i] != null){
                antallReg++;
            }
        }
    }
    public Soppregister(){ // for å lage ett tomt register
        liste = new Soppart[LISTE_LENGDE];
        antallReg = 0;
    }
    //3c
    public boolean regNyArt(Object obj){
        Soppart o = (Soppart) obj;
        
        //sjekker om den finnes
        for(int i=0; i<antallReg; i++){
            if(o.equals(liste[i])){
                return false;
            }
        }
        // hvis den ikke finnes sjekker jeg lengden på tabellen
        if(antallReg % LISTE_LENGDE == 0 && antallReg != 0){ //hvis ekte lengden på tabellen kan deles på maks er den enten 0 eller maks. men utelukker null med og utrykket
            utvid(LISTE_LENGDE);
        }
        //hvis alt er ok hittil kan jeg legge til arten.
        liste[antallReg] = o;
        return true;
    }
    //Metode for å utvide tabellen, siden det ikke er noen maks grense på tabellen er metoden void. dersom det hadde vært en maks grense ville jeg ha laget en boolean som ga tilbakemelding dersom grensen var nådd
    public void utvid(int lengde){
        // lager ny tabell med økt lengde
        Soppart[] nyListe = new Soppart[ (liste.length+lengde) ];
        // kopierer gammel tabell til ny
        for(int i=0; i<liste.length; i++){
            nyListe[i] = liste[i];
        }
        // peker gammel tabell til ny
        liste = nyListe;
    }
    //3d
    public Soppart[] getSpiseligSopp(){
        Soppart[] tempSpis = new Soppart[antallReg];
        
        int funnet = 0; //for indeksen til nye tabellen.
        for(int i=0; i<liste.length; i++){
            if(!(liste[i].getGiftig())){
                tempSpis[funnet] = liste[i];
                funnet++;
            }
        }
        Soppart[] spis = new Soppart[funnet]; // lager ny tabell med riktig lengde
        for(int j=0; j<funnet; j++){
            spis[j] = tempSpis[j];
        }
        return spis;
    }
    //3e
    public String getAlle(){
        if(antallReg == 0){
            return "Det er ingen registrerte sopparter.";
        }
        String re = "Alle registrerte sopparter:\n";
        
        for(int i=0; i<antallReg; i++){
            re += (i+1) +": "+ liste[i].getNavn() +". "+ liste[i].getBeskrivelse() +". spiselig: "+ liste[i].getGiftig() +"\n";
        }
        // vil skrive ut:
        // 1. Rød Fluesopp. Den er rød. spiselig: false
        return re;
    }
    //3f
    public String search(String text){
        if(text.equals(" ") || text == null){
            return "Feil input.";
        }
        Soppart[] sokt = new Soppart[antallReg]; // lengre tabell enn nødvendig, men den er kun midlertidig
        int funnet=0;
        
        for(int i=0; i<antallReg; i++){
            if(liste[i].search(text)){
                sokt[funnet] = liste[i];
                funnet++;
            }
        }
        if(funnet == 0){
            return "Ingen arter med samsvarende beskrivelse funnet.";
        }
        String re = "Søket ga "+ funnet +" resultater:\n";
        for(int j=0; j<funnet; j++){
            re += (j+1) +". "+ liste[j].getNavn() +". "+ liste[j].getBeskrivelse() +". spiselig: "+ liste[j].getGiftig() +"\n";
        }
        return re;
    }
    
}