// Øving 8 oppg 8.16.3

class TekstBehandling {
	public String text;

	public TekstBehandling(String text){
		this.text = text;
	}
	public int getAntallOrd(){
		String[] words = text.split(" ");
		return words.length;
	}
	public double getGjennomsnittLengde(){ // Henter gjennomsnittlig lengde på ord
		double amount = 0;
		String[] words = text.split("[ ]"); // Array med ord
		for(int i = 0; i < words.length; i++){
			amount += words[i].replace(".", "").length(); // Fjerner . fra lengden, legger til lengden på ordet til amount
		}
		return amount/words.length; // Deler den totale ordlengden på antall ord
	}
	public double getGjennomsnittPeriode(){ // Henter gjennomsnittlig antall ord per setning
		double amount = 0;
		String[] sentence = text.split("[.:!?]"); // Array med setninger, splitter setningene på . : ! ?
		for(int i = 0; i < sentence.length; i++) {
			amount += sentence[i].trim().split(" ").length; // Trimmer setningene for å fjerne mellomrom på start og slutt, så slitter det opp i ord
		}
		return amount/sentence.length; // Deler antall ord på antall setninger
	}
	public String byttOrd(String word1, String word2){ // Bytter ut word1 med word2
	word1 = word1.toLowerCase();
	word2 = word2.toLowerCase();
	return text.toLowerCase().replace(word1, word2);
	}
	public String getTekst(){
		return text;
	}
	public String getStore(){
		return text.toUpperCase();
	}
}
/*
		String replace1 = word1.toLowerCase() +" ";
		String replace2 = word1.toLowerCase() +".";
		word2 = word2.toLowerCase();
		return text.toLowerCase().replace(replace1, word2 +" ").replace(replace2, word2 +"."); */