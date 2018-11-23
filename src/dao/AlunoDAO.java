/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Aluno;

/**
 *
 * @author brenocg
 */
public class AlunoDAO {
    
    public static void cadastrarAluno(Aluno aluno) throws SQLException{
        String sql = "CALL pr_cadastra_aluno(?, ?, ?, ?, ?)";
        
        CallableStatement callableStatement = ModuloConexao.conector().prepareCall(sql);
        
        callableStatement.setString(1, aluno.getNome());
        callableStatement.setString(2, aluno.getEndereco());
        callableStatement.setInt(3, getCursoId(aluno.getCurso()));
        callableStatement.setInt(4, Integer.parseInt(aluno.getMatricula()));
        callableStatement.setDate(5, new Date(aluno.getDataIngresso().getTime()));
        
        //apagar os registros q eu fiz de teste aqui
        
        try{
            callableStatement.execute();
        }catch(SQLException ex){
            System.out.println("falha no cadastro aluno");
            return;
        }
        
        callableStatement.close();
        
        for(modelos.telefone telefone : aluno.getTelefones()){
            String sql2 = "INSERT INTO tb_telefone_aluno values (?, ?)";
            PreparedStatement psmt2 = ModuloConexao.conector().prepareStatement(sql2);
            
            psmt2.setString(1, telefone.toString());
            psmt2.setString(2, aluno.getMatricula());
            
            psmt2.execute();
            
            psmt2.close();
        }
    }
    
    
    
    public static int getCursoId(String curso) throws SQLException{
        String sql = "SELECT `cod-curso` FROM tb_curso WHERE `nome-curso`=?";
        PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
        
        psmt.setString(1, curso);
        
        ResultSet rs = psmt.executeQuery();
        
        if(rs.next()){
            int cod = rs.getInt(1);
            rs.close();
            return cod;
        }
        return -1;
    }
    
    
    public static ArrayList<String> getCursos() throws SQLException{
        String sql = "SELECT `nome-curso` FROM tb_curso";
        PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
        
        ResultSet rs = psmt.executeQuery();
        
        ArrayList<String> cursos = new ArrayList<>();
        
        while(rs.next()){
            cursos.add(rs.getString(1));
        }
        rs.close();
        return cursos;
    }
    
}
