package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import java.sql.SQLException;

import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class eliminarCuentaController extends AbstractController {
    
    @FXML TextField introduzcaContraseniaField;
    @FXML TextField escribaAceptarField;
    @FXML Button aceptarButton;
    @FXML Button atrasButton;
    @FXML Text errorText;

    /**
     * comprueba los campos y elimiana la cuenta
     */
    @FXML
    void aceptarOnClick(){
        if (!comprobarTextField(escribaAceptarField) | !comprobarTextField(introduzcaContraseniaField)) {
            errorText.setText("los campos no pueden estar vacios ");
            return;
        }

        if (getUsuarioActual().getContrasenia() != introduzcaContraseniaField.getText() || escribaAceptarField.getText()!="Aceptar") {
            errorText.setText("los campos no coinciden");
            return;
        }

        try {
            getUsuarioServiceModel().eliminarUsuario(getUsuarioActual());
            cambiarPantalla(aceptarButton, "app-init", "eliminar");
            setUsuarioActual(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void atrasOnClick(){
        cambiarPantalla(atrasButton, "opcionesCuenta", "eliminar");
    }
}
