
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
    @FXML private Button atrasButton;

    /**
     * cambia a la pantalla crear ejercito
     */
    @FXML
    private void opcionesToCrearEjercitoOnClick(){
        cambiarPantalla(crearEjercitoButton, "crearEjercito", "opciones");
    }

    /**
     * cambia a la pantalla crear
     */
    @FXML
    private void opcionesToCrearPubOnClick(){
        cambiarPantalla(crearPublicacionButton, "crearPublicacion", "opciones");
    }

    /**
     * va a las opciones de cuenta
     */
    @FXML
    private void opcionesToOpcionesCuentaOnClick(){
        cambiarPantalla(opcionesCuentaButton, "opcionesCuenta", "opciones");
    }

    /**
     * va a la pantalla de posts
     */
    @FXML
    private void opcionToInicioOnClick(){
        cambiarPantalla(inicio, "posts", "opciones");
    }

    /**
     * vuelve a la pantalla anterior
     */
    @FXML
    private void atrasOnClick(){
        cambiarPantalla(atrasButton);
    }
}
