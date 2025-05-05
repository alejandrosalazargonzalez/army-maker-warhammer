package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public class OpcionesCuentaController  extends AbstractController{
    @FXML Button atrasButton;
    @FXML Button eliminarCuentaButton;
    @FXML Button cambiarUsuarioButton;
    @FXML Button cambiarNombreButton;
    @FXML Button cambiarContraseniaButton;
    @FXML Button cambiarCorreoButton;

    @FXML void atrasOnClick(){
        cambiarPantalla(atrasButton, "opciones", "opcionesCuenta");
    }

    @FXML void elimnarCuentaOnClick(){
        cambiarPantalla(atrasButton, "eliminar", "opcionesCuenta");
    }

    @FXML void cambiarUsuarioOnClick(){
        cambiarPantalla(atrasButton, "cambiarUsuario", "opcionesCuenta");
    }

    @FXML void cambiarNombreOnClick(){
        cambiarPantalla(atrasButton, "cambiarNombre", "opcionesCuenta");
    }

    @FXML void cambiarContraseniaOnClick(){
        cambiarPantalla(atrasButton, "cambiarContrasenia", "opcionesCuenta");
    }

    @FXML void cambiarCorreoOnClick(){
        cambiarPantalla(atrasButton, "cambiarCorreo", "opcionesCuenta");
    }
}
