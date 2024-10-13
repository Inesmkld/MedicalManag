package org.example.dentelmanag.Services;

import org.example.dentelmanag.Model.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurService {

    // Simulation de la base de données avec une liste d'utilisateurs
    private List<Utilisateur> utilisateurs = new ArrayList<>();

    public UtilisateurService() {
        // Création de quelques utilisateurs en dur
        utilisateurs.add(new Utilisateur(1, "John", "Doe", "medecin", "john.doe@example.com", "0102030405", "medecin1", "password1", "Adresse 1"));
        utilisateurs.add(new Utilisateur(2, "Jane", "Doe", "infermier", "jane.doe@example.com", "0102030406", "infermier1", "password2", "Adresse 2"));
    }

    public Utilisateur login(String username, String password) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getUsername().equals(username) && utilisateur.getPassword().equals(password)) {
                return utilisateur; // Utilisateur trouvé
            }
        }
        return null; // Pas trouvé
    }
}
