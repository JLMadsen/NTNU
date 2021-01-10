import java.util.*;
class KonferanseSenter {
    
    private ArrayList<Rom> rl = new ArrayList<Rom>();
    
    public KonferanseSenter(){
        init();
    }
    public boolean bookRoom(Tidspunkt from, Tidspunkt to, int people, Kunde customer){
        for(int i=0; i<rl.size(); i++){
            if(rl.get(i).book(from, to, people, customer)){
                return true;
            }
        }
        return false;
    }
    private void init(){
        Rom a = new Rom(1, 10);
        Rom b = new Rom(2, 15);
        Rom c = new Rom(3, 5);
        rl.add(a);
        rl.add(b);
        rl.add(c);
    }
    public int getRooms(){
        return rl.size();
    }
    public Rom getRoomI(int index){
        return rl.get(index);
    }
    public Rom getRoomN(int roomNumber){
        for(Rom rom : rl){
            if(rom.getRoomNumber() == roomNumber){
                return rom;
            }
        }
        return null;
    }
    public String toString(){
        String re = "";
        for(int i=0; i<rl.size(); i++){
            re += rl.get(i).toString() +" | "+ rl.get(i).resToString() +"\n";
        }
        return re;
    }
}