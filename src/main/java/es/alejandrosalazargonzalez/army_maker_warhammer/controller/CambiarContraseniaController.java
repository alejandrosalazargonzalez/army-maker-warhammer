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

public class CambiarContraseniaController extends AbstractController {
    @FXML TextField contraseniaViejaField;
    @FXML TextField contraseniaNuevaField;
    @FXML Button aceptarButton;
    @FXML Button atrasButton;
    @FXML Text errorText;
    
    /**
     * actualiza  el correo del usuario
     */
    @FXML
    void aceptarOnClick(){
        if (!comprobarTextField(contraseniaNuevaField)||!comprobarTextField(contraseniaViejaField)) {
            errorText.setText("ningun campo puede ser vacio");
            return;
        }
        if (contraseniaViejaField.getText()!=getUsuarioActual().getContrasenia()) {
            errorText.setText("La contraseña es erronea");
            return;
        }
        getUsuarioActual().setContrasenia(contraseniaNuevaField.getText());
        try {
            getUsuarioServiceModel().actualizarUsuario(getUsuarioActual());
            errorText.setText("Contraseña cambiada");
        } catch (SQLException e) {
            e.printStackTrace();
            errorText.setText("algo salio mal");
        }
    }

    @FXML
    void atrasOnClick(){
        cambiarPantalla(atrasButton,"opcionesCuenta","cambiarContrasenia");
    }
}
