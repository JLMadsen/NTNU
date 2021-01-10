import static javax.swing.JOptionPane.*;
class Unicode {
	public static void main(String[] args) {
		String lest = showInputDialog("Tegn;");
	//	String lest = "aAzZ æ ø å Æ Ø Å";
		for(int i = 0; i < lest.length(); i++) {
			char tegn = lest.charAt(i);
			int j = tegn;

			System.out.println(tegn +" "+ j);
		}
	}
}