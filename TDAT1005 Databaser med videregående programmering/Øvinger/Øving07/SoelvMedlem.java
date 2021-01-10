import java.time.LocalDate;

public class SoelvMedlem extends BonusMedlem{
    public SoelvMedlem(int memberID, Personalia pers, LocalDate date, int points){
        super(memberID, pers, date);
        setPoeng(points);
    }
    public SoelvMedlem(int memberID, Personalia pers, LocalDate date){
        super(memberID, pers, date);
    }
    @Override
    public void registrerPoeng(int poeng){
        leggTilPoeng((int) (poeng * 1.2));
    }
}