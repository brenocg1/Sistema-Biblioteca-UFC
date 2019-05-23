/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXComboBox;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brenocg
 */
public class InserirLivrosController implements Initializable {

    @FXML
    private JFXTextField tf_nome;
    
    @FXML
    private JFXTextField tf_isbn;

    @FXML
    private JFXTextField tf_editora;

    @FXML
    private JFXComboBox<String> cb_autor;

    @FXML
    private JFXTextField tf_ano;
    
    @FXML
    private JFXTextField tf_quantidade;

    @FXML
    private JFXTextArea ta_output;
    
    @FXML
    private JFXComboBox<String> cb_categoria;

    @FXML
    void BtnCadastrar(ActionEvent event) {
        if(tf_nome.getText().equals("") ||
           tf_isbn.getText().equals("") ||
           tf_editora.getText().equals("") ||
           tf_ano.getText().equals("")  ||
           tf_quantidade.getText().equals(""))     
        {
            biblioteca.Alertas.Erro("Campo Vazio!", "Algum campo esta vazio, verifique e preecha");
            return;
        }
        
        try{dao.LivroDAO.cadastrarLivro(tf_isbn.getText(), tf_nome.getText(), tf_ano.getText(), 
                                        tf_editora.getText(), tf_quantidade.getText(), 
                                        cb_categoria.getSelectionModel().getSelectedItem(), 
                                        cb_autor.getSelectionModel().getSelectedItem());
           
            
        }catch(SQLException e){
            System.out.println("Falha na inserção");
            System.out.println(e);
            return;
        }
        biblioteca.Alertas.Informacao("Livro Cadastrado", "Sucesso no cadastro");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            tf_nome.setText("");
            tf_editora.setText("");
            tf_ano.setText("");
            tf_isbn.setText("");
            tf_quantidade.setText("");
            
            for(String cat : dao.LivroDAO.getCategorias()){
                if(cat == null) break;
                cb_categoria.getItems().add(cat);
            }
            for(String aut : dao.LivroDAO.getAutores()){
                if(aut == null) break;
                cb_autor.getItems().add(aut);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(InserirLivrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

