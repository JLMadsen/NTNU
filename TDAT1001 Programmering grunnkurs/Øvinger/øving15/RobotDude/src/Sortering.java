
class Sortering{
    public static void main(String[] args) {
        int[] tabell = {49,12,15,27,97,54,76,61,28,1,0,57,6,50};
        Sorter g = new Sorter();

        int[] tabell2 = g.getHighToLow(tabell);
        String tall = "1: ";
        for(int i = 0; i < tabell2.length; i++){
            tall += tabell2[i] +" ";
        }
        System.out.println(tall);

        int[] tabell3 = g.getLowToHigh(tabell);
        tall = "2: ";
        for(int i = 0; i < tabell3.length; i++){
            tall += tabell3[i] +" ";
        }
        System.out.println(tall);

        int[] tabell4 = g.bubbleHightoLow(tabell);
        tall = "3: ";
        for(int i = 0; i < tabell4.length; i++){
            tall += tabell4[i] +" ";
        }
        System.out.println(tall);
    }
}
class Sorter{
    public Sorter(){}
    public int[] getHighToLow(int[] tabell){

        for(int i = 0; i < tabell.length; i++){
            int highPos = i;
            for(int k = i+1; k < tabell.length; k++){
                if(tabell[highPos] < tabell[k]){
                    highPos = k;
                }
            }
            int holder = tabell[highPos];
            tabell[highPos] = tabell[i];
            tabell[i] = holder;

        }
        return tabell;
    }
    public int[] getLowToHigh(int[] tabell2){

        for(int i = 0; i < tabell2.length; i++){
            int lowPos = i;
            for(int k = i+1; k < tabell2.length; k++){
                if(tabell2[lowPos] > tabell2[k]){
                    lowPos = k;
                }
            }
            int holder = tabell2[lowPos];
            tabell2[lowPos] = tabell2[i];
            tabell2[i] = holder;

        }
        return tabell2;
    }
    public int[] bubbleHightoLow(int[] tabell3){
        for(int i = 0; i < tabell3.length; i++){
            for(int k = 0; k < tabell3.length-i-1; k++){
                if(tabell3[k] > tabell3[k+1]){
                    int holder = tabell3[k+1];
                    tabell3[k+1] = tabell3[k];
                    tabell3[k] = holder;
                }
            }
        }
        return tabell3;
    }
}
