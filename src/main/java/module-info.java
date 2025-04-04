module es.alejandrosalazargonzalez.army_maker_warhammer{
    requires transitive javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires javafx.graphics;
    requires transitive java.sql;

    opens es.alejandrosalazargonzalez.army_maker_warhammer to javafx.fxml;
    exports es.alejandrosalazargonzalez.army_maker_warhammer;
    exports es.alejandrosalazargonzalez.army_maker_warhammer.config;
    exports es.alejandrosalazargonzalez.army_maker_warhammer.controller;
    exports es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas;
    exports es.alejandrosalazargonzalez.army_maker_warhammer.model;
    exports es.alejandrosalazargonzalez.army_maker_warhammer.model.abstractas;
    opens es.alejandrosalazargonzalez.army_maker_warhammer.controller to javafx.fxml;
}