/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.userSystem;

/**
 *
 * @author brenocg
 */
public class userSysDAO {
    public static int getUserID(userSystem user) throws SQLException{
        String sql = "select `cod-pessoa` from tb_usuario where `nome-usuario`=?";
        PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
        
        psmt.setString(1, user.getLogin());
        
        ResultSet rs = psmt.executeQuery();
        
        if(rs.next()){
            int cod = rs.getInt(1);
            rs.close();
            return cod;
        }else{
            return -1;
        }
    }
    
    public static int getLivrosEmpres(userSystem user) throws SQLException{
        int cod = getUserID(user);
        
        String sql = "select count(*) from tb_emprestimo where `cod-pessoa`=? and `status-devolucao`=0";
        PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
        psmt.setInt(1, cod);
        
        ResultSet rs = psmt.executeQuery();
        
        if(rs.next()){
            int count = rs.getInt(1);
            rs.close();
            return count;
        }else{
            System.out.println("erro na consulta de quantos livros o usuario ja pegou");
            return -1;   
        }
    }
}
