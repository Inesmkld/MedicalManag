package org.example.dentelmanag.Controller.Medecin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.dentelmanag.Model.Patient;
import org.example.dentelmanag.Services.PatientService;

import java.sql.Date;

public class AddPatientController {

    @FXML
    private TextArea btnachi;

    @FXML
    private Button btnaddpatient;

    @FXML
    private TextField btnadresse;

    @FXML
    private TextArea btnall;

    @FXML
    private TextArea btnamed;

    @FXML
    private DatePicker btndaten;

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
    private ChoiceBox<String> gsanguin; // Utiliser String pour le type
    @FXML
    private TextArea hmaladie;

    @FXML
    private ChoiceBox<String> sexe; // Utiliser String pour le type

    @FXML
    private ChoiceBox<String> situation; // Utiliser String pour le type

    // Instance du service
    private PatientService patientService = new PatientService();

    @FXML
    void btnaddpatient(ActionEvent event) {
        try {
            // 1. Récupérer les valeurs des champs
            String nom = btnnom.getText();
            String prenom = btnprenom.getText();
            Date dateNaissance = Date.valueOf(btndaten.getValue()); // Conversion LocalDate to SQL Date
            String adresse = btnadresse.getText();
            String lieu = btnlieu.getText();
            String tel = btntel.getText();
            String email = btnemail.getText();
            String sexeValue = sexe.getValue();
            String gsanguinValue = gsanguin.getValue();
            String situationValue = situation.getValue();
            String antecedentsMedicaux = btnamed.getText();
            String antecedentsChirurgicaux = btnachi.getText();
            String allergies = btnall.getText();
            String histoireMaladie = hmaladie.getText();
            Date dateInscription = new Date(System.currentTimeMillis()); // La date actuelle

            // 2. Créer un nouvel objet Patient
            Patient newPatient = new Patient(nom, prenom, dateNaissance,lieu, sexeValue, adresse, tel, email, situationValue, gsanguinValue, antecedentsMedicaux, antecedentsChirurgicaux, histoireMaladie, allergies, dateInscription);

            // 3. Sauvegarder le patient en base via le service
            patientService.addPatient(newPatient);

            // 4. Afficher un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Le patient a été ajouté avec succès !");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            // Afficher un message d'erreur en cas de problème
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de l'ajout du patient !");
            alert.showAndWait();
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
