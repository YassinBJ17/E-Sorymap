package com.example.projet_gestion.controllers;

import com.example.projet_gestion.entity.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.projet_gestion.controllers.LoginController.*;

public class AccueilController implements Initializable {



    public static Project project;

    public Button user;
    public TextField nameprojet;
    public Button addProject;
    public Label alert;
    public Button deleteProject;
    public TableColumn<Project, String> TabNomProjet;
    public TableView<Project> tableview;
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        user.setText(loginUser.getNom() + " " + loginUser.getPrenom());


    }


    public void addProject(ActionEvent event) throws SQLException, InterruptedException {

        String sql = "INSERT INTO projet(nom_projet, idutilisateur) values(?,?)";

        if (Objects.equals(nameprojet.getText(), "")) {
            alert.setText("Veuillez remplir le nom du projet");
        } else {
            try {
                PreparedStatement ps = cnx.prepareStatement(sql);
                ps.setString(1, nameprojet.getText());
                ps.setInt(2, loginUser.getIdutilisateur());
                int res = ps.executeUpdate();
                if (res != 0) {
                    alert.setText("Success");


                }
            } catch (SQLException e) {
                alert.setText("Erreur");
                e.printStackTrace();
            }
        }
        select();
    }


    public void deleteProject(ActionEvent event) throws SQLException, InterruptedException {

            project = tableview.getSelectionModel().getSelectedItem();


            String sql="delete from projet where nom_projet = ? and idutilisateur= ? ";
            try {
                PreparedStatement ps=cnx.prepareStatement(sql);
                ps.setString(1,project.getNom_projet() );
                ps.setInt(2, loginUser.getIdutilisateur());
                int res=ps.executeUpdate();
                if(res!=0){

                }
            } catch (SQLException e) {
                alert.setText("Erreur du supression");
                e.printStackTrace();
            }
            select();
    }



    public void select() throws InterruptedException, SQLException {

        ObservableList<Project> projectList = FXCollections.observableArrayList();
        String sql="select * from projet where idutilisateur=?;";

        try {
                PreparedStatement ps = cnx.prepareStatement(sql);
                ps.setInt(1, loginUser.getIdutilisateur());
                ResultSet result=ps.executeQuery();

            while (result.next()){
                Project project=new Project(result.getString("idprojet"),
                        result.getString("nom_projet"),
                        result.getString("codeetat"),
                        result.getString("idutilisateur") );

                projectList.add(project);
            }

        }catch (SQLException e){
            e.printStackTrace();  }

        TabNomProjet.setCellValueFactory(new PropertyValueFactory<Project,String>("nom_projet"));
        tableview.setItems(projectList);
        alert.setText("");
    }

    public void consultProject(ActionEvent event) throws IOException {

        project = tableview.getSelectionModel().getSelectedItem();
        if(project==null)return;

        root = FXMLLoader.load(getClass().getResource("/com/example/projet_gestion/projet.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("E005 - Projet");
        stage.setScene(scene);
        stage.show();
    }

    public void logout(ActionEvent event) throws IOException  {
            root = FXMLLoader.load(getClass().getResource("/com/example/projet_gestion/logout.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
              stage.setTitle("0001 - Accueil");
            stage.setScene(scene);
            stage.show();
        }



}








