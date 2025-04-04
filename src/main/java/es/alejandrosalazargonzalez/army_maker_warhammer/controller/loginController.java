
package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import java.io.IOException;

import es.alejandrosalazargonzalez.army_maker_warhammer.PrincipalApplication;
import es.alejandrosalazargonzalez.army_maker_warhammer.config.ConfigManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public class loginController {
    
    @FXML
    private Label iniciarText;

    @FXML private TextField usuarioTextField;
    @FXML private TextField contraseniaTextField;
    @FXML private Button iniciarButton;
    @FXML private Button crearCuentaButton;
    @FXML private ComboBox idiomaComboBox;

    @FXML
    protected void ButtonToPostsOnClick() {
        iniciarText.setText("¡Bienvenidos al mundo de la programación!");
    }

    /**
     * cambia a la pantalla registrar
     */
    @FXML 
    private void logInToRegistrarOnClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("registrar.fxml"));
            Stage stage = (Stage) crearCuentaButton.getScene().getWindow();
            Scene scene;
            scene = new Scene(fxmlLoader.load(), 510, 900);
            stage.setResizable(false);
            stage.setTitle("Pantalla Princial");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void comboBoxCambiarIdioma(){
        String path = "src/main/resources/idioma-"+idiomaComboBox.getValue().toString()+".properties";
        ConfigManager.ConfigProperties.setPath(path);
    }
}