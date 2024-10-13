package org.example.dentelmanag.Model;

import javax.persistence.*;


@Entity
@Table(name = "utilisateur", catalog = "javafx")


public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String role;
    private String email;
    private String tel;
    private String username;
    private String password;
    private String adresse;

    // Constructeur sans argument (nécessaire pour Hibernate)
    public Utilisateur() {}

    // Constructeur avec paramètres sans ID
    public Utilisateur(String nom, String prenom, String role, String email, String tel, String username, String password, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.email = email;
        this.tel = tel;
        this.username = username;
        this.password = password;
        this.adresse = adresse;
    }

    // Constructeur avec paramètres incluant ID (pour usage général)
    public Utilisateur(int id, String nom, String prenom, String role, String email, String tel, String username, String password, String adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.email = email;
        this.tel = tel;
        this.username = username;
        this.password = password;
        this.adresse = adresse;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
