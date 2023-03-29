package com.example.projet_gestion.controllers;

import com.example.projet_gestion.DataBase.DataBaseCnx;
import com.example.projet_gestion.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import static com.example.projet_gestion.controllers.LoginController.cnx;

public class AdduserController {

    // Stage, scene, and parent to switch between views
    private Stage stage;
    private Scene scene;
    private Parent root;

    // FXML fields for user input and feedback labels
    @FXML private TextField nom;
    @FXML private TextField email;
    @FXML private TextField prenom;
    @FXML private PasswordField password;
    @FXML private PasswordField password2;
    @FXML private Label alert;

    // Inserts a new user into the database when the 'Inscrire' button is clicked
    public void insert(ActionEvent event) {
        String sql = "INSERT INTO utilisateur(mot_de_passe, nom, prenom, email) values(?,?,?,?)";

        // Check if required fields are filled, show an alert if not
        if (prenom.getText().isEmpty() || email.getText().isEmpty() || nom.getText().isEmpty()) {
            alert.setText("Veuillez remplir tous les champs obligatoires");
        } else {
            // Check if passwords match, show an alert if not
            if (password2.getText().equals(password.getText())) {
                try {
                    // Insert user data into the database
                    PreparedStatement ps = cnx.prepareStatement(sql);
                    ps.setString(1, password.getText());
                    ps.setString(2, nom.getText());
                    ps.setString(3, prenom.getText());
                    ps.setString(4, email.getText());
                    int res = ps.executeUpdate();

                    // Show success message if insert was successful
                    if (res != 0) {
                        alert.setText("Merci ! l'inscription est réussie");
                    }
                } catch (SQLException e) {
                    // Show error message if insert failed
                    alert.setText("Erreur d'inscription");
                    e.printStackTrace();
                }
            } else {
                // Show alert if passwords do not match
                alert.setText("Le mot de passe de confirmation doit être identique.");
            }
        }
    }

    // Switch to the login view when the 'back' button is clicked
    public void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/projet_gestion/login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("0001 - Accueil");
        stage.setScene(scene);
        stage.show();
    }
}
