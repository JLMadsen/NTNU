package sample;

public class Valuta {

    private double mod;
    private String name;

    public Valuta(double mod, String name){
        this.mod = mod;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public double getNok(double n){
        return (n/mod);
    }
    public double getReal(double n) {
        return (n * mod);
    }
}
