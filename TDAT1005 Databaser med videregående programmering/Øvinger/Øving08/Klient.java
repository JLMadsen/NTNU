import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Klient{
    public static void main(String[] args) throws Exception{
        //                 navn, pris, kapasitet
        Tribune a = new Staa("A", 100, 200);
        System.out.println("Printer Tribune A:\n"+ a.toString());
        System.out.println("Printer Billeter:\n"+ Arrays.toString(a.kjopBilletter(4)));
        System.out.println(Arrays.toString(a.kjopBilletter(15)));
        System.out.println(a.toString());

        Tribune b = new Sitte("B", 100, 10, 20);
        System.out.println("\nPrinter Tribune B:\n "+ b.toString());
        System.out.println("Printer Billeter:\n"+ Arrays.toString(b.kjopBilletter(7)));
        System.out.println(Arrays.toString(b.kjopBilletter(16)));
        System.out.println(Arrays.toString(b.kjopBilletter(30)));
        System.out.println(b.toString());

        Tribune c = new VIP("c", 10000, 20, 10);
        System.out.println("\nPrinter tribune C:\n"+ c.toString());
        String[] navn = {"Per", "Arne", "Banan", "Marius"};
        System.out.println("Printer Billeter:\n"+ Arrays.toString(c.kjopBilletter(navn)));
        System.out.println(c.toString());

        Tribune[] tribuner = new Tribune[3];
        tribuner[0] = a;
        tribuner[1] = b;
        tribuner[2] = c;

        System.out.println("\nUsortert:");
        for(Tribune t : tribuner){
            System.out.println(t.toString());
        }

        Arrays.sort(tribuner, new SortByInntekt());

        System.out.println("\nSortert:");
        for(Tribune t : tribuner){
            System.out.println(t.toString());
        }

        try{
            FileOutputStream fos = new FileOutputStream("idk.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            System.out.println("\nSkriver til fil . . .");
            oos.writeObject(tribuner);

            fos.close();
            oos.close();

            FileInputStream fis = new FileInputStream("idk.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            System.out.println("\nLeser fra fil . . .");
            Tribune[] tribune2 = (Tribune[]) ois.readObject();
            for(Tribune y : tribune2){
                System.out.println(y.toString());
            }

            fis.close();
            ois.close();

        }
        catch(Exception e){
            System.out.println("Funker ikke, fiks pls");
        }
    }
}