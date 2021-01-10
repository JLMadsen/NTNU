import Dyrehage.*;

class TestKlient{
    public static void testDyr(SkandinaviskeRovdyr dyr){
        System.out.println(dyr.getNavn());
        System.out.println(dyr.getFdato());
        System.out.println(dyr.getAlder());
        System.out.println(dyr.getAdresse());
        System.out.println(dyr.skrivUtInfo());

        System.out.println(dyr.getAntKull());
        dyr.leggTilNyttKull();
        System.out.println(dyr.getAntKull());
    }
    public static void main(String[] args){

        RovdyrFabrikk rf = new RovdyrFabrikk();

        SkandinaviskeRovdyr idk = rf.nyHannbjørn("per", 1997, true, "Brun bjørn", 2019, "Bur 420");
        SkandinaviskeRovdyr jvi = rf.nyBinne("Annbjørg", 1337, false, "Brun bjørn", 2019, "Bur 420", 69);


        testDyr(idk);
        testDyr(jvi);


    }
}