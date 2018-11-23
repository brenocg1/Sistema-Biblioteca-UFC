/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
    
    public static String printResultSet(ResultSet rs) throws SQLException {
        
        if(rs == null) return null;
        
        String resultado = "";
        // Prepare metadata object and get the number of columns.
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        // Print column names (a header).
        for (int i = 1; i <= columnsNumber; i++) {
            if (i > 1) resultado += " | ";
            resultado += rsmd.getColumnName(i);
        }
        resultado += "\n";
        resultado += "=======++=======++=======++=======++=======++=======";
        resultado += "\n";
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) resultado += " | ";
                resultado += rs.getString(i);
            }
            resultado += "\n";
        }
        return resultado;
    }
    
    public static int getCodPessoa(String nome) throws SQLException{
        String sql = "SELECT `cod-pessoa` FROM tb_pessoa WHERE nome=?";
        PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
        
        psmt.setString(1, nome);
        
        ResultSet rs = psmt.executeQuery();
        
        if(rs.next()){
            int cod = rs.getInt(1);
            rs.close();
            return cod;
        }else{
            return -1;
        }
    }
    
    public static String getUsers(String nome) throws SQLException{
        PreparedStatement psmt = null;
        if(!nome.equals("")){
            String sql = "select `nome-usuario`, `tipo-acesso` from tb_usuario natural join tb_pessoa WHERE `nome`=?";
        
            psmt = ModuloConexao.conector().prepareStatement(sql);
        
            psmt.setString(1, nome);
        }else{
            String sql = "SELECT `nome-usuario`, `tipo-acesso` FROM tb_usuario";
            psmt = ModuloConexao.conector().prepareStatement(sql);
        }
        
        ResultSet rs = psmt.executeQuery();
        
        return printResultSet(rs);
    }
}
