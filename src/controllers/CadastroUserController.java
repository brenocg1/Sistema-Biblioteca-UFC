/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author brenocg
 */


//Falta colocar multiplos telefones.
//Pegar data de conclusao prevista, pelo DatePicker de ingresso 
public class CadastroUserController implements Initializable {
    
    @FXML
    private JFXTextField tx_nome;

    @FXML
    private JFXTextField tx_matr;

    @FXML
    private JFXComboBox<?> cb_curso;

    @FXML
    private JFXTextField tx_endr;

    @FXML
    private JFXTextField tx_telf;

    @FXML
    private JFXDatePicker datePicker_dataIngress;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
