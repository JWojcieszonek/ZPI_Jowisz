package com.jowisz.jowisz.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jowisz.jowisz.DataAccessor;
import com.jowisz.jowisz.Zamowienie;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class OrdersController implements Initializable {

    @FXML
    private Button back;
   
    @FXML
    private Button add;
    
    @FXML
    private Label label;
    
    @FXML
    private TableView<Zamowienie> table;
    
    @FXML
    private TableColumn<Zamowienie, String> id;

    @FXML
    private TableColumn<Zamowienie, String> lastName;

    @FXML
    private TableColumn<Zamowienie, String> firstName;

    @FXML
    private TableColumn<Zamowienie, String> adress;

    @FXML
    private TableColumn<Zamowienie, String> phone;

    @FXML
    private TableColumn<Zamowienie, String> date;

    @FXML
    private TableColumn<Zamowienie, String> payment;
    
    private DataAccessor dataAccessor;

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
    		Parent root = FXMLLoader.load(getClass().getResource("/fxml/OrderForm.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
			label = (Label) root.lookup("#label");
			label.setText("Dodaj zamówienie");
			stage.setTitle("Dodaj zamówienie");
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
		try {
			dataAccessor =  new DataAccessor("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/sklepinternetowy");
			ObservableList<Zamowienie> zamowienieList = dataAccessor.getZamowienieList();
			System.out.println(zamowienieList.get(0).getNazw());
			id.setCellValueFactory(new PropertyValueFactory<Zamowienie,String>("idZam"));
			lastName.setCellValueFactory(new PropertyValueFactory<Zamowienie,String>("nazw"));
			firstName.setCellValueFactory(new PropertyValueFactory<Zamowienie,String>("imie"));
			adress.setCellValueFactory(new PropertyValueFactory<Zamowienie,String>("adres"));
			date.setCellValueFactory(new PropertyValueFactory<Zamowienie,String>("dataZam"));
			phone.setCellValueFactory(new PropertyValueFactory<Zamowienie,String>("tel"));
			payment.setCellValueFactory(new PropertyValueFactory<Zamowienie,String>("czyOplacono"));
			
			lastName.setCellFactory(TextFieldTableCell.forTableColumn());
			firstName.setCellFactory(TextFieldTableCell.forTableColumn());
			adress.setCellFactory(TextFieldTableCell.forTableColumn());
			date.setCellFactory(TextFieldTableCell.forTableColumn());
			phone.setCellFactory(TextFieldTableCell.forTableColumn());
			payment.setCellFactory(TextFieldTableCell.forTableColumn());		
			
			
			table.setItems(zamowienieList);
			table.setEditable(true);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void changeLastNameCellEvent(CellEditEvent<Zamowienie, String> edittedCell)
    {
        Zamowienie selected =  table.getSelectionModel().getSelectedItem();
        selected.setNazwisko(edittedCell.getNewValue().toString());
        try {
			dataAccessor.updateZamowienie(selected);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public void changeDateCellEvent(CellEditEvent<Zamowienie, String> edittedCell)
    {
        Zamowienie selected =  table.getSelectionModel().getSelectedItem();
        selected.setData(edittedCell.getNewValue().toString());
        try {
			dataAccessor.updateZamowienie(selected);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public void changeAdressCellEvent(CellEditEvent<Zamowienie, String> edittedCell)
    {
        Zamowienie selected =  table.getSelectionModel().getSelectedItem();
        selected.setAdres(edittedCell.getNewValue().toString());
        try {
			dataAccessor.updateZamowienie(selected);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public void changeFirstNameCellEvent(CellEditEvent<Zamowienie, String> edittedCell)
    {
        Zamowienie selected =  table.getSelectionModel().getSelectedItem();
        selected.setImie(edittedCell.getNewValue().toString());
        try {
			dataAccessor.updateZamowienie(selected);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public void changeTelCellEvent(CellEditEvent<Zamowienie, String> edittedCell)
    {
        Zamowienie selected =  table.getSelectionModel().getSelectedItem();
        selected.setTel(edittedCell.getNewValue().toString());
        try {
			dataAccessor.updateZamowienie(selected);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public void changePaymentCellEvent(CellEditEvent<Zamowienie, String> edittedCell)
    {
        Zamowienie selected =  table.getSelectionModel().getSelectedItem();
        System.out.println(edittedCell.getNewValue().toString());
        selected.setCzyOplacono(edittedCell.getNewValue().toString());
        try {
			dataAccessor.updateZamowienie(selected);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public void update()
	{
		Zamowienie updatedOrder = table.getSelectionModel().getSelectedItem();
		try {
			dataAccessor.updateZamowienie(updatedOrder);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
