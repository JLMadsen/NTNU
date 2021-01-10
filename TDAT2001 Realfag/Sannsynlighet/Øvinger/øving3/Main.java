import java.text.DecimalFormat;

class Main 
{
    public static void main(String[] args) 
    {
        DecimalFormat df = new DecimalFormat("#.###");

        double[] x = {0.0, 0.3, 0.5, 0.6, 0.7, 0.8, 1.0, 1.2, 1.6};
        double[] p = {(1.0/28), (2.0/28), (5.0/28), (8.0/28), (5.0/28), (3.0/28), (2.0/28), (3.0/56), (1.0/56)};

        Sannsynlighet san = new Sannsynlighet(x, p);

        System.out.println("Forventning = " + df.format( san.expectation() ));

        System.out.println("Varianse = " + df.format( san.variance() ));

        System.out.println("Standardavvik = " + df.format( san.deviation() ));

        double start = 0.7;
        double end = 1.2;
        System.out.print("Fordeling = " + san.distribution(start, end));

    }
}

// Lag en Java-klasse som har metoder for å regne ut:
class Sannsynlighet 
{
    /* variabel X, der X er gitt ved to like store tabeller/lister der den ene
    inneholder verdiene X kan ta og den andre inneholder sannsynligheten for hver av dem. Merk at fordelingsfunksjonen bør fungere som en
    (matematisk) funksjon skal, dvs. at den bør ta inn e */

    private double[] x;
    private double[] p; // probability
    private double exp;
    private double var;

    Sannsynlighet(double[] x, double[] p) 
    {
        this.x = x;
        this.p = p;
    }

    // forventningen
    public double expectation() 
    {
        double exp = 0.0;

        for(int i = 0; i<x.length; i++) 
        {
            exp += x[i]*p[i];
        }

        this.exp = exp;
        return exp;
    }
    
    // variansen
    public double variance() 
    {
        double var = 0.0;

        for(int i = 0; i<x.length; i++) 
        {
            var += Math.pow(x[i],2) * p[i];
        }

        if(exp == 0.0)
        {
            expectation();
        }

        var -= Math.pow(exp,2);
        
        this.var = var;
        return var;
    }

    // standardavviket
    public double deviation()
    {
        double dev = 0.0;

        if(var == 0.0)
        {
            variance();
        }

        dev = Math.sqrt(var);

        return dev;
    }

    // fordelingsfunksjonen til en stokastisk variabel
    public double distribution(double start, double end)
    {
        double dis = 0.0;
        boolean inRange = false;

        for(int i = 0; i<x.length; i++)
        {
            if(x[i]<=end && x[i]>=start)
            {
                inRange = true;
            }
            else{
                inRange = false;
            }
            if(inRange)
            {
                dis += p[i];
            }
        }
        
        return dis;
    }
}