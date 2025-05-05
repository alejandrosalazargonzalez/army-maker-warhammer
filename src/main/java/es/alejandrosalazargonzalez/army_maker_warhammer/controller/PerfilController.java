
package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.EjercitoEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

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
    private ComboBox<String> ejercitoComboBox;
    @FXML
    private ListView<String> ejercitosListView;
    @FXML
    private Text usuarioUsuarioText;
    @FXML
    private Text nombreUsuarioText;
    @FXML
    private Text correoUsuairoText;

    @FXML
    void initialize() {
        usuarioUsuarioText.setText(getUsuarioActual().getUsuario());
        nombreUsuarioText.setText(getUsuarioActual().getNombre());
        correoUsuairoText.setText(getUsuarioActual().getEmail());
        List<EjercitoEntity> ejercitos = new ArrayList<>();
        try {
            ejercitos = getEjercitoServiceModel().obtenerEjercitosDeUsuario(getUsuarioActual());
            if (!ejercitos.isEmpty()) {
                for (EjercitoEntity ejercitoEntity : ejercitos) {
                    ejercitoComboBox.getItems().add(ejercitoEntity.getNombre());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * va a la pantalla de opciones
     */
    @FXML
    public void optionButtonOnClick() {
        cambiarPantalla(optionButton, "opciones", "perfil");
    }

    /**
     * vuelve hacia atras
     */
    @FXML
    public void atrasOnClick() {
        cambiarPantalla(atrasButton);
    }

    /**
     * Elige un ejercito del usuario
     */
    @FXML
    public void elegirEjercito(){
        ejercitosListView.getItems().clear();
        try {
            ejercitosListView.getItems().add(getEjercitoServiceModel().buscarPorNombre(ejercitoComboBox.getValue()).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
