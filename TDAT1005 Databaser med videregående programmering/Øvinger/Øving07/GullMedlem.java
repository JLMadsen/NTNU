import java.time.LocalDate;

public class GullMedlem extends BonusMedlem{
    public GullMedlem(int memberID, Personalia pers, LocalDate date, int points){
        super(memberID, pers, date);
        setPoeng(points);
    }
    public GullMedlem(int memberID, Personalia pers, LocalDate date){
        super(memberID, pers, date);
    }
    @Override
    public void registrerPoeng(int poeng){
        leggTilPoeng((int) (poeng * 1.5) );
    }
}