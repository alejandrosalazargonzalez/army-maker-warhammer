
package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author: alejandrosalazargonzalez
 * @version: 1.0.0
 */
public class OpcionesController extends AbstractController{

    @FXML private Button crearEjercitoButton;
    @FXML private Button crearPublicacionButton;
    @FXML private Button opcionesCuentaButton;
    @FXML private Button inicio;

    @FXML
    private void opcionesToCrearEjercitoOnClick(){
        cambiarPantalla(crearEjercitoButton, "crearEjercito", "opciones");
    }

    @FXML
    private void opcionesToCrearPubOnClick(){
        cambiarPantalla(crearPublicacionButton, "crearPublicacion", "opciones");
    }

    @FXML
    private void opcionesToOpcionesCuentaOnClick(){
        cambiarPantalla(opcionesCuentaButton, "opcionesCuenta", "opciones");
    }

    @FXML
    private void opcionToInicioOnClick(){
        cambiarPantalla(inicio, "app-init", "opciones");
    }

}
