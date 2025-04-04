
package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.alejandrosalazargonzalez.army_maker_warhammer.PrincipalApplication;
import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.UsuarioEntity;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.UsuarioServiceModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public class registrarController extends AbstractController {
    
    @FXML private TextField usuarioTextField;
    @FXML private TextField nombreTextField;
    @FXML private TextField contraseniaTextfield;
    @FXML private TextField contraseniaTextfield2;
    @FXML private TextField correoTextField;
    @FXML private TextField correoTextField2;
    @FXML private Label errorText;
    @FXML private Button buttonAcetarRegistrar;
    @FXML private Button buttonAtras;

    @FXML
    private void onButtonAceptarRegClick(){
        if(usuarioTextField == null || usuarioTextField.getText().isEmpty()){
            errorText.setText("Usuario no puede estar vacio");
            return;
        }
        if(nombreTextField == null || nombreTextField.getText().isEmpty()){
            errorText.setText("Nombre no puede estar vacio");
            return;
        }
        if(contraseniaTextfield == null || contraseniaTextfield.getText().isEmpty()){
            errorText.setText("Contrasenia no puede estar vacio");
            return;
        }
        if(contraseniaTextfield2 == null || contraseniaTextfield2.getText().isEmpty()){
            errorText.setText("Repetir contrasenia no puede estar vacio");
            return;
        }
        if (!contraseniaTextfield.getText().equals(contraseniaTextfield2.getText()) ) {
            errorText.setText("La contrasenia repetida debe ser igual");
            return;
        }
        if(correoTextField == null || correoTextField.getText().isEmpty()){
            errorText.setText("El correo no puede estar vacio");
            return;
        }
        if(correoTextField2 == null || correoTextField2.getText().isEmpty()) {
            errorText.setText("Correo repetir de los valores puede estar vacio");
            return;
        }
        if (!correoTextField.getText().equals(correoTextField2.getText()) ) {
            errorText.setText("Los correos deben ser iguales");
            return;
        }
        UsuarioEntity nuevoUsuario = new UsuarioEntity(usuarioTextField.getText(), correoTextField.getText(), nombreTextField.getText(),contraseniaTextfield.getText());
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

    @FXML
    private void registrarToLoginOnClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("app-init.fxml"));
            Stage stage = (Stage) buttonAtras.getScene().getWindow();
            Scene scene;
            scene = new Scene(fxmlLoader.load(), 510, 900);
            stage.setResizable(false);
            stage.setTitle("Pantalla Princial");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
