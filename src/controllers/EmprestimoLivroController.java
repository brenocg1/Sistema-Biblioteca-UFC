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
        
        if(user.getTipoAcesso().equals("alun") && dao.emprestimoDAO.getNumbersEmpres(user) >= 3){
            biblioteca.Alertas.Erro("Falha no emprestimo", "Este aluno ja possui 3 livros com emprestimo");
            return;
        }
        
        else if(user.getTipoAcesso().equals("func") && dao.emprestimoDAO.getNumbersEmpres(user) >= 4){
            biblioteca.Alertas.Erro("Falha no emprestimo", "Este funcionario ja possui 4 livros com emprestimo");
            return;
        }
        
        else if(user.getTipoAcesso().equals("prof") && dao.emprestimoDAO.getNumbersEmpres(user) >= 5){
            biblioteca.Alertas.Erro("Falha no emprestimo", "Este professor ja possui 5 livros com emprestimo");
            return;
        }
        
        dao.emprestimoDAO.insertEmprestimo(cod, isbn);
        biblioteca.Alertas.Informacao("Emprestimo Realizado com Sucesso", "Voce tem: " + dao.emprestimoDAO.getNumbersEmpres(user) + " livros para devolver");
//        System.out.println("sucesso na insercao de emprestimo");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
