/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.userSystem;

/**
 *
 * @author brenocg
 */
public class emprestimoDAO {
    public static void insertEmprestimo(int cod, String isbn) throws SQLException{
        String sql = "call pr_cadastra_emprestimo(?, ?)";
        
        CallableStatement callableStatement = null;
       
        callableStatement = ModuloConexao.conector().prepareCall(sql);
       
        callableStatement.setInt(1, cod);
        callableStatement.setString(2, isbn);
        
        callableStatement.execute();
    }
    
    public static int getNumbersEmpres(userSystem user) throws SQLException{
        String sql = "SELECT count(*) FROM tb_emprestimo natural join tb_usuario WHERE `status-devolucao`=0 and `nome-usuario`=?";
        
        PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
        
        psmt.setString(1, user.getLogin());
        
        ResultSet rs = null;
        
        rs = psmt.executeQuery();
        
        if(rs.next()){
            int qtd = rs.getInt(1);
            rs.close();
            return qtd;
        }else{
            return -1;
        }
    }
    
}
