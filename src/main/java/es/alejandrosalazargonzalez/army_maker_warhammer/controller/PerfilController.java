
package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author: alejandrosalazargonzalez
 * @version: 1.0.0
 */
public class PerfilController extends AbstractController {
    @FXML
    private Button atrasButton;
    @FXML
    private Button optionButton;

    @FXML
    public void optionButtonOnClick() {
        cambiarPantalla(optionButton, "opciones", "perfil");
    }

    @FXML
    public void atrasOnClick() {
        cambiarPantalla(atrasButton);
    }
}
