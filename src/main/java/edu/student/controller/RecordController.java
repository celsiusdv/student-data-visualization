package edu.student.controller;

import edu.student.database.Connector;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class RecordController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            new Connector().getConnection();
            System.out.println("DB conected succesfully");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
