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

/**
 * FXML Controller class
 *
 * @author brenocg
 */
public class HomeUserController implements Initializable {
    
    private String user;
    private String senha;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
        Node node = (Node)FXMLLoader.load(getClass().getResource("/view/consultarLivros.fxml"));
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
