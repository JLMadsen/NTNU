import java.util.Arrays;
class copyOf{
	public static void main(String[] args) {
		int[] a = {1, 2, 3};
    	// make a one bigger
    	a = Arrays.copyOf(a, a.length + 1);
    	for (int i : a){
        	System.out.println(i);
    	}
	}
}