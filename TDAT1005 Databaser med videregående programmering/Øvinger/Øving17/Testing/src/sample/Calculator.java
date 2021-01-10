package sample;

public class Calculator {
    private Valuta[] currencies = {new Valuta(1, "Norske Kroner"),
                                   new Valuta(0.1144, "Amerikanske Dollar"),
                                   new Valuta(0.101783978, "Euro"),
                                   new Valuta(12.716763, "Japanske Yen"),
                                   new Valuta(1.07924528, "Svenske Kroner")};

    private Valuta curr1;
    private Valuta curr2;
    private int amount;

    private double tempMod;
    private String tempName;

    public Calculator(){

    }
    public void setAmount(int n){
        amount = n;
    }
    public void setTempMod(double n){
        tempMod = n;
    }
    public void setTempName(String n){
        tempName = n;
    }
    public void setCurr1(Valuta n){
        curr1 = n;
    }
    public void setCurr2(Valuta n){
        curr2 = n;
    }
    public Valuta getCurr2(){
        return curr2;
    }
    public Valuta getCurr(String name){
        for(int i=0; i<currencies.length; i++){
            if(name.equalsIgnoreCase(currencies[i].getName())){
                return currencies[i];
            }
        }
        return null;
    }
    public String[] getNames(){
        String[] result = new String[currencies.length];
        for(int i=0; i<currencies.length; i++){
            result[i] = currencies[i].getName();
        }
        return result;
    }
    public boolean newCurrency(){
        for(int i=0; i<currencies.length; i++){
            if(tempName.equalsIgnoreCase(currencies[i].getName())){
                return false;
            }
        }
        expand();
        currencies[currencies.length-1] = new Valuta(tempMod, tempName);
        return true;
    }
    public void expand(){
        Valuta[] temp = new Valuta[currencies.length+1];
        for(int i=0; i<currencies.length; i++){
            temp[i] = currencies[i];
        }
        currencies = temp;
    }
    public double calc(){
        return curr2.getReal(curr1.getNok(amount));
    }
}
