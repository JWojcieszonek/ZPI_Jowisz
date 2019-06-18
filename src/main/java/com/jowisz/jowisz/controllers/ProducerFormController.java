package com.jowisz.jowisz.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jowisz.jowisz.DataAccessor;
import com.jowisz.jowisz.Producent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ProducerFormController implements Initializable{

    @FXML
    private TextField name;
    @FXML
    private Label label;
    @FXML
    private Button cancel;
    @FXML
    private Button saveButton;
    @FXML
    private Button delete;

    @FXML
    private Button add;
    private DataAccessor dataAccessor ;
    private String id;
   
    public void setId(String id)
    {
    	this.id=id;
    }

    @FXML
    void cancel(ActionEvent event) {
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
    }

    @FXML
    void save(ActionEvent event) throws SQLException 
    {
    	
       	
    	if(label.getText()=="Edytuj producenta")
    	{
    		System.out.println("Zaktualizowano producenta na: "+name.getText());
    		Producent producent = new Producent(id, name.getText());
    		dataAccessor.updateProducent(producent);
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Producers.fxml"));
       		ProducersController controller =loader.getController();		
       		       		
       		List<Producent> producentList= controller.getProducentList();
       		for(int i=0;i<producentList.size();i++)
       		{
       			if(producentList.get(i).getNazwaProd()==name.getText())
       				{
       				producentList.get(i).setNazwaProd(name.getText());
       				controller.setProducentList(producentList);
       				}
       		}
       		
    	}
    	else
    	{
    		delete.setVisible(false);
    		Producent producent = new Producent("NULL", name.getText());
    		dataAccessor.addProducent(producent);
    	}
    		
    	   	
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
		
    }
    
    @FXML
    void delete(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Usunięcie producenta");
    	alert.setHeaderText("");
    	alert.setContentText("Czy na pewno chcesz usunąć producenta " + name.getPromptText());
    	System.out.println(name.getText());
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	   try {
    		   FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Producers.fxml"));
    		   ProducersController controller =loader.getController();	
          		List<Producent> producentList= controller.getProducentList();
          		for(int i=0;i<producentList.size();i++)
          		{
          			if(producentList.get(i).getNazwaProd().equals(name.getPromptText()))
          				{
          				System.out.println("Usuwanie prompt:"+name.getPromptText() +" prod:" +producentList.get(i).getNazwaProd());
          				dataAccessor.deleteProducent(producentList.get(i).getIdProd());
          				producentList.remove(i);
          				controller.setProducentList(producentList);
          				cancel(event);
          				}
          		}
          		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	} else {
    	    // ... user chose CANCEL or closed the dialog
    	}
    	
    }

	@Override
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
	}
}

