package Dyrehage;

class Dyr{
    private final String  norskNavn;
    private final String  latNavn;
    private final String  latFamilie;
    private final int  ankommetDato;
    private String adresse;

    public Dyr(String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse){
        this.norskNavn = norskNavn;
        this.latNavn = latNavn;
        this.latFamilie = latFamilie;
        this.ankommetDato = ankommetDato;
        this.adresse = adresse;
    }
    public String getNorskNavn(){
        return norskNavn;
    }
    public String getLatNavn(){
        return latNavn;
    }
    public String getLatFamilie(){
        return latFamilie;
    }
    public int getAnkommetDato(){
        return ankommetDato;
    }
    public String getAdresse(){
        return adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    public String toString(){
        return "Norsk navn: " + norskNavn + ". Latinsk navn og familie: " + latNavn
        + ", " + latFamilie + ". Adresse: "+adresse;
    }
}
class Dyregruppe extends Dyr{
    private final String gruppenavn;
    private int antIndivider;

    public Dyregruppe(String gruppenavn, int antIndivider, String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse){
        super(norskNavn, latNavn, latFamilie, ankommetDato, adresse);
        this.gruppenavn = gruppenavn;
        this.antIndivider = antIndivider;
    }
    @Override
    public String getNorskNavn(){
        return "Gruppe av "+ super.getNorskNavn();
    }
    public String getGruppenavn(){
        return gruppenavn;
    }
    public int getAntIndivider(){
        return antIndivider;
    }
    public void setAntIndivider(int n){
        antIndivider = n;
    }
    public String toString(){
        return super.toString() +". Gruppe av "+ gruppenavn +". Antall individer: "+ antIndivider;
    }
}
abstract class Individ extends Dyr implements SkandinaviskeRovdyr{
    private final String navn;
    private final int fDato;
    private final boolean hanndyr;
    private final boolean farlig;

    public Individ(String navn, int fDato, boolean hanndyr, boolean farlig, String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse){
        super(norskNavn, latNavn, latFamilie, ankommetDato, adresse);
        this.navn = navn;
        this.fDato = fDato;
        this.hanndyr = hanndyr;
        this.farlig = farlig;
    }
    public String getNavn(){
        return navn;
    }
    public int getFdato(){
        return fDato;
    }
    public int getAlder(){
        return (2019-fDato);
    }
    public void flytt(String nyAdresse){
        super.setAdresse(nyAdresse);
    }
    public boolean getHanndyr(){
        return hanndyr;
    }
    public boolean getFarlig(){
        return farlig;
    }
    public String skrivUtInfo(){
        return toString();
    }
    public String toString(){
        return super.toString() +". Navn: "+ navn +". Dato: "+ fDato +". Hanndyr = "+ hanndyr +". Farlig = "+ farlig;
    }
}
class HunnIndivid extends Individ{
    private int antKull;

    public HunnIndivid(String navn, int fDato, boolean hanndyr, boolean farlig, String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse, int antKull){
        super(navn, fDato, hanndyr, farlig, norskNavn, latNavn, latFamilie, ankommetDato, adresse);
        this.antKull = antKull;
    }
    public int getAntKull(){
        return antKull;
    }
    public void leggTilKull(int antall){
        antKull += antall;
    }
    public void leggTilNyttKull(){
        antKull++;
    }
    public String toString(){
        return super.toString() +". Antall kull: "+ antKull;
    }
}
class HannIndivid extends Individ{
    public HannIndivid(String navn, int fDato, boolean hanndyr, boolean farlig, String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse){
        super(navn, fDato, hanndyr, farlig, norskNavn, latNavn, latFamilie, ankommetDato, adresse);
    }
    public void setKull(int n){
        System.out.println("Feil kj�nn kompis.");
    }
    public int getAntKull(){
        return 0;
    }
    public void leggTilKull(int antall){
        return;
    }
    public void leggTilNyttKull(){
        return;
    }
    public String toString(){
        return super.toString();
    }
}
class Fiskestim extends Dyregruppe{
    private final double gjennomsnittligLengde;
    private final boolean kanDeleAkvarium;

    public Fiskestim(double gjennomsnittligLengde, boolean kanDeleAkvarium, String gruppenavn, int antIndivider, String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse){
        super(gruppenavn, antIndivider, norskNavn, latNavn, latFamilie, ankommetDato, adresse);
        this.gjennomsnittligLengde = gjennomsnittligLengde;
        this.kanDeleAkvarium = kanDeleAkvarium;
    }
    public double getGjennomsnittligLengde(){
        return gjennomsnittligLengde;
    }
    public boolean kanDeleAkvarium(){
        return kanDeleAkvarium;
    }
    public String toString(){
        return super.toString() +". Avg.Lengde: "+ gjennomsnittligLengde +" m. Kan dele akvarium = "+ kanDeleAkvarium;
    }
}
class Fugleflokk extends Dyregruppe{
    private final double gjennomsnittligVekt;
    private final boolean sv�mmer;

    public Fugleflokk(double gjennomsnittligVekt, boolean sv�mmer, String gruppenavn, int antIndivider, String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse){
        super(gruppenavn, antIndivider, norskNavn, latNavn, latFamilie, ankommetDato, adresse);
        this.gjennomsnittligVekt = gjennomsnittligVekt;
        this.sv�mmer = sv�mmer;
    }
    public double getGjennomsnittligVekt(){
        return gjennomsnittligVekt;
    }
    public boolean getSv�mmer(){
        return sv�mmer;
    }
    public String toString(){
        return super.toString() +". Avg.vekt: "+ gjennomsnittligVekt +" kg. Sv�mmer = "+ sv�mmer;
    }
}