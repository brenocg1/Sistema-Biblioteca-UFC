/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brenocg
 */
public class LoginAppController implements Initializable {
    @FXML
    private JFXTextField usernameTF;

    @FXML
    private JFXPasswordField passwordTF;

    @FXML
    private JFXCheckBox cbConnect;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton cadastroBtn;
    
    @FXML
    private JFXComboBox<String> cb_Tipo;
    
    
    
    public static Stage loginAppStage;
    
    @FXML
    void cadastroFunc(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/cadastroUser.fxml"));
        AnchorPane root1 = (AnchorPane) fxmlLoader.load();

//      Validacao com o banco...
//        if(tipo.equals("User")){
//            controllers.HomeUserController controller = fxmlLoader.<HomeUserController>getController();
//            controller.setUser(usernameTF.getText());
//        }

        loginAppStage = new Stage();
        loginAppStage.setTitle("Cadastro Aluno");
        loginAppStage.setScene(new Scene(root1));
        loginAppStage.show();
    }

    @FXML
    void loginFunc(ActionEvent event) throws IOException {
        System.out.println("login app clicked");
        if(usernameTF.getText().equals("admin") && passwordTF.getText().equals("admin"))
            trocarTela("homeAdmin.fxml", "Administrador");
        else if(usernameTF.getText().equals("user"))
            trocarTela("homeUser.fxml", "Usuario");
        else if(usernameTF.getText().equals("bibliotecario"))
            trocarTela("homeBibli.fxml", "Bibliotecario");

    }
    
    public void trocarTela(String fxml, String tipo) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/"+fxml));
        AnchorPane root1 = (AnchorPane) fxmlLoader.load();
        
//        if(tipo.equals("User")){
//            controllers.HomeUserController controller = fxmlLoader.<HomeUserController>getController();
//            controller.setUser(usernameTF.getText());
//        }
        
        loginAppStage = new Stage();
        loginAppStage.setTitle("Sistema Biblioteca UFC - " + tipo);
        loginAppStage.setScene(new Scene(root1));
        
        controllers.loginController.loginStage.close();
        loginAppStage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbConnect.setSelected(true);
        cb_Tipo.getItems().add("Aluno");
        cb_Tipo.getItems().add("Funcionario");
        cb_Tipo.getItems().add("Professor");
        cb_Tipo.getItems().add("Bibliotecario");
    }    
    
}
