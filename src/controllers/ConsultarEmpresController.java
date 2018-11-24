/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author brenocg
 */
public class ConsultarEmpresController implements Initializable {
    
    //select isbn, titulo, `data-devolucao`, `data-emprestimo`, `status-devolucao`, nome, `tipo-acesso`  from tb_emprestimo natural join tb_livro natural join tb_pessoa natural join tb_usuario
    
    @FXML
    private JFXTextField tf_titulo;

    @FXML
    private JFXTextArea ta_Result;

    @FXML
    private JFXTextField tf_nome;

    @FXML
    void consultarBtn(ActionEvent event) {
        try {
            ta_Result.setText(dao.emprestimoDAO.getEmpres(tf_nome.getText(), tf_titulo.getText()));
        } catch (SQLException ex) {
            System.out.println("problema no getEmpres()");
            return;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }    
    
}
