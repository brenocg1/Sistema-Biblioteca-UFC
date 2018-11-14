/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import biblioteca.Alertas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.userSystem;

/**
 *
 * @author brenocg
 */
public class validateLogin {
    public static userSystem validateLogin(String login, String senha, String acesso){
        String sql = "SELECT `nome-usuario`, senha, `tipo-acesso` FROM tb_usuario WHERE `nome-usuario` = ? and senha = ?";
        userSystem user = null;
        
        try {
            PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
            
            psmt.setString(1, login);
            psmt.setString(2, senha);
            
            try{
                ResultSet rs = psmt.executeQuery();
                if(rs.next() && rs.getString(3).equals(acesso)){
                    Alertas.Confirmacao(null, "Bem-Vindo "+ rs.getString(1));
                }else{
                    Alertas.Aviso("Usuario nao encontrado", "Verifique o tipo de acesso, e login/senha");
                    return null;
                }
                
                rs.close();
            }catch(SQLException ex){
                System.out.println("problema no resultSet da validacao");
            }
            user = new userSystem(login, senha, acesso);
            
            return user;
        } catch (SQLException ex) {
            System.out.println("Problema de conexao ao banco no momento de validar login");
        }
        
        return null;
    }
}
