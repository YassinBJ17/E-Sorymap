package com.example.projet_gestion.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class E_storyMapApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(E_storyMapApplication.class.getResource("/com/example/projet_gestion/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("0001 - Accueil");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}