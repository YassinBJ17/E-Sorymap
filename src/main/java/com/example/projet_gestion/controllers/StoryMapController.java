package com.example.projet_gestion.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.projet_gestion.controllers.AccueilController.project;
import static com.example.projet_gestion.controllers.LoginController.cnx;
import static com.example.projet_gestion.controllers.LoginController.loginUser;

public class StoryMapController implements Initializable {


    @FXML
    private GridPane grid;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public Label username;
    public Button projetBotton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(loginUser.getNom() + " " + loginUser.getPrenom());
        projetBotton.setText(project.getNom_projet());



        String sql = "SELECT epic.titreEpic, besoin.libelleBesoin \n" +
                "FROM projet \n" +
                "JOIN userstory ON projet.idProjet = userstory.idProjet \n" +
                "JOIN epic ON userstory.idEpic = epic.idEpic \n" +
                "JOIN besoin ON userstory.idBesoin = besoin.idBesoin \n" +
                "WHERE projet.nom_projet = ? \n" +
                "GROUP BY epic.titreEpic, besoin.libelleBesoin";

        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, project.getNom_projet());
            ResultSet result=ps.executeQuery();

            String epic1="";
            int i=0,j=-1;
            while (result.next()){


                if (!Objects.equals(epic1, result.getString("titreepic"))){
                    j++;
                    i=0;
                    epic1=result.getString("titreepic");
                    Label label = new Label(epic1);
                    grid.setRowIndex(label, i);
                    grid.setColumnIndex(label, j);
                    grid.getChildren().add(label);
                    i++;

                }

                    Label label2 = new Label(result.getString("libellebesoin"));
                    grid.setRowIndex(label2, i);
                    grid.setColumnIndex(label2, j);
                    grid.getChildren().add(label2);
                    i++;

                }

            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

        public void back(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/com/example/projet_gestion/Userstory storymap .fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("E006 - Plage de US et de la Story map");
        stage.setScene(scene);
        stage.show();

    }

}
