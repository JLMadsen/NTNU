class Utstilling implements java.io.Serializable{
    private String navn;
    private Kunstverk[] liste;
    private String filnavn;

    public Utstilling(String navn, String filnavn){
        this.navn = navn;
        this.filnavn = filnavn;
       // lesFraFil();
    }
    public Utstilling(String navn){
        this.navn = navn;
        filnavn = "utstilling.ser";
       // lesFraFil();
    }
    public String getNavn(){
        return navn;
    }
    public Kunstverk[] getKunstverk(){
        return liste;
    }
    public String getKunstverk(Navn Kunstner){
        int antallReg = 0;
        String posisjoner = "";

        for(int i=0; i<liste.length; i++){
            if(liste[i].getKunstner().equals(Kunstner.toString())){
                posisjoner += i +" ";
            }
        }
        String[] pos = posisjoner.split(" ");
        antallReg = pos.length;

        Kunstverk[] k = new Kunstverk[antallReg];
        for(int j=0; j<antallReg; j++){

            k[j] = liste[Integer.parseInt(pos[j])];
        }
        Kunstverk[] sortert = sorter(k);

        String re = navn +" \nKunstobjekter på utstilling:\n";
        for(int p=0; p<sortert.length; p++){
            re += sortert[p].toString() +"\n";
        }
        return re;
    }
    public Kunstverk[] sorter(Kunstverk[] k){

        Kunstverk[] sortert = new Kunstverk[k.length];
        for(int i=0; i<k.length; i++){

            sortert[i] = k[i];
        }

        for(int start=0; start<sortert.length; start++){

            int high = start;
            for(int j=(start+1); j < sortert.length; j++){

                if( sortert[high].getTittel().compareTo(sortert[j].getTittel()) > 0 )
                high = j;
            }
            Kunstverk temp = sortert[start];
            sortert[start] = sortert[high];
            sortert[high] = temp;
        }
        return sortert;
    }
    public void addKunstverk(String tittel, String type, int antKopi, int kopiNr, Navn kunstner){
        Kunstverk k = new Kunstverk(tittel, type, antKopi, kopiNr, kunstner);
        utvid();
        liste[(liste.length-1)] = k;
    }
    public void utvid(){
        Kunstverk[] temp = new Kunstverk[(liste.length+1)];

        for(int i=0; i<liste.length; i++){
            temp[i] = liste[i];
        }
        liste = temp;
    }
    /*
    public void lesFraFil(){
        try{
            FileInputSteam fis = new FileInputSteam(filnavn);
            ObjectInputSteam ois = new ObjectInputSteam(fis);

            Object read;
            int i = 0;
            while(read != null){
                read = ois.readObject();
                liste[i] = read;
                i++;
            }
            

            ois.close();
        }
        catch(Exception e){
            System.out.println("Feil i lesFraFil");
            e.printStackTrace();
        }
    }
    public void skrivTilFil(){
        try{
            FileOutputStream fos = new FileOutputStream(filnavn);
            ObjectOutputSteam oos = new ObjectOutputSteam(fos);

            for(int i=0; i<liste.length; i++){
                oos.writeObject(liste[i]);
            }
            oos.close();
        }
        catch(Exception e){
            System.out.println("Feil i SkrivTilFil");
            e.printStackTrace();
        }
    }*/
    public String toString(){
        String re = navn +" \n";

        for(int i=0; i<liste.length; i++){
            re += liste[i].toString() +"\n";
        }
        return re;
    }
    public void testing(Kunstverk[] l){
        liste = l;
    }
}