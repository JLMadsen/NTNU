import static java.io.*;

class Ordbok implements java.io.Serializable{

	private String ordbokNavn;
	private Ord[] ordbok;
	private int antallReg;
	private final int MAKS_ANTALL_ORD = 10;
	private String filnavn = "ordliste.ser";

	public Ordbok(String ordbokNavn, int b){
		this.ordbokNavn = ordbokNavn;

		if(lesOrdbokFraFil(filnavn)){

			antallReg = ordbok.length;

		} else {

			ordbok = new Ord[MAKS_ANTALL_ORD];
			antallReg = 0;
		}
	}
	public boolean regNyttOrd(Ord ord){

		if(ord == null){

			return false;
		}

		if(antallReg == MAKS_ANTALL_ORD){

			return false;

		} else {

			ordbok[antallReg] = ord;
			return true;
		}
	}
	public boolean leggTilDefinisjon(String ord, String definisjon){

		for(int i=0; i<antallReg; i++){

			if(ord.equals(ordbok[i].getNavn())){
				if(ordbok[i].leggTilDefinisjon(definisjon)){
					return true;
				}
			}
		}
		return false;
	}
	public Ord[] sorter(){
		Ord[] ord = ordbok;

		for(int start=0; start<antallReg; start++){
			int high;
			for(int j=(start+1); j<antallReg; j++){
				if(ord[start].getNavn()<ord[j].getNavn()){
					high = j;
				}
			}
			Ord holder = ord[high];
			ord[high] = ord[start];
			ord[start] = holder;
		}
		return ord;
	}
	public Ord getOrd(String sokeStreng){

		for(int i=0; i<antallReg; i++){

			if(sokeStreng.equals(ordbok[i].getNavn())){

				return ordbok[i];

			} else {

				return null;
			}
		}
	}
	public boolean lesOrdbokFraFil(String filnavn){

		try{

			FileInputStream fis = new FileInputStream(filnavn);
			ObjectInputStream ois = new ObjectInputStream(fis);

			ordbok = (Ord[]) ois.readObject();

			ois.close();

		} catch(Exception e){ System.out.println(e); }

		if(ordbok.length == 0){
			return false;
		} else {
			return true;
		}
	}
}