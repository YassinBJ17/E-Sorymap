package com.example.projet_gestion.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.projet_gestion.controllers.AccueilController.project;
import static com.example.projet_gestion.controllers.LoginController.loginUser;

public class ProjetController implements Initializable {


    public Label username;
    public Button projetBotton;
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void initialize(URL url, ResourceBundle resourceBundle) {

        username.setText(loginUser.getNom() + " " + loginUser.getPrenom());
        projetBotton.setText(project.getNom_projet());
    }

    public void accueil(ActionEvent event) throws IOException {
       LoginController.accueil(event);
    }


    public void MFC_BPMN(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/projet_gestion/MFC_BPMN.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("E009 - Plage de MFC et BPMN");
        stage.setScene(scene);
        stage.show();
    }

    public void US_SM(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/projet_gestion/userstory storymap .fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("E006 - Plage de US et de la Story map");
        stage.setScene(scene);
        stage.show();
    }


}
