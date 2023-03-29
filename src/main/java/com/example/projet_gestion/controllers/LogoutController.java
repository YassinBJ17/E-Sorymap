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

public class LogoutController implements Initializable {


    private Stage stage;
    private Scene scene;
    private Parent root;
    public Label name;
    public Label email;
    public Button projetBotton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        name.setText("Name:"+ loginUser.getNom() + " " + loginUser.getPrenom());
        email.setText("Email:"+loginUser.getEmail());
    }

    public void logout(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/com/example/projet_gestion/login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("E001 - Accueil");
        stage.setScene(scene);
        stage.show();

    }

}
