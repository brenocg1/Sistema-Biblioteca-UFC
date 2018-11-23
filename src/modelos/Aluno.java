/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author brenocg
 */
public class Aluno {
    ArrayList<telefone> telefones;
    
    String nome;
    String matricula;
    String endereco;
    Date dataIngresso;
    String curso;

    public Aluno(ArrayList<telefone> telefones, String nome, String matricula, String endereco, Date dataIngresso, String curso) {
        this.telefones = telefones;
        this.nome = nome;
        this.matricula = matricula;
        this.endereco = endereco;
        this.dataIngresso = dataIngresso;
        this.curso = curso;
    }

    public ArrayList<telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(ArrayList<telefone> telefones) {
        this.telefones = telefones;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(Date dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    

    @Override
    public String toString(){
        return "Nome: " + nome + " Matricula: " + matricula;
    }
}
