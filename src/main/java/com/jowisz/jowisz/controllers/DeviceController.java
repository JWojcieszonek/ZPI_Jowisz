package com.jowisz.jowisz.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import com.jowisz.jowisz.DataAccessor;
import com.jowisz.jowisz.Producent;
import com.jowisz.jowisz.Sprzet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DeviceController implements Initializable{

	 @FXML
	    private Button back;
	    @FXML
	    private Button add;
	    @FXML
	    private Label label;
	    @FXML
		private GridPane grid;
	    
	    
	    private DataAccessor dataAccessor ;
	    
	    
	    

	    @FXML
	    void goBack(ActionEvent event) {
	    	try {
				Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Stock.fxml"));
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
	            label = (Label) root.lookup("#label");
				label.setText("Dodaj sprzęt");
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
			
			try {
				dataAccessor = new DataAccessor("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/sklepinternetowy");

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				System.out.println("Device typd id:" + StockController.parameters);
				//System.out.println("Z labela2: "+label.getText());
				List<Producent> producentList = new ArrayList<Producent>();
				
				try {
					producentList = dataAccessor.getProducentListWithType(Integer.parseInt(StockController.parameters));
					
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				fillGrid(producentList);
				Collections.sort(producentList);
				
		}
	    
	    public void fillGrid(List<Producent> producentList)
	    {
	    	
	    	for(int i=0;i<producentList.size();i++)
	        {
	            final String text = producentList.get(i).getNazwaProd();
	            final String id = producentList.get(i).getIdProd();
	            Label producer = new Label(text);
	            TableView<Sprzet> device = new TableView<Sprzet>();
	            TableColumn<Sprzet,String> idCol = new TableColumn<Sprzet,String>("ID");
	            TableColumn<Sprzet,String> nazwaCol = new TableColumn<Sprzet,String>("Nazwa");
	            TableColumn<Sprzet,String> opisCol = new TableColumn<Sprzet,String>("Opis");
	            TableColumn<Sprzet,String> cenaCol = new TableColumn<Sprzet,String>("Cena");
	            TableColumn<Sprzet,String>  sztukiCol = new TableColumn<Sprzet,String>("Liczba sztuk");
	            ObservableList<Sprzet> sprzetList = FXCollections.observableArrayList();
	            try {
					sprzetList=dataAccessor.getSprzetList(producentList.get(i), Integer.parseInt(StockController.parameters));
										
					idCol.setCellValueFactory(new PropertyValueFactory<Sprzet,String>("idSpr"));
		            nazwaCol.setCellValueFactory(new PropertyValueFactory<Sprzet,String>("nazwa"));
		            opisCol.setCellValueFactory(new PropertyValueFactory<Sprzet,String>("opis"));
		            cenaCol.setCellValueFactory(new PropertyValueFactory<Sprzet,String>("cena"));
		            sztukiCol.setCellValueFactory(new PropertyValueFactory<Sprzet,String>("liczbaSzt"));
		            
		            idCol.setMinWidth(50);
		            nazwaCol.setMinWidth(200);
		            opisCol.setMinWidth(348);
		            cenaCol.setMinWidth(100);
		            sztukiCol.setMinWidth(100);
		                      
		           
		            nazwaCol.setCellFactory(TextFieldTableCell.forTableColumn());
		            nazwaCol.setOnEditCommit(t->t.getRowValue().setNazwa(t.getNewValue()));
		  
		            opisCol.setCellFactory(TextFieldTableCell.forTableColumn());
		            opisCol.setOnEditCommit(t->t.getRowValue().setOpis(t.getNewValue()));
		           
		           //cenaCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		            //cenaCol.setOnEditCommit(t->t.getRowValue().setCena(Double.parseDouble(t.getNewValue())));
		            //cenaCol.setCellFactory(TextFieldTableCell.<Sprzet,Double>forTableColumn(new DoubleStringConverter()));
		           // sztukiCol.setCellFactory(TextFieldTableCell.forTableColumn());
		            
		            
		            
		            device.getColumns().addAll(idCol,nazwaCol,opisCol,cenaCol,sztukiCol);
		            device.setItems(sprzetList);
		           // device.getItems().get(0).getNazwa();
		            device.setEditable(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            
	            producer.setAlignment(Pos.CENTER);
	            producer.setFont(new Font("System", 25));
	    
	           
	           producer.setOnMouseClicked((event)->
	           {
	        	   //TODO tu trzeba dodać otwarcie formularza i załadować do niego obecne dane i updatować w bazie po zapisaniu
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
	           
	           GridPane glue =new GridPane();
	           glue.setAlignment(Pos.CENTER);
	           glue.add(producer, 0, 0);
	           glue.add(device, 0, 1);
	           grid.add(glue, 0, i);
	                    
	           
	        }
	    }
		
}
