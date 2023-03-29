package com.example.projet_gestion.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ldap.learn.Flux;
import java.io.IOException;

import static com.example.projet_gestion.controllers.AccueilController.project;
import static com.example.projet_gestion.controllers.LoginController.loginUser;
import static com.example.projet_gestion.controllers.MFC_BPMNController.*;

public class ResultController {

    @FXML
    private TableView<Flux> mfcTable;

    @FXML
    private TableColumn<Flux, String> mfcMessageCol;

    @FXML
    private TableColumn<Flux, String> mfcEmitterCol;

    @FXML
    private TableColumn<Flux, String> mfcReceiverCol;

    @FXML
    private TableView<Flux> bpmnTable;

    @FXML
    private TableColumn<Flux, String> bpmnMessageCol;

    @FXML
    private TableColumn<Flux, String> bpmnEmitterCol;

    @FXML
    private TableColumn<Flux, String> bpmnReceiverCol;



    private Stage stage;
    private Scene scene;
    private Parent root;

    public Label username;
    public Button projetBotton;

    public void initialize() {
        mfcMessageCol.setCellValueFactory(new PropertyValueFactory<>("message"));
        mfcEmitterCol.setCellValueFactory(new PropertyValueFactory<>("emetteur"));
        mfcReceiverCol.setCellValueFactory(new PropertyValueFactory<>("recepteur"));

        bpmnMessageCol.setCellValueFactory(new PropertyValueFactory<>("message"));
        bpmnEmitterCol.setCellValueFactory(new PropertyValueFactory<>("emetteur"));
        bpmnReceiverCol.setCellValueFactory(new PropertyValueFactory<>("recepteur"));

        System.out.println(fluxMfc.size());
        System.out.println(fluxBpmn.size());

        username.setText(loginUser.getNom() + " " + loginUser.getPrenom());
        projetBotton.setText(project.getNom_projet());



        mfcTable.getItems().setAll(fluxMfc);
        bpmnTable.getItems().setAll(fluxBpmn);

        mfcTable.setRowFactory(tv -> {
            return new TableRow<Flux>() {
                @Override
                protected void updateItem(Flux flux, boolean empty) {
                    super.updateItem(flux, empty);
                    if (empty) {
                        setStyle("");
                    } else if (missingFluxesInMfc.contains(flux)) {
                        setStyle("-fx-background-color: #c53535;");
                    } else {
                        setStyle("-fx-background-color: #48b044;");
                    }
                }
            };
        });

        bpmnTable.setRowFactory(tv -> {
            return new TableRow<Flux>() {
                @Override
                protected void updateItem(Flux flux, boolean empty) {
                    super.updateItem(flux, empty);
                    if (empty) {
                        setStyle("");
                    } else if (missingFluxesInBpmn.contains(flux)) {
                        setStyle("-fx-background-color: #c53535;");
                    } else {
                        setStyle("-fx-background-color: #48b044;");
                    }
                }
            };
        });

    //    mfcLabel.setText(String.format("Les flux du MFC (%d)", mfcList.size()));
     //   bpmnLabel.setText(String.format("Les flux du BPMN (%d)", bpmnList.size()));
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
