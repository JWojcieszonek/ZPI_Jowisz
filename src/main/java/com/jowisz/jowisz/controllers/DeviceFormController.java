package com.jowisz.jowisz.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jowisz.jowisz.DataAccessor;
import com.jowisz.jowisz.Producent;
import com.jowisz.jowisz.Sprzet;
import com.jowisz.jowisz.Typ;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DeviceFormController implements Initializable{
	 	@FXML
	    private TextField name;

	    @FXML
	    private TextField price;

	    @FXML
	    private TextArea description;

	    @FXML
	    private TextField number;

	    @FXML
	    private Button chooseButton;

	    @FXML
	    private Label label;

	    @FXML
	    private Button cancel;

	    @FXML
	    private Button add;
	    @FXML
	    private ComboBox<Producent> comboProd;
	    @FXML
	    private ComboBox<Typ> comboTyp;
	    
	    private DataAccessor dataAccessor;
	    
	    @FXML
	    void cancel(ActionEvent event) {
				Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
				stage.close();
	    }
	    
	    @FXML
	    void save(ActionEvent event) {	    	
	    	Sprzet sprzet = new Sprzet(null, name.getText(),Double.valueOf(price.getText()), description.getText(),Integer.parseInt(number.getText()),Integer.parseInt(comboProd.getValue().getIdProd()),Integer.parseInt(comboTyp.getValue().getIdTyp()));
    		try {
				dataAccessor.addSprzet(sprzet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    		
    	   	
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

		stage.close();

	    }
		
		@FXML
	    void chooseFile(ActionEvent event) {
			final FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("All Images", "*.*"),
	                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
	                new FileChooser.ExtensionFilter("PNG", "*.png")
	            );
			 //File file = fileChooser.showOpenDialog(null);
			 //trzeba coś dalej zrobić z tym zdjeciem jakis podglad
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			try {
				dataAccessor = new DataAccessor("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/sklepinternetowy");
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ObservableList<Producent> producenci = FXCollections.observableArrayList();
			ObservableList<Typ> typy = FXCollections.observableArrayList();
			try {
				producenci=dataAccessor.getProducentObsList();
				comboProd.setItems(producenci);
				comboProd.setPromptText(producenci.get(0).getNazwaProd());
				typy = dataAccessor.getTypList();
				comboTyp.setItems(typy);
				comboTyp.setPromptText(typy.get(0).getNazwaTyp());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

}
