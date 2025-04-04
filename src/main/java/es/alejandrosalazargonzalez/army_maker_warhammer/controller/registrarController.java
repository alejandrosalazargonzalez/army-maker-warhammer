
package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.UsuarioEntity;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.UsuarioServiceModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public class registrarController extends AbstractController {
    
    @FXML private TextField usuarioTextField;
    @FXML private TextField nombreTextField;
    @FXML private TextField contraseniaTextField;
    @FXML private TextField contraseniaTextField2;
    @FXML private TextField correoTextField;
    @FXML private TextField correoTextField2;
    @FXML private Label errorText;

    @FXML
    private void onButtonAceptarRegClick(){
        if (usuarioTextField == null || usuarioTextField.toString().isEmpty()
        || nombreTextField == null || nombreTextField.toString().isEmpty()
        || contraseniaTextField == null || contraseniaTextField.toString().isEmpty()
        || contraseniaTextField2 == null || contraseniaTextField2.toString().isEmpty()
        || correoTextField == null || correoTextField.toString().isEmpty()
        || correoTextField2 == null || correoTextField2.toString().isEmpty()) {
            errorText.setText("ninguno de los valores puede estar vacio");
            return;
        }
        UsuarioEntity nuevoUsuario = new UsuarioEntity(usuarioTextField.getText(), correoTextField.getText(), nombreTextField.getText(),contraseniaTextField.getText());
        ArrayList<UsuarioEntity> usuarioEntityList;
        try {
            usuarioEntityList = getUsuarioServiceModel().obtenerUsarios();
        } catch (SQLException e) {
            e.printStackTrace();
            errorText.setText("error no controlado");
            return;
        }
        if (usuarioEntityList.contains(nuevoUsuario)) {
            errorText.setText("Ya hay una cuenta registrada con ese correo");
            return;
        }
        getUsuarioServiceModel().addUsuario(nuevoUsuario);
    }

}
