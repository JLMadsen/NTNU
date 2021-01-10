package Dyrehage;

public class RovdyrFabrikk{
    private String bjørnLatNavn = "Ursus arctos";
    private String bjørnFamilieNavn = "Ursidae";
    private String ulvLatNavn = "Canis lupus";
    private String ulvFamilieNavn = "Canidae";
    private boolean farlig = true;

    public RovdyrFabrikk(){}

    public SkandinaviskeRovdyr nyBinne(String navn, int fDato, boolean hanndyr, String norskNavn, int ankommetDato, String adresse, int antKull){
        return new HunnIndivid(navn, fDato, hanndyr, farlig, norskNavn, bjørnLatNavn, bjørnFamilieNavn, ankommetDato, adresse, antKull); 
    }
    public SkandinaviskeRovdyr nyHannbjørn(String navn, int fDato, boolean hanndyr, String norskNavn, int ankommetDato, String adresse){
        return new HannIndivid(navn, fDato, hanndyr, farlig, norskNavn, bjørnLatNavn, bjørnFamilieNavn, ankommetDato, adresse);
    }
    public SkandinaviskeRovdyr nyUlvetispe(String navn, int fDato, boolean hanndyr, String norskNavn, int ankommetDato, String adresse, int antKull){
        return new HunnIndivid(navn, fDato, hanndyr, farlig, norskNavn, ulvLatNavn, ulvFamilieNavn, ankommetDato, adresse, antKull);
    }
    public SkandinaviskeRovdyr nyUlvehann(String navn, int fDato, boolean hanndyr, String norskNavn, int ankommetDato, String adresse){
        return new HannIndivid(navn, fDato, hanndyr, farlig, norskNavn, ulvLatNavn, ulvFamilieNavn, ankommetDato, adresse);
    }
}