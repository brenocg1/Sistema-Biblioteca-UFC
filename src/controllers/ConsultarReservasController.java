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

public class ConsultarReservasController implements Initializable{
    
    @FXML
    private JFXTextField tf_nomeUser;

    @FXML
    private JFXTextArea ta_resultado;

    @FXML
    private JFXTextField tf_nomeLivro;

    @FXML
    void btnProcurar(ActionEvent event) throws SQLException{
        String res = dao.LivroDAO.consultarReservas(tf_nomeUser.getText(), tf_nomeLivro.getText());
        if(res == null){
            biblioteca.Alertas.Aviso("Nada Encontrado", "Nao foi encontrado nenhum registro de reserva");
        }else{
            ta_resultado.setText(res);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf_nomeLivro.setText("");
        tf_nomeUser.setText("");
    }
    
}
