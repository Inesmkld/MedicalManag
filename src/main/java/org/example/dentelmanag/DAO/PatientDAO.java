package org.example.dentelmanag.DAO;

import org.example.dentelmanag.Model.Patient;
import org.example.dentelmanag.Util.DB;
import org.example.dentelmanag.Util.SessionManager;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PatientDAO {

    // Créer un patient
    public void createPatient(Patient patient) {
        SessionManager.executeTransaction(session -> session.save(patient));
    }

    // Lire un patient par ID
    public Patient getPatientById(int id) {
        final Patient[] patient = new Patient[1];
        SessionManager.executeTransaction(session -> {
            patient[0] = session.get(Patient.class, id);
        });
        return patient[0];
    }

    // Lire tous les patients
    public List<Patient> getAllPatients() {
        final List<Patient>[] patients = new List[1];
        SessionManager.executeTransaction(session -> {
            patients[0] = session.createQuery("from Patient", Patient.class).list();
        });
        return patients[0];
    }

    // Mettre à jour un patient
    public void updatePatient(Patient patient) {
        SessionManager.executeTransaction(session -> session.update(patient));
    }

    // Supprimer un patient
    public void deletePatient(int id) {
        SessionManager.executeTransaction(session -> {
            Patient patient = session.get(Patient.class, id);
            if (patient != null) {
                session.delete(patient);
            }
        });
    }
    public List<Patient> findPatientsByName(String name) {
        String query = "SELECT * FROM patient WHERE nom LIKE ? OR prenom LIKE ?";

        try (Connection connection = DB.conDB();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, "%" + name + "%");
            stmt.setString(2, "%" + name + "%");

            ResultSet rs = stmt.executeQuery();
            List<Patient> patients = new ArrayList<>();

            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setDatenaissance(rs.getDate("datenaissance"));
                patient.setLieunaissance(rs.getString("lieunaissance"));
                patient.setSexe(rs.getString("sexe"));
                patient.setAdresse(rs.getString("adresse"));
                patient.setTel(rs.getString("tel"));
                patient.setEmail(rs.getString("email"));
                patient.setSituation(rs.getString("situation"));
                patient.setGroupesanguin(rs.getString("groupesanguin"));
                patient.setAntecedentmedicaux(rs.getString("antecedentmedicaux"));
                patient.setAntecedentchirugicaux(rs.getString("antecedentchirugicaux"));
                patient.setHistoiremaladie(rs.getString("histoiremaladie"));
                patient.setAlergie(rs.getString("alergie"));
                patient.setDateinscription(rs.getDate("dateinscription"));
                patients.add(patient);
            }


            return patients;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
