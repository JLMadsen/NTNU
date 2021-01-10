import static javax.swing.JOptionPane.*;

class Klient{
    public static void main(String[] args){
        Restaurant res = new Restaurant("Den gylne hane", 1945, 10);
        /*String[] arr = new String[10];
        res.importTables(arr);*/

        while(true){
            String choice = showInputDialog("Hva vil du gjøre?\n1) Status på bord.\n2) Søk med navn.\n3) Registrer bord.\n4) Frigi bord.\n5) bord.toString().\n6) Hvor gammel er restauranten?\n7) Hva heter restauranten?\n8) Avslutt.");
            switch(Integer.parseInt(choice)){
                case 1:
                System.out.println("Ledige: "+res.getFree()+". Reservert: "+res.getTaken()+".");
                break;
                case 2:
                String name1 = "";
                do{
                    name1 = showInputDialog("Hvilket navn?");
                } while(name1 == null);
                int[] find = res.getOrderedBy(name1);
                for(int i=0; i<find.length; i++){
                    System.out.println(find[i]);
                }   
                break;
                case 3:
                String n = "";
                String name2 = "";
                do{
                    n = showInputDialog("Hvor mange bord vil du reservere? (Dersom det ikke er nok bord vil ingen bord reserveres.)");
                } while(n == null);
                do{
                    name2 = showInputDialog("Hvilket navn skal det stå på?");
                } while(name2 == null);
                if(res.orderTable(Integer.parseInt(n), name2)){
                    System.out.println("Bord reservert.");
                } else {
                    System.out.println("Ingen ledige bord, "+ res.getFree() +" Ledige bord.");
                }
                break;
                case 4:
                String read = showInputDialog("Hvilke bord skal ryddes? (skill bord nr med mellomrom)");
                String[] arr1 = read.split(" ");
                if(arr1.length == 0){
                    System.out.println("Ingen bord valgt.");
                    break;
                }
                int[] arr2 = new int[arr1.length];
                for(int i=0; i<arr1.length; i++){
                    arr2[i] = Integer.parseInt(arr1[i]);
                }
                res.clearTables(arr2);
                System.out.println("Bord ryddet.");
                break;
                case 5:
                System.out.println(res.toString());
                break;
                case 6:
                System.out.println("Restauranten er "+ res.getOld() +" år gammel.");
                break;
                case 7:
                System.out.println("Restauranten heter: "+ res.getName());
                break;
                case 8:
                System.out.println("Avslutter.");
                return;
                default:
                System.out.println("Feil input, prøv igjen.");
                break;
            }
        }
    }
}