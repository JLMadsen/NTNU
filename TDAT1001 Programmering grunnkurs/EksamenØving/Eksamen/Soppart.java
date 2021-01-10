//Siden oppgave 4a leser soppregister fra fil må klassen være serializable.
class Soppart implements java.io.Serializable{
    private final String navn;
    private final String beskrivelse;
    private final boolean giftig;
    //2a
    public Soppart(String navn, String beskrivelse, boolean giftig){
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.giftig = giftig;
    }
    public Soppart(Object obj){
        Soppart o = (Soppart) obj;
        navn = o.getNavn();
        beskrivelse = o.getBeskrivelse();
        giftig = o.getGiftig();
    }
    //2b
    public String getNavn(){ return navn; }
    public String getBeskrivelse(){ return beskrivelse; }
    public boolean getGiftig(){ return giftig; }
    //2c
    public boolean equals(Object obj){
        // hvis objektet peker på null er det ikke likt
        if(obj == null){
            return false;
        }
        // hvis objektet er en annen klasse er det ikke likt
        if(!(obj instanceof Soppart)){
            return false;
        }
        // hvis objektet peker på samme referanse som this er de like
        if(obj == this){
            return true;
        }
        // hvis navnene er like er de like
        Soppart o = (Soppart) obj;
        if(navn.equals(o.getNavn())){
            return true;
        }
        return false;
    }
    //2d
    public boolean search(String text){
        if(text.equals(" ") || text == null){
            return false;
        }
        String[] sok = text.split(" "); // deler teksten opp i ord
        String[] bes = beskrivelse.replace(".", "").replace(",", "").split(" "); // deler beskrivelsen opp i ord
        if(sok.length == 1){ // hvis det bare er ett ord man søker etter
            for(int i=0; i<bes.length; i++){
                if(text.equalsIgnoreCase(bes[i])){
                    return true;
                }
            }
        } else { // hvis det er fler enn ett ord.
            for(int i=0; i<sok.length; i++){ // for hvert ord man søker
                for(int j=(i+1); j<bes.length; j++){ // for hvert ord i beskrivelse
                    if(sok[i].equalsIgnoreCase(bes[j])){ // bruker equalsIgnoreCase() slik at store og små bokstaver ikke har noe å si
                        // sjekker den om de samsvarer
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public String toString(){
        return navn +" | "+ beskrivelse +" | Spiselig: "+ giftig;
    }
    
}