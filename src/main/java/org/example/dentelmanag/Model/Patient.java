package org.example.dentelmanag.Model;

import javax.persistence.*;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "patient", catalog = "javafx")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private Date datenaissance;
    private String lieunaissance;
    private String sexe;
    private String adresse;
    private String tel;
    private String email;
    private String situation;
    private String groupesanguin;
    private String antecedentmedicaux;
    private String antecedentchirugicaux;
    private String histoiremaladie;
    private String alergie ;
    private Date dateinscription;


    public Patient(String nom, String prenom, Date datenaissance, String lieunaissance, String sexe, String adresse, String tel, String email, String situation, String groupesanguin, String antecedentmedicaux, String antecedentchirugicaux, String histoiremaladie, String alergie, Date dateinscription) {
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.lieunaissance = lieunaissance;
        this.sexe = sexe;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.situation = situation;
        this.groupesanguin = groupesanguin;
        this.antecedentmedicaux = antecedentmedicaux;
        this.antecedentchirugicaux = antecedentchirugicaux;
        this.histoiremaladie = histoiremaladie;
        this.alergie = alergie;
        this.dateinscription = dateinscription;
    }

    // Existing constructor (with id)
    public Patient(int id, String nom, String prenom, Date datenaissance, String lieunaissance, String sexe, String adresse, String tel, String email, String situation, String groupesanguin, String antecedentmedicaux, String antecedentchirugicaux, String histoiremaladie, String alergie, Date dateinscription) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.lieunaissance = lieunaissance;
        this.sexe = sexe;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.situation = situation;
        this.groupesanguin = groupesanguin;
        this.antecedentmedicaux = antecedentmedicaux;
        this.antecedentchirugicaux = antecedentchirugicaux;
        this.histoiremaladie = histoiremaladie;
        this.alergie = alergie;
        this.dateinscription = dateinscription;
    }

    // No-argument constructor (required by JPA)
    public Patient() {
    }
}
