import java.time.*;
import java.time.temporal.ChronoUnit;

public abstract class BonusMedlem{
    private final int medlNr;
    private final Personalia pers;
    private final LocalDate innmeldtDato; 
    private int poeng = 0;

    public BonusMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato){
        this.medlNr = medlNr;
        this.pers = pers;
        this.innmeldtDato = innmeldtDato;
    }
    public abstract void registrerPoeng(int nyPoeng);

    public int finnKvalPoeng(LocalDate dato){
        //Period tid = Period.between(innmeldtDato, dato);
        //int dager = tid.getDays();
        long dager = ChronoUnit.DAYS.between(innmeldtDato, dato);
        if(dager < 365) return poeng;
        return 0;
    }
    public int getMedlnr(){
        return medlNr;
    }
    public Personalia getPers(){
        return pers;
    }
    public LocalDate getInnmeldtDato(){
        return innmeldtDato;
    }
    public int getPoeng(){
        return poeng;
    }
    public boolean okPassord(String pass){
        return pers.okPassord(pass);
    }
    public void setPoeng(int poeng){
        this.poeng = poeng;
    }
    public void leggTilPoeng(int poeng){
        this.poeng += poeng;
    }
    public String toString(){
        return "MedNr: "+ medlNr +". pers: "+ pers.getFornavn() +" "+ pers.getEtternavn() +". Inmeldt: "+ innmeldtDato.toString() +". Poeng: "+ poeng +". Klasse: "+ getClass();
    }
}
