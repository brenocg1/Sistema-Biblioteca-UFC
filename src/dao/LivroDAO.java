
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
import java.util.ArrayList;
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
        
        PreparedStatement psmt = null;
        if(nome.equals("") &&
           autor.equals("") &&
           ano.equals("") &&
           editora.equals("") &&
           cat.equals(""))
        {
            String sql = "select * from vw_livro";
            psmt = ModuloConexao.conector().prepareStatement(sql);
            
        }
        else{
            String sqlQuery = "select * from vw_livro where titulo=? or autor=? or Ano_Publicado=? or Editora=? or Categoria=?";        
            psmt = ModuloConexao.conector().prepareStatement(sqlQuery);

            psmt.setString(1, nome);
            psmt.setString(2, autor);
            psmt.setString(3, ano);
            psmt.setString(4, editora);
            psmt.setString(5, cat);
        }
        
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
    
    public static ArrayList<String> getCategorias() throws SQLException{
        String sql = "select `descricao-categoria` from tb_categoria";
        PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
        
        ResultSet rs = psmt.executeQuery();
        
        ArrayList<String> categorias = new ArrayList<>();
        
        while(rs.next()){
            String desc = rs.getString("descricao-categoria");
            categorias.add(desc);
        }
        
        return categorias;
    }
    
    public static ArrayList<String> getAutores() throws SQLException{
        String sql = "select nome from tb_autor";
        PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
        
        ResultSet rs = psmt.executeQuery();
        
        ArrayList<String> autores = new ArrayList<>();
        
        while(rs.next()){
            String nome = rs.getString("nome");
            autores.add(nome);
        }
        
        return autores;
    }
    
    
    public static void cadastrarLivro(String isbn, String nome, String ano, String editora, String quantidade, String categoria, String nomeAutor) throws SQLException{
        
        String sql = "insert into tb_livro (isbn, titulo, `ano-de-lancamento`, editora, `qtd-copias`, `cod-categoria`)" +
                     "values (?,?,?,?,?,(select `cod-categoria` from tb_categoria where `descricao-categoria`=?))";
        try{
            PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
            
            psmt.setString(1, isbn);
            psmt.setString(2, nome);
            psmt.setInt(3, Integer.parseInt(ano));
            psmt.setString(4, editora);
            psmt.setInt(5, Integer.parseInt(quantidade));
            psmt.setString(6, categoria);            
            psmt.execute();
            psmt.close();
       
            
        }catch(SQLException e){
            System.out.println("Falha no cadastro");
            System.out.println(e);
        }
        
        String sql2 = "insert into tb_livro_autor(isbn, `cod-autor`) values(?, (select `cod-autor` from tb_autor where nome=?))";

        PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql2);
        
        psmt.setString(1, isbn);
        psmt.setString(2, nomeAutor);
        psmt.execute();
        psmt.close();
    }
    
    public static void removerLivro(String isbn){
        
        String sql = "delete from tb_livro where isbn=?;"
                   + "delete from tb_livro_autor where isbn=?";
        
        try{
            PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
            psmt.setString(1,isbn);
            psmt.setString(2,isbn);
            
        }catch(SQLException e){
            
        }    
    }

    public static void alteraLivro(String isbnKey, String isbn, String nome, String ano, String editora, String quantidade, String categoria, String nomeAutor){
        
        String sql = "update tb_livro" +
                     "set isbn=?, titulo=?,`ano-de-lancamento`=?, editora=?, `qtd-copias`=?, `cod-categoria`=?;"
                   + "where isbn = isbnkey" +
                
                     "update tb_livro_autor" +
                     "set isbn=?, `cod-autor` = (select `cod-autor` from tb_autor where nome =?))"
                   + "where isbn = isbnkey";
        
        try{
            PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
            
            psmt.setString(1, isbn);
            psmt.setString(2, nome);
            psmt.setInt(3, Integer.parseInt(ano));
            psmt.setString(4, editora);
            psmt.setInt(5, Integer.parseInt(quantidade));
            psmt.setString(6, categoria);
            
            psmt.setString(7, isbn);
            psmt.setString(8, nomeAutor);
            
            psmt.execute();
            psmt.close();
       
            
        }catch(SQLException e){
            System.out.println("Falha na alteração");
            System.out.println(e);
        }
    }
    
    public static void cadastrarAutor(int codAutor, String nome, String cpf, String nacionalidade){
        
        String sql = "insert into tb_autor(`cod-autor`, nome, nacionalidade, cpf)"
                   + "values(?,?,?,?)";
        
        try{
            PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
            
            psmt.setInt(1, codAutor);
            psmt.setString(2, nome);
            psmt.setString(2, nacionalidade);
            psmt.setString(3, cpf);
            
        }catch(SQLException e){
            System.out.println("Falha no cadastro");
            System.out.println(e);
        }
    }
    
    public static void removerAutor(String cpf){
        
        String sql = "delete from tb_autor where cpf=?;"
                   + "delete from tb_livro_autor where cpf=?";
    
        try{
           PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
           psmt.setString(1,cpf);
           psmt.setString(2,cpf);
            
        }catch(SQLException e){
           System.out.println("Falha na remoção");
            System.out.println(e); 
        } 
    }
    
    public static void alteraAutor(String key, String nome, String cpf, String nacionalidade){
        
        String sql = "update tb_autor" +
                     "set cpf=?, nome=?,nacionalidade=?;"
                   + "where cpf = key" +
                
                     "update tb_livro_autor" +
                     "set `cod-autor` = (select `cod-autor` from tb_autor where cpf =?)"
                   + "where `cod-autor` = (select `cod-autor` from tb_autor where cpf = key) ";
        
        try{
            PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
            
            psmt.setString(1, cpf);
            psmt.setString(2, nome);
            psmt.setString(3, nacionalidade);
            psmt.setString(4, cpf);
            
            psmt.execute();
            psmt.close();
       
            
        }catch(SQLException e){
            System.out.println("Falha na alteração");
            System.out.println(e);
        }
    } 
    
    public static void cadastrarCategoria(int codCategoria, String desc){
        
        String sql = "insert into tb_categoria(`cod-categoria`, `descricao-categoria`)"
                   + "values (?,?)";
        
        try{
            PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
            
            psmt.setInt(1, codCategoria);
            psmt.setString(2, desc);
            
        }catch(SQLException e){
            System.out.println("Falha no cadastro");
            System.out.println(e);
        }
    }
    
    public static void removerCategoria(int codCategoria){
        
        String sql = "delete from tb_categoria where `cod-categoria`=?;"
                   + "delete from tb_livro where `cod-categoria`=?;"
                   + "delete from tb_livro_autor where isbn = "
                   + "(SELECT isbn FROM tb_livro natural join tb_livro_autor natural join tb_categoria"
                   + "WHERE `cod-categoria`=?)";
    
        try{
           PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
           psmt.setInt(1,codCategoria);
           psmt.setInt(2,codCategoria);
           psmt.setInt(3,codCategoria);
            
        }catch(SQLException e){
           System.out.println("Falha na remoção");
            System.out.println(e); 
        }
    
    }
    
    public static void alterarCaegoria(int key, int codCategoria, String desc){
        
        String sql = "updade tb_categoria"
                   + "set `cod-categoria`=?, `descricao-categoria`=?"
                   + "where `cod-categoria` = key;"
                   
                   + "update tb_livro"
                   + "set `cod-categoria`=?"
                   + "where `cod-categoria` = key";
        
        try{
            PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
            
            psmt.setInt(1, codCategoria);
            psmt.setString(2, desc);
            psmt.setInt(3, codCategoria);
            
            psmt.execute();
            psmt.close();
       
            
        }catch(SQLException e){
            System.out.println("Falha na alteração");
            System.out.println(e);
        }
    }

    public static void cadastrarCurso(int codCurso, String nomeCurso){
        
        String sql = "insert into tb_curso(`cod-curso`, `nome-curso`)"
                   + "values(?,?)";
        
         try{
            PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
            
            psmt.setInt(1, codCurso);
            psmt.setString(2, nomeCurso);
            
        }catch(SQLException e){
            System.out.println("Falha no cadastro");
            System.out.println(e);
        }
    }
    
    public static void removerCurso(int codCurso){
        
        String sql = "delete from tb_curso where `cod-curso`=?;"
                   + "delete from tb_aluno where `cod-curso`=?"
                   + "delete from tb_professor where `cod-curso`=?";
        
        try{
           PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
           psmt.setInt(1,codCurso);
           psmt.setInt(2,codCurso);
           psmt.setInt(3,codCurso);
            
        }catch(SQLException e){
           System.out.println("Falha na remoção");
            System.out.println(e); 
        }
    } 
    
    public static void alterarCurso(int key, int codCurso, String nomeCurso){
        
        String sql = "update tb_curso"
                   + "set `cod-curso`=?, `nome-curso`=?"
                   + "where `cod-curso`= key;"
                
                   + "update tb_aluno" 
                   + "set `cod-curso`=?"
                   + "where `cod-curso` = key;"
        
                   + "update tb_professor"
                   + "set `cod-curso`=?"
                   + "where `cod-curso` = key ";
        
        try{
            PreparedStatement psmt = ModuloConexao.conector().prepareStatement(sql);
            
            psmt.setInt(1, codCurso);
            psmt.setString(2, nomeCurso);
            psmt.setInt(3, codCurso);
            psmt.setInt(4, codCurso);
            
            psmt.execute();
            psmt.close();
            
        }catch(SQLException e){
            System.out.println("Falha na alteração");
            System.out.println(e);
        }
        
    }
    
}
