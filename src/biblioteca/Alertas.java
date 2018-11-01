/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class Alertas {
    
    public static void Informacao(String header, String info){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informacao!");
        alert.setHeaderText(header);
        alert.setContentText(info);

        alert.showAndWait();
    }
    
    public static void Aviso(String header, String info){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Aviso!");
        alert.setHeaderText(header);
        alert.setContentText(info);

        alert.showAndWait();
    }
    
    public static void Erro(String header, String info){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Aviso!");
        alert.setHeaderText(header);
        alert.setContentText(info);

        alert.showAndWait();
    }
    
    public static boolean Confirmacao(String header, String info){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirme a acao");
        alert.setHeaderText(header);
        alert.setContentText(info);

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
    
    public static String Entrada(String header, String info){
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Entrada de Dados");
        dialog.setHeaderText(header);
        dialog.setContentText(info);
        
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        }
        else 
            return null;
    }
    public static void CadastroUser(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Cadastro de Usuario");
        dialog.setHeaderText("Digite seu nome e senha!");
        
        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Cadastrar", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        
        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField username = new TextField();
        username.setPromptText("nome");
        PasswordField password = new PasswordField();
        password.setPromptText("senha");
        
        
        grid.add(new Label("Nome: "), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Senha: "), 0, 1);
        grid.add(password, 1, 1);
        
        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        
        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());
        
        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });
        
        
        Optional<Pair<String, String>> result = dialog.showAndWait();
        
        result.ifPresent(usernamePassword -> {
            System.out.println("username = " + usernamePassword.getKey() + ", senha = " + usernamePassword.getValue());
        });
    } 
}
