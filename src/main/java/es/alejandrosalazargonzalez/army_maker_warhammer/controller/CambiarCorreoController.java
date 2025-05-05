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
public class CambiarCorreoController extends AbstractController {
    @FXML TextField introduzcaContraseniaField;
    @FXML TextField nuevoCorreoField;
    @FXML Button aceptarButton;
    @FXML Button atrasButton;
    @FXML Text errorText;
    
    /**
     * actualiza  la contraseña del usuario
     */
    @FXML
    void aceptarOnClick(){
        if (!comprobarTextField(introduzcaContraseniaField)||!comprobarTextField(nuevoCorreoField)) {
            errorText.setText("ningun campo puede ser vacio");
            return;
        }
        if (introduzcaContraseniaField.getText()!=getUsuarioActual().getContrasenia()) {
            errorText.setText("La contraseña es erronea");
            return;
        }
        getUsuarioActual().setEmail(nuevoCorreoField.getText());
        try {
            getUsuarioServiceModel().actualizarUsuario(getUsuarioActual());
            errorText.setText("Correo cambiado");
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
        cambiarPantalla(atrasButton);
    }

}
