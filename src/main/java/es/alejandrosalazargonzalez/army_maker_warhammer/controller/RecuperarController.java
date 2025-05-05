
package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.UsuarioEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * @author: alejandrosalazargonzalez
 * @version: 1.0.0
 */
public class RecuperarController extends AbstractController {
    @FXML
    private Label iniciarText;
    @FXML
    private Label correoText;
    @FXML
    private TextField correoTextField;
    @FXML
    private Text errorText;
    @FXML
    private Button aceptarButton;
    @FXML
    private Text nuevoUsuarioText;
    @FXML
    private Button crearCuentaButton;
    @FXML
    private Button atrasButton;

    /**
     * comprueba que los campos sean validos y muestra la contraseña
     */
    @FXML
    void aceptarOnClick() {
        if (!comprobarTextField(correoTextField)) {
            errorText.setText("El correo no puede estar vacio");
            return;
        }
        UsuarioEntity usuario = getUsuarioServiceModel().obtenerUsuarioPorEmail(correoTextField.getText());
        if (usuario == null) {
            errorText.setText("No existe ningun usuario con ese correo");
            return;
        }
        errorText.setText("Su contraseña es: " + usuario.getContrasenia());
    }

    /**
     * va a la pantalla registrar
     */
    @FXML
    void logInToRegistrarOnClick() {
        cambiarPantalla(crearCuentaButton, "registrar", "recuperar");
    }

    /**
     * vuelve atras
     */
    @FXML
    void atrasOnClick() {
        cambiarPantalla(atrasButton);
    }
}
