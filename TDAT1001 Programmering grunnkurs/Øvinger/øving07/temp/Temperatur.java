class Temperatur {
	private static double[][] temperatur;
	private java.util.Random random = new java.util.Random();
	public Temperatur(){
		temperatur = new double[30][24];
		for(int dag = 0; dag < temperatur.length; dag++){
			for(int time = 0; time < temperatur[0].length; time++){
				temperatur[dag][time] = random.nextDouble()*(dag+1);
			}
		}
	}
	public static double[] getGjennomsnittDag(){
		double[] dagGj = new double[30];
		int i = 0;
		int j = 0;
		int l = 0;
		double gjennomsnitt = 0;
		for(i = 0; i < temperatur.length; i++){
			for(j = 0; j < temperatur[0].length; j++){
				l += temperatur[i][j];
			}
			gjennomsnitt = l/temperatur[0].length;
			dagGj[i] = gjennomsnitt;
		}
		return dagGj;
	}
	public double[] getGjennomsnittTime(){
		double[] timeGj = new double[24];
		int i = 0;
		int j = 0;
		int l = 0;
		double gjennomsnitt = 0;
		for(i = 0; i < temperatur[0].length; i++){
			for(j = 0; j < temperatur.length; j++){
				l += temperatur[j][i];
			}
			gjennomsnitt = l/temperatur.length;
			timeGj[i] = gjennomsnitt;
		}
		return timeGj;
	}
	public double getGjennomsnittManed(){
		int i = 0;
		int p = 0;
		int gjennomsnitt;
		double[] tab = Temperatur.getGjennomsnittDag();
		for(i = 0; i < 30; i++){
			p += tab[i];
		}
		gjennomsnitt = p/i;
		return gjennomsnitt;
	}
	public static int getTabellLength1(){
		return temperatur.length;
	}
	public static int getTabellLength2(){
		return temperatur[0].length;
	}
	public int[] getGruppe(){
		int[] gruppe = new int[5];
		double p = 0;
		for(int o = 0; o < temperatur.length; o++){
			p = Temperatur.getGjennomsnittDag()[o];
			if(p < -5){  				 // mindre enn -5
				gruppe[0]++;
			}else if(p == -5 || p < 0){	 // mellom -5 og 0
				gruppe[1]++;
			}else if(p == 0 || p < 5){     // mellom 0 og 5
				gruppe[2]++;
			}else if(p == 5 || p <= 10){ // mellom 5 og 10
				gruppe[3]++;
			}else if(p > 10){            // over 10
				gruppe[4]++;
			}
		}	
		return gruppe;
	}
}

