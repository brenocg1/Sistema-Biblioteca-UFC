/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeAdminController implements Initializable {

    @FXML
    private AnchorPane loginBtn;

    @FXML
    private JFXTextField usernameTF;

    @FXML
    private JFXPasswordField passwordTF;

    @FXML
    private JFXButton login;

    @FXML
    private JFXCheckBox cbConect;

    @FXML
    private JFXButton sair;

    @FXML
    private JFXButton livros1;

    @FXML
    private JFXButton autores1;

    @FXML
    private JFXButton usuarios1;

    @FXML
    private JFXButton curso1;

    @FXML
    private JFXButton categoria1;

    @FXML
    private JFXButton livros2;

    @FXML
    private JFXButton autores2;

    @FXML
    private JFXButton usuarios2;

    @FXML
    private JFXButton curso2;

    @FXML
    private JFXButton categoria2;

    @FXML
    private JFXButton livros3;

    @FXML
    private JFXButton autores3;

    @FXML
    private JFXButton usuarios3;

    @FXML
    private JFXButton curso3;

    @FXML
    private JFXButton categoria3;

    @FXML
    private VBox pnl_scroll;
    
    @FXML
    void loginBtn(ActionEvent event){
    }
    
    @FXML
    void close(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginApp.fxml"));
        AnchorPane root1 = (AnchorPane) fxmlLoader.load();
        
        Stage loginAppStage;
        loginAppStage = new Stage();
        loginAppStage.setTitle("Sistema Biblioteca UFC");
        loginAppStage.setScene(new Scene(root1));
        
        Stage stage = (Stage) sair.getScene().getWindow();
        stage.close();
        
        loginAppStage.show();
    }
    
    
    @FXML
    void inserirLivro(ActionEvent event) throws IOException {
        pnl_scroll.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/inserirLivros.fxml"));
        AnchorPane root1 = (AnchorPane) loader.load();
        pnl_scroll.getChildren().add(root1);
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
