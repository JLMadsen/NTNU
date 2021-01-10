package Dyrehage;

public class RovdyrFabrikk{
    private String bj�rnLatNavn = "Ursus arctos";
    private String bj�rnFamilieNavn = "Ursidae";
    private String ulvLatNavn = "Canis lupus";
    private String ulvFamilieNavn = "Canidae";
    private boolean farlig = true;

    public RovdyrFabrikk(){}

    public SkandinaviskeRovdyr nyBinne(String navn, int fDato, boolean hanndyr, String norskNavn, int ankommetDato, String adresse, int antKull){
        return new HunnIndivid(navn, fDato, hanndyr, farlig, norskNavn, bj�rnLatNavn, bj�rnFamilieNavn, ankommetDato, adresse, antKull); 
    }
    public SkandinaviskeRovdyr nyHannbj�rn(String navn, int fDato, boolean hanndyr, String norskNavn, int ankommetDato, String adresse){
        return new HannIndivid(navn, fDato, hanndyr, farlig, norskNavn, bj�rnLatNavn, bj�rnFamilieNavn, ankommetDato, adresse);
    }
    public SkandinaviskeRovdyr nyUlvetispe(String navn, int fDato, boolean hanndyr, String norskNavn, int ankommetDato, String adresse, int antKull){
        return new HunnIndivid(navn, fDato, hanndyr, farlig, norskNavn, ulvLatNavn, ulvFamilieNavn, ankommetDato, adresse, antKull);
    }
    public SkandinaviskeRovdyr nyUlvehann(String navn, int fDato, boolean hanndyr, String norskNavn, int ankommetDato, String adresse){
        return new HannIndivid(navn, fDato, hanndyr, farlig, norskNavn, ulvLatNavn, ulvFamilieNavn, ankommetDato, adresse);
    }
}