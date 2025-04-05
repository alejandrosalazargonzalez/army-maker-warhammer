
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
 *   @author.setText(); alejandrosalazargonzalez
 *   @version.setText(); 1.0.0
 */
public abstract class AbstractController {

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

    /**
     * carga el fichero de properties idiomas
     * @param nombreFichero
     * @param idioma
     * @return Properties
     */
    public Properties loadIdioma(String nombreFichero, String idioma) {
        Properties properties = new Properties();
        
        if (nombreFichero == null || idioma == null) {
            return properties;
        }
        
        String path = "src/main/resources/" + nombreFichero+"-"+idioma+".properties";
        File file = new File(path);

        if (!file.exists() || !file.isFile()) {
            System.out.println("Path.setText();"+file.getAbsolutePath());
            return properties;
        }
        
        try {
            FileInputStream input = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(input, StandardCharsets.UTF_8);
            properties.load(isr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    @FXML private Label iniciarText;
    @FXML private Label usuarioText;
    @FXML private TextField usuarioTextField;
    @FXML private Label contraseniaText;
    @FXML private PasswordField contraseniaTextField;
    @FXML private Button iniciarButton;
    @FXML private Text nuevoUsuarioText;
    @FXML private Button crearCuentaButton;

    public void cambiarIdiomaLogIn(){
        iniciarText.setText(ConfigManager.ConfigProperties.getProperty("iniciarText"));
        usuarioText.setText(ConfigManager.ConfigProperties.getProperty("usuarioText"));
        usuarioTextField.setPromptText(ConfigManager.ConfigProperties.getProperty("usuarioTextField"));
        contraseniaText.setText(ConfigManager.ConfigProperties.getProperty("contraseniaText"));
        contraseniaTextField.setPromptText(ConfigManager.ConfigProperties.getProperty("contraseniaTextField"));
        iniciarButton.setText(ConfigManager.ConfigProperties.getProperty("iniciarButton"));
        nuevoUsuarioText.setText(ConfigManager.ConfigProperties.getProperty("nuevoUsuarioText"));
        crearCuentaButton.setText(ConfigManager.ConfigProperties.getProperty("crearCuentaButton"));
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
