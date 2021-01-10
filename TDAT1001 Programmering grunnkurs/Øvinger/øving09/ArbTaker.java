// Øving 9 oppg 11.10.1

class ArbTaker{
	private final int workernum;
	private final int hireyear;
	private double monthsalary;
	private double taxpercentage;
	private Person person = new Person();

	private java.util.Random random = new java.util.Random();
	private java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
	private int currentyear = calendar.get(java.util.Calendar.YEAR);

	public ArbTaker(Person person, int workernum, int hireyear, double monthsalary, double taxpercentage){
		if(taxpercentage>1){
			taxpercentage = taxpercentage/100;
		}
		this.workernum = random.nextInt(1000);
		this.hireyear = hireyear;
		this.monthsalary = monthsalary;
		this.taxpercentage = taxpercentage;
		this.person = person;
	}
	public void setSalary(double b){
		monthsalary = b;
	}
	public void setTax(double c){ // setter prosent som f.eks 60, blir gjort om til 0.6
		if(c>1){
			c /= 100;
		}
		taxpercentage = c;
	}
	public int getWorkerNumber(){
		return workernum;
	}
	public int getHireYear(){
		return hireyear;
	}
	public double getSalary(){
		return monthsalary;
	}
	public double getTax(){
		return taxpercentage*100;
	}
	public double getTaxCut(){
		return monthsalary*taxpercentage;
	}
	public double getGrossYearSalary(){
		return monthsalary*12;
	}
	public double getYearSalary(){
		return monthsalary*12 - monthsalary*10*taxpercentage - monthsalary*(taxpercentage/2);
	}
	public String getName(){
		return person.getFirstname() +" "+ person.getLastname();
	}
	public int getAge(){
		return currentyear - person.getBirthyear();
	}	
	public int getTimeHired(){
		return currentyear - hireyear;
	}
	public boolean workedLonger(int d){
		boolean longer = false;
		int worked = currentyear - hireyear;
		if(d<=worked){
			longer = true;
		}
		return longer;
	}
	public String toString(){
		return person.toString() +". ArbeiderNr: "+ workernum +". Ansettelsesår: "+ hireyear +". Månedslønn: "+ monthsalary +". Skatteprosent: "+ taxpercentage;
	}
}