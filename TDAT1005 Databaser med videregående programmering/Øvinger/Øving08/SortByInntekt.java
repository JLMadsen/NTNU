import java.util.Comparator;

public class SortByInntekt implements Comparator<Tribune> {
    public int compare(Tribune a, Tribune b){
        if(a.finnInntekt() < b.finnInntekt()) {
            return -1;
        }else if (a.finnInntekt() == b.finnInntekt()) {
            return 0;
        }else {
            
        } return 1;
    }
}