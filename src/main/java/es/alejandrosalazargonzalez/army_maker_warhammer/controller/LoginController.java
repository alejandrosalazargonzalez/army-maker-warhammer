
package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public class LoginController extends AbstractController {
    
    @FXML
    private Text errorText;

    @FXML private TextField usuarioTextField;
    @FXML private TextField contraseniaTextField;
    @FXML private Button iniciarButton;
    @FXML private Button crearCuentaButton;
    @FXML private ComboBox<String> idiomaComboBox;

    @FXML void initialize(){
        List<String> idiomas = new ArrayList<>(Arrays.asList("es","en"));
        idiomaComboBox.getItems().addAll(idiomas);
    }

    /**
     * va a la pantalla de posts despues de comprobar que todo es correcto
     */
    @FXML
    private void buttonToPostsOnClick() {
        if (!comprobarTextField(usuarioTextField)) {
            errorText.setText("Usuario no puede estar vacio");
            return;
        }
        if (!comprobarTextField(contraseniaTextField)) {
            errorText.setText("Contraseña no puede estar vacio");
            return;
        }
        UsuarioEntity usuario = getUsuarioServiceModel().obtenerUsuarioPorUsuario(usuarioTextField.getText());
        if (usuario == null) {
            errorText.setText("el usuario no existe");
            return;
        }
        if (!(usuario.getContrasenia().equals(contraseniaTextField.getText()))) {
            errorText.setText("error en usuario o contraseña");
            return;
        }
        errorText.setText("¡Bienvenidos al mundo de la programación!");
        cambiarPantalla(iniciarButton, "posts");
    }

    /**
     * cambia a la pantalla registrar
     */
    @FXML
    private void logInToRegistrarOnClick(){
        cambiarPantalla(crearCuentaButton, "registrar");
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