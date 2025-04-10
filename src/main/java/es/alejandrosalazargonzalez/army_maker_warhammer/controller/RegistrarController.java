package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import es.alejandrosalazargonzalez.army_maker_warhammer.config.ConfigManager;
import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.UsuarioEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author: alejandrosalazargonzalez
 * @version: 1.0.0
 */
public class RegistrarController extends AbstractController {

    @FXML
    public Label registrarText;
    @FXML
    public Label usuarioText;
    @FXML
    private TextField usuarioTextField;
    @FXML
    public Label nombreText;
    @FXML
    private TextField nombreTextField;
    @FXML
    public Label contraseniaText;
    @FXML
    private TextField contraseniaTextfield;
    @FXML
    public Label contraseniaText2;
    @FXML
    private TextField contraseniaTextfield2;
    @FXML
    public Label correoText;
    @FXML
    private TextField correoTextField;
    @FXML
    public Label correoText2;
    @FXML
    private TextField correoTextField2;
    @FXML
    private Label errorText;
    @FXML
    private Button buttonAtras;
    @FXML
    public Button buttonAceptarRegistrar;

    @FXML
    public void initialize() {
        cambiarIdiomaRegistrar();
    }

    /**
     * mete al usuario en la bbdd
     */
    @FXML
    private void onButtonAceptarRegClick() {
        if (!comprobarRegistrar()) {
            return;
        }

        UsuarioEntity nuevoUsuario = new UsuarioEntity(usuarioTextField.getText(), correoTextField.getText(),
                nombreTextField.getText(), contraseniaTextfield.getText());
        ArrayList<UsuarioEntity> usuarioEntityList;
        try {
            usuarioEntityList = getUsuarioServiceModel().obtenerUsarios();
            if (usuarioEntityList.contains(nuevoUsuario)) {
                errorText.setText("Ya hay una cuenta registrada con ese correo");
                return;
            }
            if (getUsuarioServiceModel().obtenerUsuarioPorUsuario(usuarioTextField.getText()) != null) {
                errorText.setText("Ya hay una cuenta registrada con ese usuario");
                return;
            }
            getUsuarioServiceModel().addUsuario(nuevoUsuario);
        } catch (SQLException e) {
            e.printStackTrace();
            errorText.setText("error no controlado");
        }
        registrarToLoginOnClick();
    }

    /**
     * comprueba que los campos sean validos
     * 
     * @return true/false
     */
    private boolean comprobarRegistrar() {
        if (!comprobarTextField(usuarioTextField)) {
            errorText.setText("Usuario no puede estar vacio");
            return false;
        }
        if (!comprobarTextField(nombreTextField)) {
            errorText.setText("Nombre no puede estar vacio");
            return false;
        }
        if (!comprobarTextField(contraseniaTextfield)) {
            errorText.setText("Contrasenia no puede estar vacio");
            return false;
        }
        if (!comprobarTextField(contraseniaTextfield2)) {
            errorText.setText("Repetir contrasenia no puede estar vacio");
            return false;
        }
        if (!contraseniaTextfield.getText().equals(contraseniaTextfield2.getText())) {
            errorText.setText("La contrasenia repetida debe ser igual");
            return false;
        }
        if (!comprobarTextField(correoTextField)) {
            errorText.setText("El correo no puede estar vacio");
            return false;
        }
        if (!comprobarTextField(correoTextField2)) {
            errorText.setText("Correo repetir de los valores puede estar vacio");
            return false;
        }
        if (!correoTextField.getText().equals(correoTextField2.getText())) {
            errorText.setText("Los correos deben ser iguales");
            return false;
        }
        return true;
    }

    /**
     * vuelve a log in
     */
    @FXML
    private void registrarToLoginOnClick() {
        cambiarPantalla(buttonAtras, "app-init", "registrar");
    }

    /**
     * cambiar idioma Registrar
     */
    public void cambiarIdiomaRegistrar() {
        registrarText.setText(ConfigManager.ConfigProperties.getProperty("registrarText"));
        usuarioText.setText(ConfigManager.ConfigProperties.getProperty("usuarioText"));
        usuarioTextField.setPromptText(ConfigManager.ConfigProperties.getProperty("usuarioTextField"));
        nombreText.setText(ConfigManager.ConfigProperties.getProperty("nombreText"));
        nombreTextField.setPromptText(ConfigManager.ConfigProperties.getProperty("nombreTextField"));
        contraseniaText2.setText(ConfigManager.ConfigProperties.getProperty("contraseniaText2"));
        contraseniaTextfield2.setPromptText(ConfigManager.ConfigProperties.getProperty("contraseniaTextfield2"));
        correoText.setText(ConfigManager.ConfigProperties.getProperty("correoText"));
        correoTextField.setPromptText(ConfigManager.ConfigProperties.getProperty("correoTextField"));
        correoText2.setText(ConfigManager.ConfigProperties.getProperty("correoText2"));
        correoTextField2.setPromptText(ConfigManager.ConfigProperties.getProperty("correoTextField2"));
        buttonAceptarRegistrar.setText(ConfigManager.ConfigProperties.getProperty("buttonAceptarRegistrar"));
        buttonAtras.setText(ConfigManager.ConfigProperties.getProperty("buttonAtras"));
    }

}
