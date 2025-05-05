package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.EjercitoEntity;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.GeneralEntity;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.UnidadEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

public class CrearEjercitoController extends AbstractController {

    @FXML
    ComboBox<String> faccionComboBox;
    @FXML
    ComboBox<String> ejercitoComboBox;
    @FXML
    ComboBox<String> subFaccionComboBox;
    @FXML
    TextField nombreEjercitoField;
    @FXML
    TextField nombreDelLiderField;
    @FXML
    TextField puntosDelLiderField;
    @FXML
    Button aceptarLiderButton;
    @FXML
    TextField nombreDeUnidadField;
    @FXML
    TextField puntosDeUnidadField;
    @FXML
    TextField numeroDeUnidadesField;
    @FXML
    TextField tipoDeUnidadField;
    @FXML
    Button aceptarUnidadButton;
    @FXML
    ToggleButton activarLimitePuntosToggle;
    @FXML
    Text limitePuntosText;
    @FXML
    TextField limitePuntosField;
    @FXML
    ListView listaEjercitoList;
    @FXML
    Button atrasButton;
    @FXML
    Button guardarEjercitoButton;
    @FXML
    Text nombreEjercitoText;
    @FXML
    Text errorText;
    EjercitoEntity ejercito;

    @FXML
    void initialize() throws SQLException {
        List<String> facciones = new ArrayList<>(Arrays.asList("Impreio de la Humanidad", "Eldars", "Caos", "Necrones",
                "Tau", "Ligas de Votan", "Drukari", "Tiranidos"));
        List<String> ejercitos = new ArrayList<>(Arrays.asList("nuevo"));
        List<EjercitoEntity>misEjercitos = getEjercitoServiceModel().obtenerEjercitosDeUsuario(getUsuarioActual());
        for (EjercitoEntity ejercitoEntity : misEjercitos) {
            ejercitos.add(ejercitoEntity.getNombre());
        }
        List<String> subFacciones = new ArrayList<>(Arrays.asList("primero elige faccion"));
        faccionComboBox.getItems().addAll(facciones);
        ejercitoComboBox.getItems().addAll(ejercitos);
        subFaccionComboBox.getItems().addAll(subFacciones);
        ejercito = new EjercitoEntity();
    }

    /**
     * elige una faccion jugable
     */
    @FXML
    void elegirFaccion() {
        ejercito.setFaccion(faccionComboBox.getValue());
        List<String> subFacciones = new ArrayList<>(
                Arrays.asList("Marines espaciales", "Astra Militarum", "mucho mas"));
        subFaccionComboBox.getItems().addAll(subFacciones);
    }

    /**
     * elige un ejercito del usuario
     * @throws SQLException
     */
    @FXML
    void elegirEjercito() throws SQLException {
        if (ejercitoComboBox.getValue().equals("nuevo")) {
            nombreEjercitoField.setVisible(true);
            nombreEjercitoField.textProperty().addListener((observable, oldValue, newValue) -> 
                ejercito.setNombre(newValue));
            return;
        }
        nombreEjercitoField.setVisible(false);
        ejercito = getEjercitoServiceModel().buscarPorNombre(ejercitoComboBox.getValue());
        nombreEjercitoText.setText(ejercito.getNombre());
        listaEjercitoList.getItems().clear();
        listaEjercitoList.getItems().add(ejercito.toString());
    }

    /**
     * se elige una subfaccion
     */
    @FXML
    void elegirSubFaccion() {
        if (ejercito.getFaccion() == null) {
            return;
        }
        ejercito.setSubFaccion(subFaccionComboBox.getValue());
    }

    /**
     * activa un limite de puntos
     */
    @FXML
    void activarLimitePuntos() {
        limitePuntosText.setVisible(!limitePuntosText.isVisible());
        limitePuntosField.setVisible(!limitePuntosField.isVisible());
    }

    /**
     * cambia de pantalla a opciones
     */
    @FXML
    void atrasOnClick() {
        cambiarPantalla(atrasButton, "opciones", "crearEjercito");
    }

    /**
     * agrega un lider al ejercito del usuario
     */
    @FXML
    void aceptarLiderOnClick() {
        if (ejercito.getGeneral() != null) {
            errorText.setText("este este ejercito ya tiene general");
            return;
        }
        if (!comprobarTextField(nombreDelLiderField)) {
            errorText.setText("el general debe tener nombre");
            return;
        }
        if (!comprobarTextField(puntosDelLiderField)) {
            errorText.setText("el general debe tener puntos");
            return;
        }
        ejercito.setGeneral(
                new GeneralEntity(nombreDelLiderField.getText(), Integer.parseInt(puntosDelLiderField.getText())));
    }

    /**
     * si todos los datos son valido son correctos lo agrega al ejercito
     */
    @FXML
    void aceptarUnidadOnClick() {
        if (!comprobarTextField(nombreDeUnidadField)) {
            errorText.setText("la unidad debe tener nombre");
            return;
        }
        if (!comprobarTextField(puntosDeUnidadField)) {
            errorText.setText("la unidad debe tener puntos");
            return;
        }
        if (!comprobarTextField(numeroDeUnidadesField)) {
            errorText.setText("la unidad debe tener un numero de miniaturas");
            return;
        }
        if (!comprobarTextField(tipoDeUnidadField)) {
            errorText.setText("la unidad debe tener un tipo");
            return;
        }
        ejercito.addUnidad(
                new UnidadEntity(nombreDeUnidadField.getText(), Integer.parseInt(numeroDeUnidadesField.getText()),
                        Integer.parseInt(puntosDeUnidadField.getText()), tipoDeUnidadField.getText()));
    }

    /**
     * Guarda el ejercito en la base de datos enlazandolo con el usuario actual
     * @throws SQLException
     */
    @FXML
    void guardarEjercitoOnClick() throws SQLException {
        if (ejercito.getEjercito() == null || ejercito.getFaccion() == null 
            || ejercito.getGeneral() == null || ejercito.getSubFaccion() == null 
            || ejercito.getNombre() == null || ejercito.getNombre().trim().isEmpty()) {
            errorText.setText("No se puede guardar el ejército: faltan datos obligatorios");
            return;
        }
        
        try {
            if (ejercitoComboBox.getValue().equals("nuevo")) {
                if (nombreEjercitoField.getText().trim().isEmpty()) {
                    errorText.setText("Debe ingresar un nombre para el ejército");
                    return;
                }
                ejercito.setNombre(nombreEjercitoField.getText().trim());
                getEjercitoServiceModel().insertarEjercito(ejercito);
                errorText.setText("Ejército guardado correctamente");
            } else {
                if (getEjercitoServiceModel().actualizarEjercito(ejercito)) {
                    errorText.setText("Ejército actualizado correctamente");
                } else {
                    errorText.setText("Error al actualizar el ejército");
                }
            }
        } catch (SQLException e) {
            errorText.setText("Error de base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        listaEjercitoList.getItems().clear();
        listaEjercitoList.getItems().add(ejercito.toString());
    }
}
