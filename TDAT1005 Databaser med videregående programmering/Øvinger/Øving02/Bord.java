class Bord{

    private String[] customers;

    public Bord(int tables){
        customers = new String[tables];
    }

    public int getFree(){
        int free = 0;
        for(int i=0; i<customers.length; i++){
            if(customers[i] == null){
                free++;
            }
        }
        return free;
    }
    public int getTaken(){
        return (customers.length-getFree());
    }
    public boolean orderTable(int n, String name){
        if(getFree() < n){
            return false;
        }
        int counter = 0;
        for(int i=0; i<customers.length; i++){
            if(counter == n){
                break;
            } else if(customers[i] == null){
                customers[i] = name;
                counter++;
            }
        }
        return true;
    }
    public void clearTable(int[] tables){
        for(int i=0; i<tables.length; i++){
            if(tables[i] < 0 || tables[i] > (customers.length-1)){
                System.out.println("Bord nr "+ tables[i] +" finnes ikke.");
            } else {
                customers[tables[i]] = null;
            }
        }
    }
    public int[] getOrderedBy(String customer){
        String temp = "";
        for(int i=0; i<customers.length; i++){
            if(customers[i]!=null){
                if(customers[i].equalsIgnoreCase(customer)){
                    temp += i +" ";
                }
            }
        }
        String[] arr = temp.split(" ");
        int [] arrr = new int[arr.length];
        for(int j=0; j<arr.length; j++){
            arrr[j] = Integer.parseInt(arr[j]);
        }
        return arrr;
    }
    public String toString(){
        String re = "";
        for(int i=0; i<customers.length; i++){
            re += customers[i] +" ";
        }
        return re;
    }
}