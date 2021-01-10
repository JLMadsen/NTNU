class spiller{
	private final String navn;
	private int poeng;

	public spiller(String navn, int poeng){
		this.navn = navn;
		this.poeng = poeng;
	}
	public String getNavn(){
		return navn;
	}
	public int getPoeng(){
		return poeng;
	}
	public void setPoeng(int k){
		poeng=k;
	}
	public void kastTerning(int nypoeng){
		poeng=poeng+nypoeng;
	}
}
