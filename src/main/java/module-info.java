module es.alejandrosalazargonzalez.army_maker_warhammer{
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires javafx.graphics;

    opens es.alejandrosalazargonzalez.army_maker_warhammer to javafx.fxml;
    exports es.alejandrosalazargonzalez.army_maker_warhammer;
    exports es.alejandrosalazargonzalez.army_maker_warhammer.controller;
    opens es.alejandrosalazargonzalez.army_maker_warhammer.controller to javafx.fxml;
}