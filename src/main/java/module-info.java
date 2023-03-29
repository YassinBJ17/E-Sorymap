module com.example.projet_gestion {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    requires camunda.bpmn.model;
    requires camunda.xml.model;
    requires com.opencsv;

    opens com.example.projet_gestion.entity to javafx.base;
    opens com.example.projet_gestion to javafx.fxml;
    exports com.example.projet_gestion.app;
    opens com.example.projet_gestion.app to javafx.fxml;
    exports com.example.projet_gestion.controllers;
    opens com.example.projet_gestion.controllers to javafx.fxml;
    opens com.example.projet_gestion.DataBase to javafx.fxml;
    exports ldap.learn;
    opens ldap.learn to javafx.fxml;
}