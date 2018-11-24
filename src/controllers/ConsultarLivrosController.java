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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author brenocg
 */
public class ConsultarLivrosController implements Initializable {

    @FXML
    private JFXTextField tf_nome;

    @FXML
    private JFXTextField tf_editora;

    @FXML
    private JFXTextField tf_autor;

    @FXML
    private JFXTextField tf_ano;

    @FXML
    private JFXTextArea ta_output;
    
    @FXML
    private JFXTextField tf_categoria;

    @FXML
    void BtnProcurar(ActionEvent event) {
        ta_output.setText("");
        try {
            ta_output.setText(dao.LivroDAO.getLivros(tf_nome.getText(), tf_autor.getText(), tf_ano.getText(), tf_editora.getText(), tf_categoria.getText()));
        } catch (SQLException ex) {
            //usar Alerts aqui
            System.out.println("falha na consulta");
            System.out.println(ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tf_nome.setText("");
        tf_autor.setText("");
        tf_editora.setText("");
        tf_ano.setText("");
        tf_categoria.setText("");
    }    
    
}
