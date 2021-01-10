import java.text.*;
class terningspill {
	public static void main(String[] args) {
		spiller a = new spiller("A", 0, 0, 0);
		spiller b = new spiller("B", 0, 0, 0);

		java.util.Random j = new java.util.Random();
		boolean turn = true;  
		int runde = 0;
		while(true) {	
			if(turn == true) {
				while(turn == true) {
					runde++;
					int i = j.nextInt(6)+1;
					a.kastTerning(i);
					int n = a.getPoeng();
					System.out.println("Kast: "+ runde +". Spiller "+ a.getNavn() +" rullet "+ i +" og har "+ n +" poeng.");
					if(i == 1) {
						if(a.getPoeng()>=a.getHighscore()){
							a.setHighscore(n-1);
							a.setHighrunde(runde);
						}
						a.setPoeng(0);
						System.out.println("Spiller "+ a.getNavn() +" rullet 1. Spiller B sin tur");
						System.out.println("-----------------------------------------------");
						turn = false;
					}
					if(a.getPoeng() == 100) {
						System.out.println("Spiller "+ a.getNavn() +" har vunnet! \nSpiller B fikk "+ b.getHighscore() +" på kast "+ b.getHighrunde());
						System.out.println(a.toString() +"\n"+ b.toString());
						return;
					}
					if(a.getPoeng() >= 101) {
						a.kastTerning(-i);
					}
				}
			} else {
				while(turn == false) {
					runde++;
					int i = j.nextInt(6)+1;
					b.kastTerning(i);
					int n = b.getPoeng();
					System.out.println("Kast: "+ runde +". Spiller "+ b.getNavn() +" rullet "+ i +" og har "+ n +" poeng.");
					if(i == 1) {
						if(b.getPoeng()>=b.getHighscore()) {
							b.setHighscore(n-1);
							b.setHighrunde(runde);
						}
						b.setPoeng(0);
						System.out.println("Spiller "+ b.getNavn() +" rullet 1. Spiller A sin tur");
						System.out.println("-----------------------------------------------");
						turn = true;
					}
					if(b.getPoeng() == 100) {
						System.out.println("Spiller "+ b.getNavn() +" har vunnet! \nSpiller A fikk "+ a.getHighscore() +" på kast "+ a.getHighrunde());
						System.out.println(a.toString() +"\n"+ b.toString());
						return;
					}
					if(b.getPoeng() >= 101) {
						b.kastTerning(-i);
					}
				}
			}
		}
	}
}