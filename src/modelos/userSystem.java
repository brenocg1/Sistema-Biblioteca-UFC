/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

//tipoAcesso
//alun, func, prof, bibli;

public class userSystem {
    private String login;
    private String senha;
    
    private String tipoAcesso;  

    public userSystem(String login, String senha, String tipoAcesso) {
        this.login = login;
        this.senha = senha;
        this.tipoAcesso = tipoAcesso;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(String tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }
    
    
}
