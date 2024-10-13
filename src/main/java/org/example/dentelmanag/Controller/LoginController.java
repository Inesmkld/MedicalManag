package org.example.dentelmanag.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.dentelmanag.Model.Utilisateur;
import org.example.dentelmanag.Services.UtilisateurService;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button seconnecter;

    private UtilisateurService utilisateurService = new UtilisateurService(); // Service qui va gérer la connexion

    @FXML
    private void seconnecter() {
        String usernameInput = username.getText();
        String passwordInput = password.getText();

        Utilisateur utilisateur = utilisateurService.login(usernameInput, passwordInput);

        if (utilisateur != null) {
            // Utilisateur trouvé
            switch (utilisateur.getRole()) {
                case "medecin":
                    ouvrirMenuMedecin();
                    break;
                case "infermier":
                    ouvrirMenuInfirmier();
                    break;
                default:
                    afficherErreur("Rôle non reconnu");
            }
        } else {
            // Utilisateur non trouvé ou mot de passe incorrect
            afficherErreur("Nom d'utilisateur ou mot de passe incorrect");
        }
    }

    private void ouvrirMenuMedecin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/dentelmanag/FXML/Medecin/GestionPatients.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Médecin");
            stage.show();

            // Fermer la fenêtre de connexion
            Stage currentStage = (Stage) seconnecter.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ouvrirMenuInfirmier() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/dentelmanag/FXML/Infermier/MenuInfermier.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Infirmier");
            stage.show();

            // Fermer la fenêtre de connexion
            Stage currentStage = (Stage) seconnecter.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de connexion");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
