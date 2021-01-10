package team07.vikingwars.Model;

import java.util.ArrayList;

/**
 * Class that creates battle reports for the user
 */

public class BattleReport {
    private int attSpearmanLoss, defTown, attTown, defCavalryLoss, defArcherLoss, defSpearmanLoss, attCavalryLoss,
             attArcherLoss;

    private String attTownName, defTownName;
    private int[] localTowns;
    private ArrayList<Integer> attUnitsLoss = new ArrayList<Integer>();
    private ArrayList<Integer> defUnitsLoss = new ArrayList<Integer>();
    private ArrayList<String> units = new ArrayList<String>();

    /**
     * Constructor for battle report
     * @param attSpearmanLoss
     * @param attArcherLoss
     * @param attCavalryLoss
     * @param defSpearmanLoss
     * @param defArcherLoss
     * @param defCavalryLoss
     * @param attTown
     * @param defTown
     * @param attTownName
     * @param defTownName
     * @param localTowns
     */

    public BattleReport(int attSpearmanLoss, int attArcherLoss, int attCavalryLoss, int defSpearmanLoss,
            int defArcherLoss, int defCavalryLoss, int attTown, int defTown, String attTownName, String defTownName,
            int[] localTowns) {
        this.attSpearmanLoss = attSpearmanLoss;
        this.attArcherLoss = attArcherLoss;
        this.attCavalryLoss = attCavalryLoss;
        this.defSpearmanLoss = defSpearmanLoss;
        this.defArcherLoss = defArcherLoss;
        this.defCavalryLoss = defCavalryLoss;
        this.attTown = attTown;
        this.defTown = defTown;
        this.attTownName = attTownName;
        this.defTownName = defTownName;
        this.localTowns = localTowns;
    }

    /**
     * Collects data from a battle (namely the amount of troops killed), and creates
     * a report displaying this data
     * 
     * @return report
     */
    public String report() {
        String report = "";
        attUnitsLoss.add(attSpearmanLoss);
        attUnitsLoss.add(attArcherLoss);
        attUnitsLoss.add(attCavalryLoss);
        defUnitsLoss.add(defSpearmanLoss);
        defUnitsLoss.add(defArcherLoss);
        defUnitsLoss.add(defCavalryLoss);
        units.add("spearmen");
        units.add("archers");
        units.add("cavalries");
        for (int i = 0; i < localTowns.length; i++) {
            if (localTowns[i] == attTown) {
                report += "You attacked " + defTownName + "!\n\n";
                report += "While fighting, your army lost:\n";
                for (int a = 0; a < attUnitsLoss.size(); a++) {
                    report += attUnitsLoss.get(a) + " " + units.get(a) + "\n";
                }
                report += "\nYour opponent lost:\n";
                for (int b = 0; b < defUnitsLoss.size(); b++) {
                    report += defUnitsLoss.get(b) + " " + units.get(b) + "\n";
                }
            }

            if (localTowns[i] == defTown) {
                report += "Your town was attacked by " + attTownName + "!\n\n";
                report += "While fighting, your army lost:\n";
                for (int c = 0; c < defUnitsLoss.size(); c++) {
                    report += defUnitsLoss.get(c) + " " + units.get(c) + "\n";
                }
                report += "\nYour opponent lost:\n";
                for (int d = 0; d < attUnitsLoss.size(); d++) {
                    report += attUnitsLoss.get(d) + " " + units.get(d) + "\n";
                }
            }
        }
        return report;
    }
}