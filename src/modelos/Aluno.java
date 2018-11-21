/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author brenocg
 */
public class Aluno {
    ArrayList<telefone> telefones;
    
    String nome;
    String matricula;
    String endereco;
    String dataIngresso;
    String curso;

    public Aluno(ArrayList<telefone> telefones, String nome, String matricula, String endereco, String dataIngresso, String curso) {
        this.telefones = telefones;
        this.nome = nome;
        this.matricula = matricula;
        this.endereco = endereco;
        this.dataIngresso = dataIngresso;
        this.curso = curso;
    }

    @Override
    public String toString(){
        return "Nome: " + nome + " Matricula: " + matricula;
    }
}
