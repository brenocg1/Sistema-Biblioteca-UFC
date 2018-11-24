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
    
    public static void insertEmprestimoBibli(String nome, String titulo) throws SQLException{
        String isbn = LivroDAO.getIsbn(titulo);
        int cod = userSysDAO.getCodPessoa(nome);
        
        if(getNumbersEmpres(cod) == 0){
            biblioteca.Alertas.Erro("Emprestimo Nao Realizado", "Este usuario ja excedeu o seu limite de emprestimo!");
            return;
        }
        
        String sql = "call pr_cadastra_emprestimo(?, ?)";
        
        CallableStatement callableStatement = null;
       
        callableStatement = ModuloConexao.conector().prepareCall(sql);
       
        callableStatement.setInt(1, cod);
        callableStatement.setString(2, isbn);
        
        callableStatement.execute();
        
        biblioteca.Alertas.Informacao("Emprestimo Concluido", "");
    }
    
    public static void darBaixa(String nome, String titulo) throws SQLException{
        String isbn = LivroDAO.getIsbn(titulo);
        int cod = userSysDAO.getCodPessoa(nome);
        
        String sql = "update tb_emprestimo set `status-devolucao`=1 where `cod-pessoa`=? and isbn=?";
        PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
        
        psmt.setInt(1, cod);
        psmt.setString(2, isbn);
        
        psmt.executeUpdate();
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
    
        public static int getNumbersEmpres(int cod) throws SQLException{
        String sql = "SELECT count(*), `tipo-acesso` FROM tb_emprestimo natural join tb_usuario WHERE `status-devolucao`=0 and `cod-pessoa`=?";
        
        PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
        
        psmt.setInt(1, cod);
        
        ResultSet rs = null;
        
        rs = psmt.executeQuery();
        
        if(rs.next()){
            if(rs.getInt(1) >= 3 && rs.getString(2).equals("alun")){
                rs.close();
                return 0;
            }else if(rs.getInt(1) >= 4 && rs.getString(2).equals("func")){
                rs.close();
                return 0;
            }else if(rs.getInt(1) >= 5 && rs.getString(2).equals("prof")){
                rs.close();
                return 0;
            }
            return 1;
        }
        return 0;
    }
    public static String getEmpres(String nome, String titulo) throws SQLException{
        String isbn = LivroDAO.getIsbn(titulo);
        int cod = userSysDAO.getCodPessoa(nome);
        
        String sql;
        PreparedStatement psmt;
        
        if(nome.equals("") && titulo.equals("")){
            sql = "select isbn, titulo, `data-devolucao`, `data-emprestimo`, `status-devolucao`, nome, `tipo-acesso` from tb_emprestimo natural join tb_livro natural join tb_pessoa natural join tb_usuario";
            psmt = ModuloConexao.conector().prepareStatement(sql);
        }else{
            sql = "select isbn, titulo, `data-devolucao`, `data-emprestimo`, `status-devolucao`, nome, `tipo-acesso`  from tb_emprestimo natural join tb_livro natural join tb_pessoa natural join tb_usuario where `cod-pessoa`=? or isbn=?";
            psmt=ModuloConexao.conector().prepareStatement(sql);
            
            psmt.setInt(1, cod);
            psmt.setString(2, isbn);
        }
        
        ResultSet rs = psmt.executeQuery();
        
        return LivroDAO.printResultSet(rs);
    }
}
