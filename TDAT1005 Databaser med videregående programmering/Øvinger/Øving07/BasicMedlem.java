import java.time.LocalDate;

public class BasicMedlem extends BonusMedlem{
    public BasicMedlem(int memberID, Personalia pers, LocalDate date, int points){
        super(memberID, pers, date);
        setPoeng(points);
    }
    public BasicMedlem(int memberID, Personalia pers, LocalDate date){
        super(memberID, pers, date);
    }
    @Override
    public void registrerPoeng(int poeng){
        leggTilPoeng(poeng);
    } 
}