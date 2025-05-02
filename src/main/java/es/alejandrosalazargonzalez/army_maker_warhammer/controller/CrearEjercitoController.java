package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.EjercitoServiceModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

public class CrearEjercitoController extends AbstractController {
    
    @FXML ComboBox<String> faccionComboBox;
    @FXML ComboBox<String> ejercitoComboBox;
    @FXML ComboBox<String> subFaccionComboBox;
    @FXML TextField nombreEjercitoField;
    @FXML TextField nombreDelLiderField;
    @FXML TextField puntosDelLiderField;
    @FXML Button aceptarLiderButton;
    @FXML TextField nombreDeUnidadField;
    @FXML TextField puntosDeUnidadField;
    @FXML TextField numeroDeUnidadesField;
    @FXML TextField tipoDeUnidadField;
    @FXML Button aceptarUnidadButton;
    @FXML ToggleButton activarLimitePuntosToggle;
    @FXML Text limitePuntosText;
    @FXML TextField limitePuntosField;
    @FXML ListView listaEjercitoList;
    @FXML Button atrasButton;
    @FXML Button guardarEjercitoButton;

    @FXML
    void initialize(){
        List<String> facciones = new ArrayList<>(Arrays.asList("Impreio de la Humanidad","Eldars","Caos","Necrones","Tau","Ligas de Votan","Drukari","Tiranidos"));
        List<String> ejercitos = new ArrayList<>(Arrays.asList("nuevo"));
        List<String> subFacciones = new ArrayList<>(Arrays.asList("primero elige faccion"));
        faccionComboBox.getItems().addAll(facciones);
        ejercitoComboBox.getItems().addAll(ejercitos);
        subFaccionComboBox.getItems().addAll(subFacciones);
    }

    @FXML
    void elegirFaccion(){

    }

    @FXML
    void elegirEjercito(){

    }

    @FXML
    void elegirSubFaccion(){

    }

    @FXML
    void activarLimitePuntos(){
        limitePuntosText.setVisible(!limitePuntosText.isVisible());
        limitePuntosField.setVisible(!limitePuntosField.isVisible());
        
    }

    @FXML
    void atrasOnClick(){
        cambiarPantalla(atrasButton, "opciones", "crearEjercito");
    }

    @FXML
    void aceptarLiderOnClick(){
        
    }

    @FXML
    void aceptarUnidadOnClick(){

    }

    @FXML
    void guardarEjercitoOnClick(){

    }
}
