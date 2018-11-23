/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
public class EmprestimoLivroBibliController implements Initializable {    
    @FXML
    private JFXTextField tf_titulo;

    @FXML
    private JFXTextField tf_nome;

    @FXML
    void emprestimoBtn(ActionEvent event){
        try {
            dao.emprestimoDAO.insertEmprestimoBibli(tf_nome.getText(), tf_titulo.getText());
        } catch (SQLException ex) {
            System.out.println("deu bo no emprestimo");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
