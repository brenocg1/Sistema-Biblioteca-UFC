/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
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

//        As informacoes do usuario estao chegando certo nesse controlador!      
//        System.out.println(user.toString());    
    
    @FXML
    void reservarButton(ActionEvent event) throws IOException {
        pnl_scroll.getChildren().clear();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reservarLivro.fxml"));
        AnchorPane root1 = (AnchorPane) loader.load();
        
//         Criando instancia do controller pra passar o usuario desse controlador
        ReservarLivroController controller = loader.getController();
        controller.setUser(user);

        pnl_scroll.getChildren().add(root1);
    }
    
    
    @FXML
    void emprestimosBtn(ActionEvent event) throws IOException {
        pnl_scroll.getChildren().clear();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/emprestimoLivro.fxml"));
        AnchorPane root1 = (AnchorPane) loader.load();
        
//      Criando instancia do controller pra passar o usuario desse controlador
        EmprestimoLivroController controller = loader.getController();
        controller.setUser(user);

        pnl_scroll.getChildren().add(root1);
    }
    
    @FXML
    public void close(){
        Stage stage = (Stage) sair.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
