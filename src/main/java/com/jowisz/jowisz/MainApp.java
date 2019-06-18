package com.jowisz.jowisz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
	  public DataAccessor dataAccessor ;
	  

    @Override
    public void start(Stage stage) throws Exception {
    	
    	
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Start.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Jowisz");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setMaximized(true);
        root.requestFocus();
        
      
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
