package com.jowisz.jowisz.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController implements Initializable {
    
    @FXML
    private Button stock;

    @FXML
    private Button orders;

    @FXML
    private Button producers;
    

    @FXML
    void goToOrders(ActionEvent event) throws ClassNotFoundException, SQLException {
    	try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Orders.fxml"));
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.getScene().setRoot(parent);

			parent.requestFocus();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void goToProducers(ActionEvent event) {
    	try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Producers.fxml"));
			//Scene producers = new Scene(parent);
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.getScene().setRoot(parent);
			parent.requestFocus();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void goToStock(ActionEvent event) throws ClassNotFoundException, SQLException {
    	try {
    		FXMLLoader loader =new FXMLLoader(getClass().getResource("/fxml/Stock.fxml"));
			Parent parent = loader.load();
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.getScene().setRoot(parent);
			parent.requestFocus();
						
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    }    
}