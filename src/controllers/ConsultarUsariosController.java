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
public class ConsultarUsariosController implements Initializable {

    @FXML
    private JFXTextField tf_nomeUser;

    @FXML
    private JFXTextArea ta_resultado;

    @FXML
    void btnProcurar(ActionEvent event) throws SQLException{
        String result = dao.userSysDAO.getUsers(tf_nomeUser.getText());
        
        if(result != null){
            ta_resultado.setText(result);
        }else{
            biblioteca.Alertas.Erro("Usuario Nao Encontrado!", "Verifique se ha espacos no nome inserido");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ta_resultado.setText("");
    }    
    
}
