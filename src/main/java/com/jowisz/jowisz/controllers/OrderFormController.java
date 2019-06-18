package com.jowisz.jowisz.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrderFormController {

	@FXML
	private TextField lastName;
	@FXML
	private TextField firstName;
	@FXML
	private TextField adress;
	@FXML
	private TextField phone;
    @FXML
    private Button cancel;
    @FXML
    private Button add;

    @FXML
    void cancel(ActionEvent event) {
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.close();
    }
 
    @FXML
    void save(ActionEvent event) {
    	//zapisanie do bazy po nacisnieciu przycisku "zapisz"
    }
}
