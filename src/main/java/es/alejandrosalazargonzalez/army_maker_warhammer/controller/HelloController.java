package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    
    @FXML
    private Label iniciarText;

    @FXML
    protected void onHelloButtonClick() {
        iniciarText.setText("¡Bienvenidos al mundo de la programación!");
    }
}