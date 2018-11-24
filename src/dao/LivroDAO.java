
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import biblioteca.Alertas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import modelos.userSystem;

/**
 *
 * @author brenocg
 */
public class LivroDAO {
    
    public static String rsPrintTable(ResultSet rs) throws SQLException{
        if(rs == null) return "";
        
        String res = "";
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) res += ",  ";
                String columnValue = rs.getString(i);
                res += columnValue + " " + rsmd.getColumnName(i);
            }
            res += "\n";
        }
        return res;
    }
    //Duas formas diferentes de printar a tabela...
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
        resultado += "=======++=======++=======";
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

    public static String getLivros(String nome, String autor, String ano, String editora, String cat) throws SQLException{
        String sqlQuery = "select * from vw_livro where titulo=? or autor=? or Ano_Publicado=? or Editora=? or Categoria=?";
        PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sqlQuery);
        
        psmt.setString(1, nome);
        psmt.setString(2, autor);
        psmt.setString(3, ano);
        psmt.setString(4, editora);
        psmt.setString(5, cat);
        
        ResultSet rs = null;
        
        try{
            rs = psmt.executeQuery();
        }catch (SQLException ex){
            System.out.println("erro ao executar consulta");
            System.out.println(ex);
        }
        //System.out.println(rsPrintTable(rs));
        try{
            return printResultSet(rs);
        }catch(SQLException e){
            System.out.println("erro no resultado do print");
        }
        
        psmt.close();
       
        return null;
    }
    
    public static int getDispLivros(String titulo) throws SQLException{
        //qtd de copias - count(do emprestimo)
        
        String sql = "select `qtd-copias`, isbn from tb_livro where titulo = ?";
        PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
        
        psmt.setString(1, titulo);
        
        ResultSet rs = psmt.executeQuery();
        
        String isbn = null;
        int qtd = -1;
        
        if(rs.next()){
            qtd = Integer.parseInt(rs.getString(1)); 
            isbn = rs.getString(2);
        }else{
            return -1;
        }
        
        //descobrindo quantos emprestimos o livro tem
        String sql2 = "select count(*) from tb_emprestimo where isbn = ? and `status-devolucao`=0";
        psmt = ModuloConexao.conector().prepareStatement(sql2);
        
        psmt.setString(1, isbn);
        
        rs = psmt.executeQuery();
        
        if(rs.next()){
            qtd = qtd - rs.getInt(1);
            rs.close();
            return qtd;
        }else{
            return -1;
        }
    }
    
    public static String getIsbn(String titulo) throws SQLException{
        String sql = "select isbn from tb_livro where titulo=?";
        PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
        psmt.setString(1, titulo);
        
        ResultSet rs = psmt.executeQuery();
        
        if(rs.next()){
            String isbn = rs.getString(1);
            rs.close();
            return isbn;
        }else{
            return null;
        }
        
        
        
    }
    
    public static int reservar(userSystem user, String titulo) throws SQLException{
        int cod = dao.userSysDAO.getUserID(user);
        String date = String.valueOf(LocalDate.now().getYear()) + "-" + String.valueOf(LocalDate.now().getMonth().getValue()) + "-" + String.valueOf(LocalDate.now().getDayOfMonth());
        String isbn = getIsbn(titulo);
        
        System.out.println(date + "\n" + isbn + "\n" + cod);
        
        
        if(isbn == null){
            Alertas.Erro("Livro nao encontrado", "Verifique o titulo digitado");
            return -1;
        }
        
        String sql = "insert into tb_reserva "
                + "(`data-reserva`, isbn, `cod-pessoa`) "
                + "values (?, ?, ?)";
    
        PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
        
        psmt.setString(1, date);
        psmt.setString(2, isbn);
        psmt.setInt(3, cod);
        
        psmt.executeUpdate();
        
        
        return 0;
    }
    
    public static String consultarReservas(String nome, String livro) throws SQLException{
        PreparedStatement psmt;
        
        if(nome.equals("") && livro.equals("")){
            String sql = "select `data-reserva`, isbn, nome from vw_reserva";
            psmt = ModuloConexao.conector().prepareStatement(sql);
        }else{
            String isbn = getIsbn(livro);
            String sql = "SELECT `data-reserva`, isbn, nome FROM vw_reserva";
            psmt = ModuloConexao.conector().prepareStatement(sql);

            psmt.setString(1, nome);
            psmt.setString(2, isbn);
        }
        
        ResultSet rs = psmt.executeQuery();
        
        return printResultSet(rs);
    }
    
}