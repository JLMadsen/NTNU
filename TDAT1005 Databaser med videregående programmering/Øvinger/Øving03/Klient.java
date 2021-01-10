import static javax.swing.JOptionPane.*;

class GodkjenningBGS {
    public final String NY_STUDENT = "Ny student";
    public final String AVSLUTT = "Avslutt";
    private String[] muligeValg = {NY_STUDENT, AVSLUTT};  // første gang, ingen studenter registrert

/**/private OppgaveOversiktList oversikt;
/**/public GodkjenningBGS(OppgaveOversiktList oversikt) {
        this.oversikt = oversikt;
    }
    public String lesValg() {
        int antStud = oversikt.getNumStud();
        String valg = (String) showInputDialog(null, "Velg fra listen, " + antStud + " studenter:",  "Godkjente oppgaver",
        DEFAULT_OPTION, null, muligeValg, muligeValg[0]);
        if (AVSLUTT.equals(valg)) {
            valg = null;
        }
        return valg;
    }
    public void utforValgtOppgave(String valg) {
        if (valg != null && !valg.equals(AVSLUTT)) {
            if (valg.equals(NY_STUDENT)) {
                registrerNyStudent();
            } else {
                registrerOppgaver(valg);  // valg er navnet til studenten
            }
        }
    }
    private void registrerNyStudent() {
        String navnNyStud = null;
        while(navnNyStud == null){
            navnNyStud = showInputDialog("Oppgi navn: ");
        }
        navnNyStud = navnNyStud.trim();

        if (oversikt.newStudent(navnNyStud)) {
            showMessageDialog(null, navnNyStud + " er registrert.");
            String[] alleNavn = oversikt.finnAlleNavn();
            String[] nyMuligeValg = new String[alleNavn.length + 2];
            for (int i = 0; i < alleNavn.length; i++) {
                nyMuligeValg[i] = alleNavn[i];
            }
            nyMuligeValg[alleNavn.length] = NY_STUDENT;
            nyMuligeValg[alleNavn.length + 1] = AVSLUTT;
            muligeValg = nyMuligeValg;
        } else  {
            showMessageDialog(null, navnNyStud + " er allerede registrert.");
        }
    }
    private void registrerOppgaver(String studNavn) {
        String melding = "Oppgi antall nye oppgaver som skal godkjennes for " + studNavn +": ";
        int antOppgOkning = 0;
        boolean registrert = false;
        do { // gjentar inntil registrering aksepteres av objektet oversikt
            try {
                antOppgOkning = lesHeltall(melding);
                oversikt.increaseTask(antOppgOkning, studNavn);  // kan ikke returnere false, pga navn alltid gyldig
                registrert = true; // kommer hit bare dersom exception ikke blir kastet
            } catch (IllegalArgumentException e) {  // kommer hit hvis studenter får negativt antall oppgaver
                melding = "Du skrev " + antOppgOkning + ". \nIkke godkjent økning for " + studNavn + ". Prøv igjen: ";
            }
        } while (!registrert);

        melding = "Totalt antall oppgaver registrert på " + studNavn + " er " + oversikt.getTaskStud(studNavn) + ".";
        showMessageDialog(null, melding);
    }
    private int lesHeltall(String melding) {
        int tall = 0;
        boolean ok = false;
        do {
            String tallLest = showInputDialog(melding);
            try {
                tall = Integer.parseInt(tallLest);
                ok = true;
            } catch (Exception e) {
            showMessageDialog(null, "Kan ikke tolke det du skrev som tall. Prøv igjen. ");
        }
    } while (!ok);
    return tall;
    }
}
class Klient {
    public static void main(String[] args) {

/**/    OppgaveOversiktList oversikt = new OppgaveOversiktList();
        GodkjenningBGS bgs = new GodkjenningBGS(oversikt);

        String valg = bgs.lesValg();
        while (valg != null) {
            bgs.utforValgtOppgave(valg);
            valg = bgs.lesValg();
        }
        System.out.println("\nHer kommer informasjon om alle studentene: ");
        System.out.println(oversikt);
        
    }
}