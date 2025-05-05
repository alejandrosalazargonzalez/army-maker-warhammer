
package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * @author: alejandrosalazargonzalez
 * @version: 1.0.0
 */
public class PostsController extends AbstractController {

    @FXML
    private Button imageProfileButton;
    @FXML
    private Button optionButton;
    @FXML
    private Text miUsuarioText;

    @FXML
    void initialize(){
        miUsuarioText.setText(getUsuarioActual().getUsuario());
    }

    /**
     * va a la pantalla del perfil
     */
    @FXML
    public void postToProfileOnCliclk() {
        cambiarPantalla(imageProfileButton, "perfil", "posts");
    }

    /**
     * va a la pantalla de las opciones
     */
    @FXML
    public void optionButtonOnClick() {
        cambiarPantalla(optionButton, "opciones", "posts");
    }

}
