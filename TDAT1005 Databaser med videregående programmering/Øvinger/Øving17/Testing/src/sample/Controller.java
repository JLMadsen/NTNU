package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private String holder;

    @FXML
    private ListView lv1;
    @FXML
    private ListView lv2;
    @FXML
    private TextField input;
    @FXML
    private TextField value;
    @FXML
    private TextField name;
    @FXML
    private Label out;
    @FXML
    private Label warning;

    private Calculator cl = new Calculator();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] names = cl.getNames();
        for(int i=0; i<names.length; i++) {
            lv1.getItems().add(names[i]);
            lv2.getItems().add(names[i]);
        }
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            cl.setAmount(Integer.parseInt(newValue));
        });
        value.textProperty().addListener((observable, oldValue, newValue) -> {
            cl.setTempMod(Double.parseDouble(newValue));
        });
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            cl.setTempName(newValue);
            holder = newValue;
        });
        lv1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = (String) lv1.getSelectionModel().getSelectedItem();
                cl.setCurr1(cl.getCurr(name));
            }
        });
        lv2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = (String) lv2.getSelectionModel().getSelectedItem();
                cl.setCurr2(cl.getCurr(name));
            }
        });
    }
    public void updateInput(){
    }
    public void inputKeyPress(){
    }
    public void lv1click(){
    }
    public void lv2click(){
    }
    public void convert(){
        DecimalFormat df = new DecimalFormat("#0.00");
        double output = cl.calc();
        String formatted = df.format(output);
        out.setText(formatted +" "+ cl.getCurr2().getName());
    }
    public void newCurrency(){
        if(cl.newCurrency()){
            lv1.getItems().add(holder);
            lv2.getItems().add(holder);
            name.clear();
            value.clear();
        } else {
            warning.setText("Currency already in list.");
        }

    }

}
