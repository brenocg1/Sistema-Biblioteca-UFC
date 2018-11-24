/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brenocg
 */
public class HomeBibliController implements Initializable {
    
    @FXML // fx:id="sair"
    private JFXButton sair; // Value injected by FXMLLoader

    @FXML // fx:id="consultarLivros"
    private JFXButton consultarLivros; // Value injected by FXMLLoader

    @FXML // fx:id="consultarUsuarios"
    private JFXButton consultarUsuarios; // Value injected by FXMLLoader

    @FXML // fx:id="consultarReservas"
    private JFXButton consultarReservas; // Value injected by FXMLLoader

    @FXML // fx:id="empresRealizar"
    private JFXButton empresRealizar; // Value injected by FXMLLoader

    @FXML // fx:id="empresAlterar"
    private JFXButton empresAlterar; // Value injected by FXMLLoader

    @FXML // fx:id="empresBaixa"
    private JFXButton empresBaixa; // Value injected by FXMLLoader

    @FXML // fx:id="pnl_scroll"
    private VBox pnl_scroll; // Value injected by FXMLLoader
    
    @FXML
    public void close() throws IOException{
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
    void consultarLivros(ActionEvent event) throws IOException {
        pnl_scroll.getChildren().clear();
        Node node = (Node)FXMLLoader.load(getClass().getResource("/view/consultarLivros.fxml"));
        pnl_scroll.getChildren().add(node);
    }
    
    @FXML
    void consultarUsuarios(ActionEvent event) throws IOException {
        pnl_scroll.getChildren().clear();
        Node node = (Node)FXMLLoader.load(getClass().getResource("/view/consultarUsarios.fxml"));
        pnl_scroll.getChildren().add(node);
    }
    
    
    @FXML
    void consultarReserva(ActionEvent event) throws IOException {
        pnl_scroll.getChildren().clear();
        Node node = (Node)FXMLLoader.load(getClass().getResource("/view/consultarReservas.fxml"));
        pnl_scroll.getChildren().add(node);
    }
    
    @FXML
    void realizarEmprestimo(ActionEvent event) throws IOException {
        pnl_scroll.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/emprestimoLivroBibli.fxml"));
        AnchorPane root1 = (AnchorPane) loader.load();
        pnl_scroll.getChildren().add(root1);
    }
    
    
    @FXML
    void darBaixa(ActionEvent event) throws IOException {
        pnl_scroll.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/emprestimoDarBaixa.fxml"));
        AnchorPane root1 = (AnchorPane) loader.load();
        pnl_scroll.getChildren().add(root1);
    }
    
    @FXML
    void consultarEmpres(ActionEvent event) throws IOException{
        pnl_scroll.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/consultarEmpres.fxml"));
        AnchorPane root1 = (AnchorPane) loader.load();
        pnl_scroll.getChildren().add(root1);
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }    
    
}
