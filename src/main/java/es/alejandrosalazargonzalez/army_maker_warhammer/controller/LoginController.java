package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import java.util.ArrayList;
import java.util.List;

import es.alejandrosalazargonzalez.army_maker_warhammer.config.ConfigManager;
import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.UsuarioEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * @author: alejandrosalazargonzalez
 * @version: 1.0.0
 */
public class LoginController extends AbstractController {

    @FXML
    public ComboBox<String> idiomaComboBox;
    @FXML
    public Label iniciarText;
    @FXML
    public Label usuarioText;
    @FXML
    public TextField usuarioTextField;
    @FXML
    public Label contraseniaText;
    @FXML
    public PasswordField contraseniaTextField;
    @FXML
    public Text errorText;
    @FXML
    public Button iniciarButton;
    @FXML
    public Hyperlink olvidasteText;
    @FXML
    public Text nuevoUsuarioText;
    @FXML
    public Button crearCuentaButton;

    @FXML
    public void initialize() {
        List<String> idiomas = new ArrayList<>();
        idiomas.add("es");
        idiomas.add("en");
        idiomaComboBox.getItems().addAll(idiomas);
        idiomaComboBox.setValue(cargarIdiomaActual());
        cambiarIdiomaLogIn();
    }

    /**
     * va a la pantalla de posts despues de comprobar que es correcto
     *
     */
    @FXML
    private void buttonToPostsOnClick() {
        if (revisarCamposLogin()) {
            errorText.setText("¡Bienvenidos al mundo de la programación!");
            cambiarPantalla(iniciarButton, "posts", "app-init");
        }
    }

    /**
     * comprueba los campos de la pagina
     * 
     * @return true/false
     */
    boolean revisarCamposLogin() {
        if (!comprobarTextField(usuarioTextField)) {
            errorText.setText("Usuario no puede estar vacio");
            return false;
        }
        if (!comprobarTextField(contraseniaTextField)) {
            errorText.setText("Contraseña no puede estar vacio");
            return false;
        }
        UsuarioEntity usuario = getUsuarioServiceModel().obtenerUsuarioPorUsuario(usuarioTextField.getText());
        if (usuario == null) {
            errorText.setText("el usuario no existe");
            return false;
        }
        if (!(usuario.getContrasenia().equals(contraseniaTextField.getText()))) {
            errorText.setText("error en usuario o contraseña");
            return false;
        }
        return true;
    }

    /**
     * cambia a la pantalla registrar
     */
    @FXML
    private void logInToRegistrarOnClick() {
        cambiarPantalla(crearCuentaButton, "registrar", "app-init");
    }

    @FXML
    private void loginToRecuperarOnClick(){
        cambiarPantalla(crearCuentaButton, getIdioma(), getIdioma());
    }

    /**
     * cambia el idioma de la web
     */
    @FXML void comboBoxCambiarIdioma() {
        String idioma = idiomaComboBox.getValue().toString();
        setIdioma(idioma);
        cargarIdiomaActual();
        cambiarIdiomaLogIn();
    }

    /**
     * cambiar idioma de la pantalla login
     */
    public void cambiarIdiomaLogIn() {
        iniciarText.setText(ConfigManager.ConfigProperties.getProperty("iniciarText"));
        usuarioText.setText(ConfigManager.ConfigProperties.getProperty("usuarioText"));
        usuarioTextField.setPromptText(ConfigManager.ConfigProperties.getProperty("usuarioTextField"));
        contraseniaText.setText(ConfigManager.ConfigProperties.getProperty("contraseniaText"));
        contraseniaTextField.setPromptText(ConfigManager.ConfigProperties.getProperty("contraseniaTextField"));
        iniciarButton.setText(ConfigManager.ConfigProperties.getProperty("iniciarButton"));
        olvidasteText.setText(ConfigManager.ConfigProperties.getProperty("olvidasteText"));
        nuevoUsuarioText.setText(ConfigManager.ConfigProperties.getProperty("nuevoUsuarioText"));
        crearCuentaButton.setText(ConfigManager.ConfigProperties.getProperty("crearCuentaButton"));
    }
}