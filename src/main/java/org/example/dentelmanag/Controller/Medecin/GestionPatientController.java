package org.example.dentelmanag.Controller.Medecin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.dentelmanag.Model.Patient;
import org.example.dentelmanag.Services.PatientService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GestionPatientController {

    @FXML
    private Button btndeletepatient;

    @FXML
    private Button btneditpatient;

    @FXML
    private Button btnnewpatient;

    @FXML
    private TableColumn<Patient, Integer> colid;  // Colonne pour l'ID

    @FXML
    private TableColumn<Patient, String> colnom;  // Colonne pour le nom

    @FXML
    private TableColumn<Patient, String> colprenom;  // Colonne pour le prénom

    @FXML
    private TableColumn<Patient, String> coldaten;  // Colonne pour la date de naissance

    @FXML
    private TableColumn<Patient, String> collieun;  // Colonne pour le lieu de naissance

    @FXML
    private TableColumn<Patient, String> colgsang;  // Colonne pour le groupe sanguin

    @FXML
    private TableColumn<Patient, String> coltel;  // Colonne pour le téléphone

    @FXML
    private TableColumn<Patient, String> colsit;  // Colonne pour la situation

    @FXML
    private TableView<Patient> patientTable;  // TableView des patients

    @FXML
    private TextField search;  // Champ de recherche

    private final PatientService patientService;

    public GestionPatientController() {
        // Initialisation du PatientService
        patientService = new PatientService();
    }

    // Méthode pour la recherche des patients
    @FXML
    void onSearch(ActionEvent event) {
        String searchText = search.getText().trim();

        if (!searchText.isEmpty()) {
            // Récupérer les patients correspondant à la recherche
            List<Patient> foundPatients = patientService.searchPatientsByName(searchText);

            // Mettre à jour la table avec les résultats
            patientTable.getItems().clear();
            patientTable.getItems().addAll(foundPatients);
        } else {
            // Si le champ de recherche est vide, afficher tous les patients
            afficherTousLesPatients();
        }
    }

    // Méthode pour supprimer un patient
    @FXML
    void ondeletepatient(ActionEvent event) {
        Patient selectedPatient = patientTable.getSelectionModel().getSelectedItem();

        if (selectedPatient != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce patient ?");
            alert.setContentText(selectedPatient.getNom() + " " + selectedPatient.getPrenom());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                patientService.deletePatient(selectedPatient.getId());
                afficherTousLesPatients();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Aucun patient sélectionné");
            alert.setContentText("Veuillez sélectionner un patient à supprimer.");
            alert.showAndWait();
        }
    }

    // Méthode pour modifier un patient
    @FXML
    void oneditpatient(ActionEvent event) {
        Patient selectedPatient = patientTable.getSelectionModel().getSelectedItem();

        if (selectedPatient != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/dentelmanag/FXML/Medecin/EditPatient.fxml"));
                Parent root = loader.load();

                // Passer les informations du patient sélectionné au contrôleur de la modification
                EditPatientController controller = loader.getController();
                controller.setPatient(selectedPatient);

                Stage stage = new Stage();
                stage.setTitle("Modifier Patient");
                stage.setScene(new Scene(root));
                stage.showAndWait();

                // Rafraîchir la liste des patients après modification
                afficherTousLesPatients();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Aucun patient sélectionné");
            alert.setContentText("Veuillez sélectionner un patient à modifier.");
            alert.showAndWait();
        }
    }

    // Méthode pour ajouter un nouveau patient
    @FXML
    void onnewpatient(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/dentelmanag/FXML/Medecin/AddPatient.fxml"));
            Parent root = loader.load();

            // Ouvrir la fenêtre pour ajouter un nouveau patient
            Stage stage = new Stage();
            stage.setTitle("Nouveau Patient");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Rafraîchir la liste des patients après ajout
            afficherTousLesPatients();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour afficher tous les patients dans la TableView
    private void afficherTousLesPatients() {
        patientTable.getItems().clear();
        patientTable.getItems().addAll(patientService.getAllPatients());
    }

    // Méthode d'initialisation pour configurer la TableView et ses colonnes
    @FXML
    public void initialize() {
        // Lier les colonnes aux propriétés correspondantes du modèle Patient
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        coldaten.setCellValueFactory(new PropertyValueFactory<>("datenaissance"));
        collieun.setCellValueFactory(new PropertyValueFactory<>("lieunaissance"));
        colgsang.setCellValueFactory(new PropertyValueFactory<>("groupesanguin"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colsit.setCellValueFactory(new PropertyValueFactory<>("situation"));

        // Afficher tous les patients au démarrage
        afficherTousLesPatients();
    }
}
