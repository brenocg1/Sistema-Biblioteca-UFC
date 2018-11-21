/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextField;
import dao.ModuloConexao;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelos.userSystem;

public class EmprestimoLivroController implements Initializable {    
    
    userSystem user;
    
    public void setUser(userSystem user){
        this.user = user;
    }
    
    @FXML
    private JFXTextField ta_titulo;

    @FXML
    void emprestimoBtn(ActionEvent event) throws SQLException {
        String isbn = dao.LivroDAO.getIsbn(ta_titulo.getText());
        
        int cod = dao.userSysDAO.getUserID(user);
        String sql = "{call pr_cadastra_emprestimo`(?, ?)}";
        
//        
        
        CallableStatement callableStatement = null;
       
        callableStatement = ModuloConexao.conector().prepareCall(sql);
       
        callableStatement.setInt(1, cod);
        callableStatement.setString(2, isbn);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
