/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

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
}
