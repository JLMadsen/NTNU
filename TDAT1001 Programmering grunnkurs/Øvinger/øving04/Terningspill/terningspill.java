import java.text.*;
class terningspill{
	public static void main(String[] args) {
		spiller a = new spiller("A", 0);
		spiller b = new spiller("B", 0);

		java.util.Random j = new java.util.Random();
		boolean atur = true;
		while(true){
			if(atur == true){
			while(atur == true){
				int i = j.nextInt(6)+1;
				a.kastTerning(i);
				int n = a.getPoeng();
				System.out.println("Spiller A har "+ n +" poeng.");
				if(i == 1){
					a.setPoeng(0);
					System.out.println("Spiller A rullet 1. Spiller B sin tur");
					atur = false;
				}
				if(a.getPoeng() >= 100){
					System.out.println("Spiller A har vunnet!");
					return;
				}
			}
		}else{
			while(atur == false){
				int i = j.nextInt(6)+1;
				b.kastTerning(i);
				int n = b.getPoeng();
				System.out.println("Spiller B har "+ n +" poeng.");
				if(i == 1){
					b.setPoeng(0);
					System.out.println("Spiller B rullet 1. Spiller A sin tur");
					atur = false;
				}
				if(b.getPoeng() >= 100){
					System.out.println("Spiller B har vunnet!");
					return;
					}
				}
			}
		}
	}
}