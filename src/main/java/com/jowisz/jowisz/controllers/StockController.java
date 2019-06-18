package com.jowisz.jowisz.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StockController extends Application implements Initializable 
{
	
	@FXML
	private GridPane grid;
	@FXML
    private Button back;
   public static String parameters;

    @FXML
    void goBack(ActionEvent event) 
    {
    	try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Start.fxml"));
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.getScene().setRoot(parent);
			parent.requestFocus();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void openForm(ActionEvent event) {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/fxml/DeviceForm.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
           
			stage.setTitle("Dodaj sprzęt");
		    stage.setScene(scene);
		    stage.show();
		    root.requestFocus();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String[] list = {"Laptop","Klawiatura","Mysz"};
		for(int i=0;i<list.length;i++)
		{
			final String id = String.valueOf(i+1);
			
			final String text = list[i];
			Button typ = new Button(list[i]);

			typ.setPrefWidth(400.0);
			typ.setMinHeight(100.0);
			typ.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			    	try {
			    		parameters=String.valueOf(id);
			    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Device.fxml"));
			    		Parent parent= loader.load();
						Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
						
						Label label = (Label)parent.lookup("#label");
						label.setText(text);

						stage.getScene().setRoot(parent);
						parent.requestFocus();
						
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
			    }
			});
			grid.add(typ,0,i);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		parameters=getParameters().getNamed().toString();
		
		
	}
}
