class Brok{ 
	private int teller;
	private int nevner; // Deklarerer variabler

	// Konstruktører
	public Brok(int teller, int nevner){                                          // Konstruktør for brøk objektet
		if(nevner == 0){                                                          // Hvis nevner er 0
			throw new IllegalArgumentException("Nevner kan ikke være null");      // Så vil objektet bli "kastet"
		}
		.teller = teller;    // Deklarerer at variablene i klassen og variablene i attributtene er de "samme"
		this.nevner = nevner;
		forkort();
		
	}
	public Brok(int teller){     // hvis den bare får teller vil den sette nevner til 1
		this.teller = teller;
		this.nevner = 1;
		forkort();
	}
	// Metoder
	public int getTeller(){      // Henter teller fra objekt
		return teller;
	}

	public int getNevner(){      // Henter nevner fra objekt
		return nevner;
	}

	public void add(Brok fraction){              // addisjon.  Metode som bruker teller & nevner fra b1 og henter teller & nevner fra b2 
		teller = (teller*fraction.getNevner())+(fraction.getTeller()*nevner);
		nevner = nevner*fraction.getNevner();
	}

	public void sub(Brok fraction){             // subtrakson
		teller = (teller*fraction.getNevner())-(fraction.getTeller()*nevner);
		nevner = nevner*fraction.getNevner();
	}

	public void mul(Brok fraction){             // multiplikasjon
		teller = (teller*fraction.getTeller());
		nevner = (nevner*fraction.getNevner());
	}

	public void div(Brok fraction){             // divisjon
		teller = teller*fraction.getNevner();
		nevner = nevner*fraction.getTeller();
	}

	public void forkort(){
		for(int j = 20; j >= 2; j--){                  // "for" løkke for forkorting
			if(teller % j == 0 && nevner % j == 0){    // if teller og nevner kan deles på samme tall
				teller = teller / j;                   // sett teller og nevner til å deles
				nevner = nevner / j;
			}
		}
	}
	public String toString(){                // to string
		return teller +"/"+ nevner;
	}
}
