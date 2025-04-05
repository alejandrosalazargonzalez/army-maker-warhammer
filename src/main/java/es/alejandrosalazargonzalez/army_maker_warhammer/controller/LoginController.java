
package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import java.io.IOException;
import java.sql.SQLException;

import es.alejandrosalazargonzalez.army_maker_warhammer.PrincipalApplication;
import es.alejandrosalazargonzalez.army_maker_warhammer.config.ConfigManager;
import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.UsuarioEntity;
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
public class LoginController extends AbstractController {
    
    @FXML
    private Label iniciarText;

    @FXML private TextField usuarioTextField;
    @FXML private TextField contraseniaTextField;
    @FXML private Button iniciarButton;
    @FXML private Button crearCuentaButton;
    @FXML private ComboBox<String> idiomaComboBox;

    /**
     * va a la pantalla de posts despues de comprobar que todo es correcto
     */
    @FXML
    private void buttonToPostsOnClick() {
        if (!comprobarTextField(usuarioTextField)) {
            iniciarText.setText("Usuario no puede estar vacio");
            return;
        }
        if (!comprobarTextField(contraseniaTextField)) {
            iniciarText.setText("Contraseña no puede estar vacio");
            return;
        }
        UsuarioEntity usuario = getUsuarioServiceModel().obtenerUsuarioPorUsuario(usuarioTextField.getText());
        if (usuario == null) {
            iniciarText.setText("el usuario no existe");
            return;
        }
        if (usuario.getContrasenia().equals(contraseniaTextField.getText())) {
            iniciarText.setText("error en usuario o contraseña");
            return;
        }
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

    /**
     * cambia el idioma de la web
     */
    @FXML
    private void comboBoxCambiarIdioma(){
        String path = "src/main/resources/idioma-"+idiomaComboBox.getValue().toString()+".properties";
        ConfigManager.ConfigProperties.setPath(path);
    }
}