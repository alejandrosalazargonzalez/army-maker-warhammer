
package es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import es.alejandrosalazargonzalez.army_maker_warhammer.PrincipalApplication;
import es.alejandrosalazargonzalez.army_maker_warhammer.config.ConfigManager;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.UsuarioServiceModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *   @author alejandrosalazargonzalez
 *   @version 1.0.0
 */
public abstract class AbstractController {

    private final String pathFichero = "src/main/resources/";
    private final String ficheroStr = "idioma-";
    private static String idiomaActual = "es";
    
    static final String PATH_DB ="src/main/resources/es/alejandrosalazargonzalez/army_maker_warhammer/usuarios.db";

    private UsuarioServiceModel usuarioServiceModel;

    private Properties propertiesIdioma;

    /**
     * Constructor
     */
    protected AbstractController() {
        try {
            usuarioServiceModel = new UsuarioServiceModel(PATH_DB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * setea el properties
     * @param properties
     */
    public void setpropertiesIdioma(Properties properties) {
        propertiesIdioma = properties;
    }

    /**
     * retorna el properties
     * @return Properties
     */
    public Properties getPropertiesIdioma() {
        return propertiesIdioma;
    }


    public static String getIdioma() {
        return idiomaActual;
    }

    public static void setIdioma(String idioma){
        idiomaActual = idioma;
    }

    /**
     * Funcion para cargar el idioma
     * @param idioma a cargar
     */
    protected void cargarIdiomaActual() {
        if (idiomaActual == null || idiomaActual.isEmpty()) {
            idiomaActual = "es";
        }
        
        String path = pathFichero+ficheroStr + idiomaActual + ".properties";
        ConfigManager.ConfigProperties.setPath(path);
    }

    @FXML public Label iniciarText;
    @FXML public Label usuarioText;
    @FXML public TextField usuarioTextField;
    @FXML public Label contraseniaText;
    @FXML public PasswordField contraseniaTextField;
    @FXML public Button iniciarButton;
    @FXML public Text olvidasteText;
    @FXML public Text nuevoUsuarioText;
    @FXML public Button crearCuentaButton;

    /**
     * cambiar idioma de la pantalla log in
     */
    public void cambiarIdiomaLogIn(){
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

    @FXML public Label registrarText;
    @FXML public Label nombreText;
    @FXML public TextField nombreTextField;
    @FXML public Label contraseniaText2;
    @FXML public TextField contraseniaTextfield2;
    @FXML public Label correoText;
    @FXML public TextField correoTextField;
    @FXML public Label correoText2;
    @FXML public TextField correoTextField2;
    @FXML public Button buttonAceptarRegistrar;
    @FXML public Button buttonAtras;

    /**
     * cambiar idioma Registrar
     */
    public void cambiarIdiomaRegistrar(){
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

    /**
     * retorna el usuarioServiceModel
     * @return UsuarioServiceModel
     */
    public UsuarioServiceModel getUsuarioServiceModel() {
        return this.usuarioServiceModel;
    }

    /**
     * comprueba que los textField sean correctos
     * @param campo
     * @return true/false
     */
    @FXML
    public boolean comprobarTextField( TextField campo){
        if (campo.getText() == null || campo.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * cambia a la pantalla indicada usando el boton que se le pasa como referencia
     * @param botton
     * @param pantalla
     */
    @FXML
    public void cambiarPantalla( Button botton, String pantalla){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource(pantalla+".fxml"));
            Stage stage = (Stage) botton.getScene().getWindow();
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

}
