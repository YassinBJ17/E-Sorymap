package com.example.projet_gestion.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ldap.learn.BpmnFlowListerGUI;
import ldap.learn.CSVParser;
import ldap.learn.Flux;
import ldap.learn.FluxListGUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.projet_gestion.controllers.AccueilController.project;
import static com.example.projet_gestion.controllers.LoginController.loginUser;

public class MFC_BPMNController implements Initializable {


    private Stage stage;
    private Scene scene;
    private Parent root;

    public Label labelMPMN;

    public Label labelMFC;
    public Button Check;

    public Label username;
    public Button projetBotton;
    public Button ImportBPMN;
    public Button ImportMFC;
    public ProgressIndicator importBPMN;
    public static File BPMNFile;

    public static File MFCFile;

    public ProgressIndicator importMFC;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        username.setText(loginUser.getNom() + " " + loginUser.getPrenom());
        projetBotton.setText(project.getNom_projet());


        ImportBPMN.setOnAction(event -> {
            // Open a file chooser dialog to allow the user to select a file
            FileChooser fileChooser = new FileChooser();
            BPMNFile = fileChooser.showOpenDialog(stage);

            if (BPMNFile != null) {
                // Display the selected file name in the Label
                importBPMN.setVisible(true);
                importBPMN.setProgress(0.2); // Sets the progress to 50%
                importBPMN.setProgress(0.4); // Sets the progress to 50%
                importBPMN.setProgress(0.6); // Sets the progress to 50%
                importBPMN.setProgress(0.8); // Sets the progress to 50%
                importBPMN.setProgress(1);
                labelMPMN.setText(BPMNFile.getName());
                if (!((BPMNFile==null||MFCFile==null)))
                    Check.setDisable(false);
            }
        });


        ImportMFC.setOnAction(event -> {
            // Open a file chooser dialog to allow the user to select a file
            FileChooser fileChooser = new FileChooser();
            MFCFile= fileChooser.showOpenDialog(stage);

            if (MFCFile != null) {
                // Display the selected file name in the Label
                importMFC.setVisible(true);
                importMFC.setProgress(0.2); // Sets the progress to 50%
                importMFC.setProgress(0.4); // Sets the progress to 50%
                importMFC.setProgress(0.6); // Sets the progress to 50%
                importMFC.setProgress(0.8); // Sets the progress to 50%
                importMFC.setProgress(1);
                labelMFC.setText(MFCFile.getName());
                if (!((BPMNFile==null||MFCFile==null)))
                    Check.setDisable(false);
            }
        });


        }








    public void back(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/com/example/projet_gestion/projet.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("E005 - Projet");
        stage.setScene(scene);
        stage.show();

    }




    static CSVParser csvParser = new CSVParser();
    static ldap.learn.BpmnFlowListerGUI bpmnFlowListerGUI = new ldap.learn.BpmnFlowListerGUI();

    static ArrayList<Flux> fluxMfc = CSVParser.getFluxMfc();
    static ArrayList<Flux> fluxBpmn = BpmnFlowListerGUI.getFluxBpmn();

    static ArrayList<Flux> missingFluxesInMfc = new ArrayList<>();
    public static ArrayList<Flux> getMissingFluxesInMfc() {
        return missingFluxesInMfc;
    }

    static ArrayList<Flux> missingFluxesInBpmn = new ArrayList<>();

    public static ArrayList<Flux> getMissingFluxesInBpmn() {
        return missingFluxesInBpmn;
    }



    public void compare(ActionEvent event) throws IOException {

            csvParser.getFluxofCSVFile(MFCFile);
            bpmnFlowListerGUI.importBpmnFile(BPMNFile);


            for (Flux flux : fluxMfc) {
                if (!fluxBpmn.stream().anyMatch(fluxBpmn -> fluxBpmn.getMessage().equalsIgnoreCase(flux.getMessage()))) {
                    missingFluxesInMfc.add(flux);
                }
            }

            for (Flux flux : fluxBpmn) {
                if (!fluxMfc.stream().anyMatch(fluxMfc -> fluxMfc.getMessage().equalsIgnoreCase(flux.getMessage()))) {
                    missingFluxesInBpmn.add(flux);
                }
            }

            //FluxListGUI fluxListGUI = new FluxListGUI(fluxMfc , fluxBpmn , missingFluxesInMfc , missingFluxesInBpmn);





        root = FXMLLoader.load(getClass().getResource("/com/example/projet_gestion/result.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("E005 - Result");
        stage.setScene(scene);
        stage.show();


            System.out.println(fluxBpmn);
            System.out.println(fluxMfc);


        }



}





