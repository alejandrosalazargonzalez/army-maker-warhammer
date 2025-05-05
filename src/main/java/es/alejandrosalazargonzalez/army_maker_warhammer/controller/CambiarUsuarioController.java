package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import java.sql.SQLException;

import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */

public class CambiarUsuarioController extends AbstractController {
    @FXML TextField introduzcaContraseniaField;
    @FXML TextField nuevoUsaurioField;
    @FXML Button aceptarButton;
    @FXML Button atrasButton;
    @FXML Text errorText;
    
    /**
     * actualiza el usuario del usuario
     */
    @FXML
    void aceptarOnClick(){
        if (!comprobarTextField(introduzcaContraseniaField)||!comprobarTextField(nuevoUsaurioField)) {
            errorText.setText("ningun campo puede ser vacio");
            return;
        }
        if (introduzcaContraseniaField.getText()!=getUsuarioActual().getContrasenia()) {
            errorText.setText("La contraseña es erronea");
            return;
        }
        getUsuarioActual().setUsuario(nuevoUsaurioField.getText());
        try {
            getUsuarioServiceModel().actualizarUsuario(getUsuarioActual());
            errorText.setText("Usuario cambiado");
        } catch (SQLException e) {
            e.printStackTrace();
            errorText.setText("algo salio mal");
        }
    }

    /**
     * cambiar pantalla a opciones cuenta
     */
    @FXML
    void atrasOnClick(){
        cambiarPantalla(atrasButton,"opcionesCuenta","cambiarCorreo");
    }
}
