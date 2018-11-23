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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author brenocg
 */
public class EmprestimoDarBaixaController implements Initializable {
    @FXML
    private JFXTextField tf_titulo;

    @FXML
    private JFXTextField tf_nome;

//    @FXML
//    void darBaixa(ActionEvent event) {
//        try {
//            dao.emprestimoDAO.darBaixa(tf_nome.getText(), tf_titulo.getText());
//        } catch (SQLException ex) {
//            biblioteca.Alertas.Erro("Erro Emprestimo", "Nao foi possivel dar baixa, verifique os campos");
//        }
//        biblioteca.Alertas.Informacao("Sucesso!", "Livro Devolvido com sucesso!");
//    }
    
    @FXML
    void devolverBtn(ActionEvent event) {
        try {
            dao.emprestimoDAO.darBaixa(tf_nome.getText(), tf_titulo.getText());
        } catch (SQLException ex) {
            biblioteca.Alertas.Erro("Erro Emprestimo", "Nao foi possivel dar baixa, verifique os campos");
            return;
        }
        biblioteca.Alertas.Informacao("Sucesso!", "Livro Devolvido com sucesso!");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
