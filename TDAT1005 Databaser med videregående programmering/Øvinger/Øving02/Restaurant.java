import java.util.GregorianCalendar;

class Restaurant {

    private String name;
    private final int anno;
    private Bord bord;
    private GregorianCalendar calendar = new GregorianCalendar();

    public Restaurant(String name, int anno, int tables){
        this.name = name;
        this.anno = anno;
        bord = new Bord(tables);
    }
    public String getName(){ return name; }
    public void rename(String newname){ name = newname; }
    public int getAnno(){ return anno; }
    public int getOld(){ return (calendar.get(calendar.YEAR)-anno); }
    public int getFree(){ return bord.getFree(); }
    public int getTaken(){ return bord.getTaken(); }
    public void clearTables(int[] list){ bord.clearTable(list); }
    public int[] getOrderedBy(String customer){ return bord.getOrderedBy(customer); }

    public boolean orderTable(int n, String customer){
        if(bord.orderTable(n, customer)){
            return true;
        } else {
            return false;
        }
    }
    public String toString(){
        return bord.toString();
    }
}