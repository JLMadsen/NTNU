class Kunstverk implements java.io.Serializable{
    private final String tittel;
    private final String type;
    private final int antKopi;
    private final int kopiNr;
    private final Navn kunstner;

    public Kunstverk(String tittel, String type, int antKopi, int kopiNr, Navn kunstner){
        this.tittel = tittel;
        this.type = type;
        this.antKopi = antKopi;
        this.kopiNr = kopiNr;
        this.kunstner = kunstner;
    }
    public String getTittel(){
        return tittel;
    }
    public String getType(){
        return type;
    }
    public int getAntKopi(){
        return antKopi;
    }
    public int getKopiNr(){
        return kopiNr;
    }
    public String getKunstner(){
        return kunstner.toString();
    }
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(o == this){
            return true;
        }
        if(!(o instanceof Kunstverk)){
            return false;
        }
        Kunstverk temp = (Kunstverk) o;
        if(temp.getKunstner().equals(kunstner.toString()) && temp.getTittel().equals(tittel)){
            return true;
        }
        return false;
    }
    public String toString(){
        return kopiNr +"/"+ antKopi +" "+ tittel +" "+ kunstner.toString();
    }
}