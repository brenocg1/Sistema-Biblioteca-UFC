/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author brenocg
 */
public class Biblioteca extends Application {
    
    //retorna o stage inicial
    public static Stage primaryStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        primaryStage = stage;
                                                             //pasta com os arquivos .fxml(telas)
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        
        stage.initStyle(StageStyle.DECORATED);
        
        stage.setTitle("Login no Banco de Dados");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
