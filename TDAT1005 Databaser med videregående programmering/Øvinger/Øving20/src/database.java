
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class Database implements Serializable {

    private String url = "jdbc:mysql://mysql.stud.idi.ntnu.no:3306/jakoblm?user=jakoblm&password=YbBHG2Nc";
    private Connection connection;

    public Database() {
        try{
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
        }catch (SQLException e){
            System.out.println("Error in constructor");
            System.out.println(e.getMessage());
        }
    }
    public void closeConnection(){
        try{
            connection.close();
        }catch (SQLException e){
            System.out.println("Error in closing connection");
        }
    }
    public void getCost(int month, String filename){
        try{
            ArrayList<String> result = new ArrayList<>();

            // drop gammel view
            PreparedStatement drop = connection.prepareStatement("DROP VIEW IF EXISTS idk;");
            drop.execute();

            // lag ny view som henter ut lønn i en måned
            PreparedStatement view = connection.prepareStatement("CREATE VIEW idk AS SELECT prosj_id, kunde, (ant_timer*timefaktor*timelønn) AS cost FROM prosjekt JOIN prosjektarbeid USING(prosj_id) JOIN ansatt USING(ans_id) WHERE MONTH(dato) = ?;");
            view.setInt(1, month);
            view.execute();

            // samle all lønn i hvert prosjekt
            PreparedStatement select = connection.prepareStatement("SELECT prosj_id, kunde, sum(cost) AS cost FROM idk GROUP BY prosj_id;");
            ResultSet hours = select.executeQuery();

            // legg det til i result
            while(hours.next()){
                String prosj_id = hours.getString("prosj_id");
                String timer = "timer";
                String kunde = hours.getString("kunde");
                String cost = hours.getString("cost");
                result.add(prosj_id +"; "+ timer +"; "+ kunde +"; "+ cost);
            }

            // hent kostnader for produktene
            PreparedStatement cost2ElectricBoogaloo = connection.prepareStatement("SELECT prosj_id, tekst, kunde, beløp FROM prosjektkostnader JOIN prosjekt USING(prosj_id) WHERE MONTH(dato) = ?;");
            cost2ElectricBoogaloo.setInt(1, month);
            ResultSet products = cost2ElectricBoogaloo.executeQuery();

            // Set faktura_sendt
            PreparedStatement update1 = connection.prepareStatement("UPDATE prosjektarbeid SET faktura_sendt = CURDATE() WHERE MONTH(dato) = ?");
            PreparedStatement update2 = connection.prepareStatement("UPDATE prosjektkostnader SET faktura_sendt = CURDATE() WHERE MONTH(dato) = ?");
            update1.setInt(1, month);
            update2.setInt(1,month);
            update1.execute();
            update2.execute();

            // commit
            connection.commit();

            // legg til i resultat
            while(products.next()){
                String prosj_id2 = products.getString("prosj_id");
                String tekst = products.getString("tekst");
                String kunde = products.getString("kunde");
                String cost2 = products.getString("beløp");
                result.add(prosj_id2 +"; "+ tekst +"; "+ kunde +"; "+ cost2);
            }

            // skriv til fil og skriv ut
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            System.out.println("Skriver til fil: "+ filename);
            for(int i=0; i<result.size(); i++){
                writer.println(result.get(i));
                System.out.println(result.get(i));
            }
            writer.close();

        }catch (SQLException e){
            System.out.println("Error in getCost");
            System.out.println(e.getMessage());
            rollback();
        }
        catch (Exception e){
            System.out.println("Error in getCost");
            System.out.println(e.getMessage());
        }
    }
    public void rollback(){
        try{
            connection.rollback();
        }catch (SQLException e){
            System.out.println("Error in rollback");
        }
    }
}
