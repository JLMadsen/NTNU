import java.sql.SQLOutput;
import java.util.ArrayList;

class Aksjer {

    public static void main(String[] args) {

        // From the book
        int[] changes = {-1, 3, -9, 2, 2, -1, 2, -1, -5};

        // Random data
        //int[] changes = getRandom(n);

        long start = System.currentTimeMillis();

        int[] values = getValues(changes);
        int[] result = buyAll(values);

        long end = System.currentTimeMillis();

        System.out.println("Buy on day: "+ result[0]);
        System.out.println("Sell on day: "+ result[1]);
        System.out.println("Profit: "+ result[2]);

        System.out.println("Time used: "+ (end-start));


    }

    public static int[] buyAll(int[] values) {
        ArrayList<Integer[]> transactions = new Arraylist<>();
        
        int buyDay = 0;
        int sellDay = 0;
        int finalProfit = 0;

        for(int i = 0; i<values.length; i++) {
            
            for(int j = i+1; j<values.length; j++) {

                int buy = values[i];
                int sell = values[j];
                int profit = sell-buy;

                if(profit > finalProfit) {
                    buyDay = i+1;
                    sellDay = j+1;
                    finalProfit = profit;
                    continue;
                }
            }
        }

        int[] result = {buyDay, sellDay, finalProfit};
        return result;
    }

    public static int[] getValues(int[] changes) {
        ArrayList<Integer> values = new ArrayList<>();

        for(int change : changes) {
            int previous = 0;
            if(values.length > 0 ) {
                previous = values.get(values.size()-1);
            }
            values.add(change + previous);
        }
        int[] re = (int[]) values.toArray();
        return re;
    }

    public int[] getRandom(int n) {
        int[] changes = new int[n];
        return changes;
    }
}