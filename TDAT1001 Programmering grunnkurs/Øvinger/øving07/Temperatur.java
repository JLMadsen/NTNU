// Øving 7 oppg 9.11.2

class Temperatur {
	private static double[][] temperatur;
	private java.util.Random random = new java.util.Random();

	public Temperatur(){
		temperatur = new double[30][24];
		for(int dag = 0; dag < temperatur.length; dag++){
			for(int time = 0; time < temperatur[0].length; time++){
				temperatur[dag][time] = random.nextDouble()*(dag);
			}
		}
	}
	public double getTemp(int dag, int time){
		double k = temperatur[dag][time];
		return k;
	}
	public static double[] getGjennomsnittDag(){
		double[] dagGj = new double[30];
		int tellerdag = 0;
		int tellertime = 0;
		for(tellerdag = 0; tellerdag < temperatur.length; tellerdag++){
			double gj = 0;
			for(tellertime = 0; tellertime < temperatur[0].length; tellertime++){
				gj += temperatur[tellerdag][tellertime];
			}
			dagGj[tellerdag] = gj/24;
		}
		return dagGj;
	}
	public double[] getGjennomsnittTime(){
		double[] timeGj = new double[24];
		int tellerdag = 0;
		int tellertime = 0;
		for(tellertime = 0; tellertime < temperatur[0].length; tellertime++){
			double gj = 0;
			for(tellerdag = 0; tellerdag < temperatur.length; tellerdag++){
				gj += temperatur[tellerdag][tellertime];
			}
			timeGj[tellertime] = gj/30;		}
		return timeGj;
	}
	public double getGjennomsnittManed(){
		int i = 0;
		double p = 0;
		double[] tab = Temperatur.getGjennomsnittDag();
		for(i = 0; i < 30; i++){
			p += tab[i];
		}
		return p/i;
	}
	public static int getTabellLengthDag(){ // dag array lengde (30)
		return temperatur.length;
	}
	public static int getTabellLengthTime(){ // time array lengde (24)
		return temperatur[0].length;
	}
	public int[] getGruppe(){
		int[] gruppe = new int[5];
		for(int o = 0; o < temperatur.length; o++){
			double p = Temperatur.getGjennomsnittDag()[o];
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