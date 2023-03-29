package com.example.projet_gestion.controllers;
import com.example.projet_gestion.entity.User;
import com.example.projet_gestion.DataBase.DataBaseCnx;
import javafx.fxml.Initializable;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
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
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.projet_gestion.DataBase.DataBaseCreation.createDatabase;

// LoginController is the class responsible for handling login-related functionality in the application.
public class LoginController implements Initializable {
    // Variables for accessing the user object and the database connection.
    public static User loginUser;
    public static Connection cnx;

    // Variables for managing the UI.
    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    public Label logerror;


    // UI components for the login form.
    @FXML
    private TextField EmailField;
    @FXML
    private PasswordField PassField;
    @FXML
    private Label loginError;

    // This method is called when the login form is initialized.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Connect to the database using the database connection object.
        DataBaseCnx dataBase=new DataBaseCnx();
        try {
            cnx = dataBase.getCnx();

        } catch (SQLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    // This method is called when the user clicks the "submit" button on the login form.
    @FXML
    void submit(ActionEvent event) {
        // Check if the email and password fields are empty.
        if (EmailField.getText().isBlank() || PassField.getText().isBlank()) {
            loginError.setText("Veuillez saisir l'identifiant et le mot de passe");
        } else {
            // Create a SQL query to check if the user exists in the database.
            String sql = "SELECT * FROM utilisateur WHERE email=? AND mot_de_passe=?";

            try (PreparedStatement ps = cnx.prepareStatement(sql)) {
                // Set the email and password parameters in the SQL query.
                ps.setString(1, EmailField.getText());
                ps.setString(2, PassField.getText());

                try (ResultSet res = ps.executeQuery()) {
                    if (res.next()) {
                        // If the user exists, create a new User object and store the user's data in it.
                        loginUser = new User(res.getString("idutilisateur"), res.getString("mot_de_passe"),
                                res.getString("nom"), res.getString("prenom"), res.getString("email"));

                        // Print the user's name and email to the console (for debugging purposes).
                        System.out.println("login name " + loginUser.getNom()+" email "+loginUser.getEmail());

                        // Switch to the "accueil" scene.
                        accueil(event);
                    } else {
                        // If the user doesn't exist, display an error message.
                        loginError.setText("L'identifiant ou le mot de passe saisi est incorrect.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // This method is called when the user clicks the "add user" button on the login form.
    public void addUser(ActionEvent event) throws IOException {
        // Switch to the "adduser" scene.
        root = FXMLLoader.load(getClass().getResource("/com/example/projet_gestion/adduser.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("E002 - Création d'un nouveau compte");
        stage.setScene(scene);
        stage.show();
    }

    // This method is called when the user successfully logs in and needs to be directed to the home page.
    public static void accueil(ActionEvent event) throws IOException {
        // Switch to the "accueil" scene.
        root = FXMLLoader.load(LoginController.class.getResource("/com/example/projet_gestion/accueil.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("E003 - Tableau de bord");
        stage.setScene(scene);
        stage.show();
    }
}
