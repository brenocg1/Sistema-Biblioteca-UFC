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
import dao.ModuloConexao;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * @author brenocg
 */

//mySql no linux eh um instalador, nao um .jar*

public class loginController implements Initializable {
    
    @FXML
    private JFXButton login = new JFXButton();
    
    @FXML
    private JFXTextField usernameTF;

    @FXML
    private JFXPasswordField passwordTF;
    
    @FXML
    private JFXCheckBox cbConect;
    
    Paint color = new Color(1, 1, 1, 1);
    
    Connection conexao = null; // faz a conexao
    PreparedStatement pst = null; //prepara a query que eu vou mandar
    ResultSet rs = null; // me retorna o resultado da query
    
    public static Stage loginStage;
    
    @FXML
    public void loginBtn() throws IOException, InterruptedException{
        System.out.println("login clicked");
        
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        
        conexao = ModuloConexao.conector(username, password);
        
        if(conexao != null){
            cbConect.setText("Conectado ao Banco de Dados");
            cbConect.setSelected(true);
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginApp.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            loginStage = new Stage();
            loginStage.setTitle("Login na Aplicacao");
            loginStage.setScene(new Scene(root1));
            
                       
            biblioteca.Biblioteca.primaryStage.close();
            
            loginStage.show();
        }else{
            cbConect.setText("Falha na conexao");
            cbConect.setSelected(false);
        }
        
        System.out.println(conexao);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("initialized");
        login.setButtonType(JFXButton.ButtonType.RAISED);
        login.setTextFill(color);
        cbConect.setText("Nao Conectado ao Banco de Dados");
    }
}
