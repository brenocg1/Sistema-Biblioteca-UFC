/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    public void close(){
        // get a handle to the stage
        Stage stage = (Stage) sair.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }    
    
}
