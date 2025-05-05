package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CrearPublicacionController extends AbstractController {
    @FXML Button atrasButton;

    /**
     * cambiar pantalla a opciones cuenta
     */
    @FXML
    void atrasOnClick(){
        cambiarPantalla(atrasButton);
    }
}
