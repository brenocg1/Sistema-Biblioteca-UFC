/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
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
    private JFXComboBox<String> cb_curso;
    
    @FXML
    private JFXTextField tf_login;

    @FXML
    private JFXPasswordField tfp_password;


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
    void cadastrar(ActionEvent event) throws ParseException{
        
        if(tx_nome.getText().equals("") ||
           tx_endr.getText().equals("") ||
           tx_matr.getText().equals("") ||
           tx_telf.getText().equals(""))
        {
            biblioteca.Alertas.Erro("Campo Vazio!", "Algum campo esta vazio, verifique e preecha");
            return;
        }
        
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(datePicker_dataIngress.getValue().toString());
        
        telefones.add(new telefone(tx_telf.getText()));
        
        Aluno aluno = new Aluno(telefones, tx_nome.getText(), tx_matr.getText(), tx_endr.getText(), parsed, cb_curso.getSelectionModel().getSelectedItem());
        
        try {
            if(dao.AlunoDAO.cadastrarAluno(aluno, tf_login.getText(), tfp_password.getText()) == -1) return;
        } catch (SQLException ex){
            System.out.println("exception na dao");
            return;
        }
        
        biblioteca.Alertas.Informacao("Cadastrado Realizado!", "Novo aluno cadastrado com successo!");
        
        //fechando tela de cadastro
        Stage stage = (Stage) tx_nome.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        tx_endr.setText("");
        tx_nome.setText("");
        tx_telf.setText("");
        tx_matr.setText("");
        
        ArrayList<String> cursos = null;
        try {
            cursos = dao.AlunoDAO.getCursos();
        } catch (SQLException ex) {
            System.out.println("problema no getCursos()");
        }
        
        for(String curso : cursos)
            cb_curso.getItems().add(curso);
    }    
    
}
