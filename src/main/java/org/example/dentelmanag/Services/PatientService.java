package org.example.dentelmanag.Services;

import org.example.dentelmanag.Model.Patient;
import org.example.dentelmanag.DAO.PatientDAO;

import java.util.List;

public class PatientService {
    private final PatientDAO patientDAO;

    public PatientService() {
        this.patientDAO = new PatientDAO();
    }

    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    public void addPatient(Patient patient) {
        patientDAO.createPatient(patient);
    }

    public void updatePatient(Patient patient) {
        patientDAO.updatePatient(patient);
    }

    public void deletePatient(int id) {
        patientDAO.deletePatient(id);
    }
    public List<Patient> searchPatientsByName(String name) {
        return patientDAO.findPatientsByName(name);
    }

}
