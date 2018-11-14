/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import biblioteca.Alertas;
import com.jfoenix.controls.JFXButton;
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
public class ConsultarDispLivroController implements Initializable {

    @FXML
    private JFXTextField tf_nomeLivro;

    @FXML
    private JFXButton btn_procurar;

    @FXML
    private JFXTextArea ta_qtdDisp;

    @FXML
    void procurarAction(ActionEvent event) {
        int qtd = -1;
        
        try {
            qtd = dao.LivroDAO.getDispLivros(tf_nomeLivro.getText());
        } catch (SQLException ex) {
            System.out.println("falha na consulta de disponibilidade de livros");
        }
        
        if(qtd == -1){
            Alertas.Erro("Livro nao encontrado", "verifique o titulo digitado");
        }else{
            ta_qtdDisp.setText(qtd + "");
        }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
