package org.example.dentelmanag.Controller.Medecin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.dentelmanag.Model.Patient;
import org.example.dentelmanag.Services.PatientService;

import java.sql.Date;
import java.time.LocalDate;

public class EditPatientController {

    @FXML
    private TextArea btnachi;

    @FXML
    private TextField btnadresse;

    @FXML
    private TextArea btnall;

    @FXML
    private TextArea btnamed;

    @FXML
    private DatePicker btndaten;

    @FXML
    private Button btneditpatient;

    @FXML
    private TextField btnemail;

    @FXML
    private TextField btnlieu;

    @FXML
    private TextField btnnom;

    @FXML
    private TextField btnprenom;

    @FXML
    private TextField btntel;

    @FXML
    private ChoiceBox<String> gsanguin; // Type spécifié

    @FXML
    private TextArea hmaladie;

    @FXML
    private ChoiceBox<String> sexe; // Type spécifié

    @FXML
    private ChoiceBox<String> situation; // Type spécifié

    private Patient patient; // Patient à éditer

    private final PatientService patientService = new PatientService(); // Service pour gérer les patients

    // Cette méthode sera appelée depuis l'autre contrôleur pour initialiser le patient à modifier
    public void setPatient(Patient patient) {
        this.patient = patient;
        initializeFields();
    }

    // Initialiser les champs avec les informations du patient
    private void initializeFields() {
        if (patient != null) {
            btnnom.setText(patient.getNom());
            btnprenom.setText(patient.getPrenom());
            btndaten.setValue(patient.getDatenaissance().toLocalDate());
            btnlieu.setText(patient.getLieunaissance());
            btnadresse.setText(patient.getAdresse());
            btntel.setText(patient.getTel());
            btnemail.setText(patient.getEmail());
            btnamed.setText(patient.getAntecedentmedicaux());
            btnachi.setText(patient.getAntecedentchirugicaux());
            btnall.setText(patient.getAlergie());
            hmaladie.setText(patient.getHistoiremaladie());

            // Préremplir les listes déroulantes
            gsanguin.setValue(patient.getGroupesanguin());
            sexe.setValue(patient.getSexe());
            situation.setValue(patient.getSituation());
        }
    }

    // Appelé quand l'utilisateur appuie sur le bouton pour enregistrer les modifications
    @FXML
    void btneditpatient(ActionEvent event) {
        if (patient != null) {
            // Mettre à jour les informations du patient avec les nouvelles données
            patient.setNom(btnnom.getText());
            patient.setPrenom(btnprenom.getText());
            patient.setDatenaissance(Date.valueOf(btndaten.getValue()));
            patient.setLieunaissance(btnlieu.getText());
            patient.setAdresse(btnadresse.getText());
            patient.setTel(btntel.getText());
            patient.setEmail(btnemail.getText());
            patient.setAntecedentmedicaux(btnamed.getText());
            patient.setAntecedentchirugicaux(btnachi.getText());
            patient.setAlergie(btnall.getText());
            patient.setHistoiremaladie(hmaladie.getText());
            patient.setGroupesanguin(gsanguin.getValue());
            patient.setSexe(sexe.getValue());
            patient.setSituation(situation.getValue());

            // Enregistrer les modifications dans le service (cela peut impliquer une sauvegarde dans une base de données)
            patientService.updatePatient(patient);

            // Fermer la fenêtre une fois les modifications enregistrées (si applicable)
            ((Button) event.getSource()).getScene().getWindow().hide();
        }
    }

    @FXML
    public void initialize() {
        // Initialiser les options des listes déroulantes
        gsanguin.getItems().addAll("A+","A-", "B+","B-", "AB+","AB-", "O+","O-");
        sexe.getItems().addAll("F", "M");
        situation.getItems().addAll("Célibataire", "Marié", "Divorcé", "Veuf");
    }
}
