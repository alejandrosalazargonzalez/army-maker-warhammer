
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author alejandrosalazargonzalez
 * @version 1.0.0
 */
public abstract class AbstractController {

    private final String pathFichero = "src/main/resources/";
    private final String ficheroStr = "idioma-";
    private static String idiomaActual = "es";

    private String pantallaAnterior;

    static final String PATH_DB = "src/main/resources/es/alejandrosalazargonzalez/army_maker_warhammer/usuarios.db";

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
     * 
     * @param properties
     */
    public void setpropertiesIdioma(Properties properties) {
        propertiesIdioma = properties;
    }

    /**
     * retorna el properties
     * 
     * @return Properties
     */
    public Properties getPropertiesIdioma() {
        return propertiesIdioma;
    }

    public static String getIdioma() {
        return idiomaActual;
    }

    public static void setIdioma(String idioma) {
        idiomaActual = idioma;
    }

    /**
     * Funcion para cargar el idioma
     * 
     * @param idioma a cargar
     */
    protected String cargarIdiomaActual() {
        if (idiomaActual == null || idiomaActual.isEmpty()) {
            idiomaActual = "es";
        }

        String path = pathFichero + ficheroStr + idiomaActual + ".properties";
        ConfigManager.ConfigProperties.setPath(path);
        return idiomaActual;
    }

    /**
     * retorna el usuarioServiceModel
     * 
     * @return UsuarioServiceModel
     */
    public UsuarioServiceModel getUsuarioServiceModel() {
        return this.usuarioServiceModel;
    }

    /**
     * comprueba que los textField sean correctos
     * 
     * @param campo
     * @return true/false
     */
    @FXML
    public boolean comprobarTextField(TextField campo) {
        if (campo.getText() == null || campo.getText().isEmpty()) {
            return false;
        }
        return campo.getText() != null || !(campo.getText().isEmpty());
    }

    /**
     * cambia a la pantalla indicada usando el boton que se le pasa como referencia
     * 
     * @param botton
     * @param pantalla
     */
    @FXML
    public void cambiarPantalla(Button botton, String pantalla, String pantallaAnterior) {
        try {
            this.pantallaAnterior = pantallaAnterior;
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource(pantalla + ".fxml"));
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

        /**
     * cambia a la pantalla indicada usando el boton que se le pasa como referencia
     * 
     * @param botton
     * @param pantalla
     */
    @FXML
    public void cambiarPantalla(Hyperlink botton, String pantalla, String pantallaAnterior) {
        try {
            this.pantallaAnterior = pantallaAnterior;
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource(pantalla + ".fxml"));
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

    /**
     * cambia a la pantalla anterior usando el boton que se le pasa como referencia
     * 
     * @param botton
     * @param pantalla
     */
    @FXML
    public void cambiarPantalla(Button botton) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource(pantallaAnterior + ".fxml"));
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
