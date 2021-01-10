import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

class eks2018{
    public static void main(String[] args){

    }
}

class Kunde{
    
    private final int kundenr;
    private final String navn;
    private ArrayList<Dyr> dyrene = new ArrayList<Dyr>();

    public Kunde(int kundenr, String navn){
        this.kundenr = kundenr;
        this.navn = navn;
    }

    public int getKundenr(){ return kundenr; }
    public String getNavn(){ return navn; }
    public ArrayList<Opphold> getOpphold(){ return oppholdene; }
    public ArrayList<Dyr> getDyr(){ return dyrene; }
    public boolean nyttOpphold(String navn, Dato fra, Dato til){
        for(Dyr dyr : dyrene){
            if(dyr.getNavn().equals(navn)){
                if(dyr.nyttOpphold(fra, til)){
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
    public int faktura(Dato fra, Dato til){
        int totalpris = 0;
        boolean hundRabatt = false;
        boolean kattRabatt = false;
        for(Dyr dyr : dyrene){
            int[] p = dyr.faktura(fra, til);
            if(p[0] == 0){
                continue;
            } else {
                if(dyr instanceof Hund){
                    dyr = (Hund) dyr;
                    if(hundRabatt){
                        totalpris += (int) p[0]*0.75;
                        totalpris += p[1];
                    } else {
                        totalpris += p[0] + p[1];
                        hundRabatt = true;
                    }
                } else {
                    dyr = (Katt) dyr;
                    if(kattRabatt){
                        totalpris += (int) p[0]*0.75;
                        totalpris += p[1];
                    } else {
                        totalpris += p[0] + p[1];
                        kattRabatt = true;
                    }
                }
            }
        }
        return totalpris;
    }
}

abstract class Dyr{

    private final int id;
    private final String navn;
    private ArrayList<Opphold> oppholdene = new ArrayList<Opphold>();


    public Dyr(int id, String navn){
        this.id = id;
        this.navn = navn;

    }

    public int getId(){ return id; }
    public String getNavn(){ return navn; }
    public abstract getPris();
    public boolean nyttOpphold(Dato fra, Dato til){
        for(Opphold opphold : oppholdene){
            if(opphold.ledig(fra, til) == false){
                return false;
            }
        }
        oppholdene.add(new Opphold(this, fra, til));
        return true;
    }
    public int[] faktura(Dato fra, Dato til){
        Dato[] d = {fra, til};
        int[] pris = new int[2];
        for(Opphold opphold : oppholdene){
            if(opphold.getDato().equals(d)){
                ArrayList<Tillegg> tillegg = opphold.getTjenester();
                for(Tillegg t : tillegg){
                    pris[1] += t.getPris();
                }
                int dager = Dato.antDager(fra, til);
                pris[0] = dyr.getPris()*dager;
            }
        }
        return pris;
    }
}

class Hund extends Dyr{

    private int str;
    private final int GRUNNPRIS = 360;

    public Hund(int id, String navn, int str){
        super(id, navn);
        this.str = str;
    }

    public int getStr(){ return str; }

    @Override
    public int getPris(){
        int tillegg = 0;
        if(str != 1){
            tillegg += 20*str-10;
        }
        return GRUNNPRIS+tillegg;
    }
}

class Katt extends Dyr{

    private boolean groom;
    private int GRUNNPRIS = 170;

    public Katt(int id, String navn, boolean groom){
        super(id, navn);
        this.groom = groom;
    }

    public boolean getGroom(){ return groom; }

    @Override
    public int getPris(){
        if(groom){
            return GRUNNPRIS+20;
        } else {
            return GRUNNPRIS;
        }
    }
}

class Opphold{

    private Dyr dyr;
    private Dato fra;
    private Dato til;
    private ArrayList<Tillegg> tjenester = new ArrayList<Tillegg>();

    public Opphold(Dyr dyr, Dato fra, Dato til){
        this.dyr = dyr;
        this.fra = fra;
        this.til = til;
    }
    public Dato[] getDato(){
        Dato[] d = {fra, til};
        return d;
    }

    public boolean ledig(Dato nyFra, Dato nyTil){
        //Pseudokode
        if(Dato.overlapp(fra, til, nyFra, nyTil)){
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<Tillegg> getTjenester(){ return tjenester; }
}

class Database{

    private java.sql.Connection conn;

    public ArrayList<Tillegg> hentTillegg(){
        PreparedStatement query = null;
        ArrayList<Tillegg> tillegg = new ArrayList<>();
        try{
            query = conn.preparestatement("SELECT * FROM tillegg JOIN tillegg_med_str USING(nr);");
            ResultSet resultat = query.executeQuery();
            query.close();
            Tilleg t;
            while(resultat.next()){
                int nr = resultat.getInt("nr");
                int pris = resultat.getInt("pris");
                String navn = resultat.getString("navn");
                int str = resultat.getInt("størrelse");
                if(str == 0){
                    t = new Tillegg(nr, navn, pris);
                } else {
                    t = new TilleggMedStr(nr, navn, pris, str);
                }
                tillegg.add(t);

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}