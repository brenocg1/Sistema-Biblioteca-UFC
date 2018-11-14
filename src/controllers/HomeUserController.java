/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import dao.LivroDAO;
import dao.ModuloConexao;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelos.userSystem;

/**
 * FXML Controller class
 *
 * @author brenocg
 */
public class HomeUserController implements Initializable {
    
    userSystem user;
    
    public void setUser(userSystem user){
        this.user = user;
    }
    
    @FXML
    private JFXButton sair;

    @FXML
    private JFXButton consultarLivros;

    @FXML
    private JFXButton empres;

    @FXML
    private JFXButton dispExem;

    @FXML
    private JFXButton reservar;

    @FXML
    private VBox pnl_scroll;
    
    @FXML
    void consultarLivrosAction(ActionEvent event) throws IOException{
        pnl_scroll.getChildren().clear();
        Node node = (Node)FXMLLoader.load(getClass().getResource("/view/consultarLivros.fxml"));
        pnl_scroll.getChildren().add(node);
    }
    
    @FXML
    void dispExemAction(ActionEvent event) throws IOException {
        pnl_scroll.getChildren().clear();
        Node node = (Node)FXMLLoader.load(getClass().getResource("/view/consultarDispLivro.fxml"));
        pnl_scroll.getChildren().add(node);
    }
    
    
    @FXML
    public void close(){
        Stage stage = (Stage) sair.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
