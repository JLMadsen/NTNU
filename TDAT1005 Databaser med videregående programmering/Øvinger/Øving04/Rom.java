import java.util.*;
class Rom {
    private int roomNumber;
    private int seats;
    private ArrayList<Reservasjon> res = new ArrayList<Reservasjon>();

    public Rom(int roomNumber, int seats){
        this.roomNumber = roomNumber;
        this.seats = seats;
    }
    public int getRoomNumber(){
        return roomNumber;
    }
    public boolean book(Tidspunkt from, Tidspunkt to, int people, Kunde customer){
        if(seats<people){
            return false;
        }
        if(res.size()==0){
            res.add(new Reservasjon(from, to, customer, people));
            return true;
        } else {
            for(int i=0; i<res.size(); i++){
                Tidspunkt tempFrom = res.get(i).getFraTid();
                Tidspunkt tempTo = res.get(i).getTilTid();

// ___from1---to1___from2---to2___from3---to3___
//            to1 < from2 < to2
//                  from2 < to2 < from3               

                if(from.compareTo(tempTo) != to.compareTo(tempTo)){
                    return false;
                }
                if(from.compareTo(tempFrom) != to.compareTo(tempFrom)){
                    return false;
                }
            }
            res.add(new Reservasjon(from, to, customer, people));
            return true;
        }
        //return false;
    }
    public void clearAll(){
        res.clear();
    }
    public String toString(){
        return "Rom nr. "+ roomNumber +". Antall plasser: "+ seats;
    }
    public String resToString(){
        String re = "";
        for(int i=0; i<res.size(); i++){
            re += "\n"+ res.get(i).toString() +"\n";
        }
        return re;
    }
    //____________________________________________________________________________ Test
    public static void main(String[] args){

        Rom t = new Rom(3, 10);

        Tidspunkt q = new Tidspunkt(200903191500L);
        Tidspunkt w = new Tidspunkt(200903191700L);
        Tidspunkt e = new Tidspunkt(200903191600L);

        Kunde r = new Kunde("Per", "911");

        System.out.println(t.book(q, w, 30, r)); // false    for lite rom
        System.out.println(t.book(q, e, 4, r)); // true      ingen andre reservasjoner
        System.out.println(t.book(q, w, 4, r)); // false     tiden kræsjer

    }
}
