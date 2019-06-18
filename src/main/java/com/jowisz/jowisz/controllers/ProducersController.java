package com.jowisz.jowisz.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import com.jowisz.jowisz.DataAccessor;
import com.jowisz.jowisz.Producent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ProducersController implements Initializable{

    @FXML
    private Button back;
    @FXML
    private Button add;
    @FXML
    private Label label;
    @FXML
	private GridPane grid;
    private DataAccessor dataAccessor ;
    private List<Producent> producentList ; 

    public List<Producent> getProducentList() {
		return producentList;
	}
	public void setProducentList(List<Producent> producentList) {
		this.producentList = producentList;
	}
	@FXML
    void goBack(ActionEvent event) {
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
    		Parent root = FXMLLoader.load(getClass().getResource("/fxml/ProducerForm.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            label = (Label) root.lookup("#label");
			label.setText("Dodaj producenta");
			stage.setTitle("Dodaj producenta");
		    stage.setScene(scene);
		    stage.show();
		    root.requestFocus();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void fillGrid(List<Producent> producentList)
    {
    	
    	for(int i=0;i<producentList.size();i++)
        {
            final String text = producentList.get(i).getNazwaProd();
            final String id = producentList.get(i).getIdProd();
            TextField producer = new TextField(text);

            producer.setPrefWidth(390.0);
            producer.setMinWidth(390.0);
            producer.setMaxWidth(390.0);
            producer.setMinHeight(60.0);
            producer.setCursor(Cursor.HAND);
            producer.setAlignment(Pos.CENTER);
            producer.setEditable(false);
            //grid.setPadding(new Insets(10,0,10,0));
            grid.setMargin(producer, new Insets(0,0,10,0));
            //grid.setMinHeight(60.0);
            //grid.setPrefHeight(20.0);
            //grid.setGridLinesVisible(true);
           
           producer.setOnMouseClicked((event)->
           {
        	   try {
        		   FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProducerForm.fxml"));
           		Parent root = loader.load();
           		ProducerFormController controller =loader.getController();
           	
           		controller.setId(id);
                   Stage stage = new Stage();
                   Scene scene = new Scene(root);
                   label = (Label) root.lookup("#label");
       			label.setText("Edytuj producenta");
       			TextField name = (TextField) root.lookup("#name");
       			
       			//name.setText(text);
       			name.setPromptText(text);
       			//name.requestFocus();
       			//name.selectAll();
       			stage.setTitle("Edytuj producenta");
       		    stage.setScene(scene);
       		    stage.show();
       		    root.requestFocus();
       			
       		} catch (IOException e) {
       			// TODO Auto-generated catch block
       			e.printStackTrace();
       		}
           });
           grid.add(producer,0,i);
        }
    }
    public void initialize(URL location, ResourceBundle resources) {
       
        try {

        	dataAccessor = new DataAccessor("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/sklepinternetowy");
			

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            producentList = dataAccessor.getProducentList();
           
           fillGrid(producentList); 
           
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }


}
