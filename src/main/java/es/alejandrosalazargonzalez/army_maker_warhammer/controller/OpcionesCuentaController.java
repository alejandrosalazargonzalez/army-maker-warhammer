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

    /**
     * vuelve a opciones
     */
    @FXML void atrasOnClick(){
        cambiarPantalla(atrasButton, "opciones", "opcionesCuenta");
    }

    /**
     * va a eliminar cuenta
     */
    @FXML void elimnarCuentaOnClick(){
        cambiarPantalla(atrasButton, "eliminar", "opcionesCuenta");
    }

    /**
     * va a cambiar usuario
     */
    @FXML void cambiarUsuarioOnClick(){
        cambiarPantalla(atrasButton, "cambiarUsuario", "opcionesCuenta");
    }

    /**
     * va a cambiar nombre
     */
    @FXML void cambiarNombreOnClick(){
        cambiarPantalla(atrasButton, "cambiarNombre", "opcionesCuenta");
    }

    /**
     * va a cambiar contrasenia
     */
    @FXML void cambiarContraseniaOnClick(){
        cambiarPantalla(atrasButton, "cambiarContrasenia", "opcionesCuenta");
    }

    /**
     * va a cambiar correo
     */
    @FXML void cambiarCorreoOnClick(){
        cambiarPantalla(atrasButton, "cambiarCorreo", "opcionesCuenta");
    }
}
