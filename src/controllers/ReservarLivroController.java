/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import biblioteca.Alertas;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelos.userSystem;

/**
 * FXML Controller class
 *
 * @author brenocg
 */
public class ReservarLivroController implements Initializable {
    
    userSystem user;
    
    public void setUser(userSystem user){
        this.user = user;
    }
    
    @FXML
    private JFXTextField tf_titulo;

    @FXML
    void reservarBtn(ActionEvent event) {
        try {
            if(dao.LivroDAO.reservar(user, tf_titulo.getText()) != -1){
                Alertas.Informacao("Livro Reservado!", "Livro reservado com sucesso!");
            }else{
                Alertas.Aviso("Nao foi possivel reservar seu livro", "Verifique o nome");
            }
        } catch (SQLException ex) {
            System.out.println("falha na reserva do livro NIVEL CONTROLLER");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUser(new userSystem("teste", "testesenha", "alun"));
    }    
    
}
