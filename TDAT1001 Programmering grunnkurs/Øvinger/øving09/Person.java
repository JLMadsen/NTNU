// Øving 9 oppg 11.10.1

class Person{
	private  String firstname;
	private  String lastname;
	private  int birthyear;

	public Person(String firstname, String lastname, int birthyear){
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthyear = birthyear;
	}
	public Person(){
	}
	public String getFirstname(){
		return firstname;
	}
	public String getLastname(){
		return lastname;
	}
	
	public int getBirthyear(){
		return birthyear;
	} 
	public String toString(){
		return "Fornavn: "+ firstname +". Etternavn: "+ lastname +". Fødselsår: "+ birthyear;
	}
}