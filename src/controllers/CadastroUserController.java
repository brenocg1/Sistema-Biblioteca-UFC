/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextInputDialog;
import modelos.Aluno;
import modelos.telefone;

/**
 * FXML Controller class
 *
 * @author brenocg
 */


//Falta colocar multiplos telefones.
//Pegar data de conclusao prevista, pelo DatePicker de ingresso 
public class CadastroUserController implements Initializable {
    
    ArrayList<telefone> telefones = new ArrayList<>();
    
    @FXML
    private JFXTextField tx_nome;

    @FXML
    private JFXTextField tx_matr;

    @FXML
    private JFXTextField tx_endr;

    @FXML
    private JFXTextField tx_telf;

    @FXML
    private JFXDatePicker datePicker_dataIngress;

    @FXML
    private JFXTextField tx_curso;

    @FXML
    void adcTel(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("telefone");
        dialog.setTitle("Cadastro Telefones");
        dialog.setHeaderText("Adicione mais um telefone");
        dialog.setContentText("Coloque um telefone, caso queira colocar mais clique de novo");
        
        Optional<String> result = dialog.showAndWait();
        
        if (result.isPresent()){
            telefones.add(new telefone(result.get()));
        }
    }

    @FXML//chamar a dao e testar essa parada do Date...
    void cadastrar(ActionEvent event){
        Aluno aluno = new Aluno(telefones, tx_nome.getText(), tx_matr.getText(), tx_endr.getText(), Date.valueOf(datePicker_dataIngress.getValue().toString()), tx_curso.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
