/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author brenocg     
 */
public class ModuloConexao {
    //metodo por estabelecer a conexao com o banco 
    public static Connection conector(String login, String pw){
        Connection conexao = null;
        // a linha abaixo "chama" o driver que eu instalei(windows importa)
        String driver = "com.mysql.jdbc.Driver";   
        //informacoes do banco                 
        String url = "jdbc:mysql://localhost:3306/DB-Aula"; //falta o nome do Banco aqui
        String user = login;//vai mah
        String password = pw;
        
        //Estabelecendo comunicacao com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        }
        
        catch (Exception e){
            System.out.println("problema ao conectar ao banco de dados");
            //e.printStackTrace();
            System.out.println(e);
            System.out.println("com usuario " + user + " e senha " + password);
        }   
        return conexao;
    }
    
}
