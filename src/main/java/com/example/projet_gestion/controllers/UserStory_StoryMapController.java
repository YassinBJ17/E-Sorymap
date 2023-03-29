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

public class UserStory_StoryMapController implements Initializable {


    private Stage stage;
    private Scene scene;
    private Parent root;

    public Label username;
    public Button projetBotton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(loginUser.getNom() + " " + loginUser.getPrenom());
        projetBotton.setText(project.getNom_projet());
    }

    public void userStory(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/com/example/projet_gestion/Userstory .fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("E007 - Les US");
        stage.setScene(scene);
        stage.show();
    }
    public void storyMap(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/com/example/projet_gestion/storymap .fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("E008 - La storymap");
        stage.setScene(scene);
        stage.show();




    }



    public void generate(){


    }

    public void back(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/com/example/projet_gestion/projet.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("E005 - Projet");
        stage.setScene(scene);
        stage.show();

    }





}
