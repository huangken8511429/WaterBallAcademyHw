package Base;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PatientDataBase {
    private Patient[] patients;
    private PatientSystem patientSystem;

    protected void setPatients(Patient[] patients) {
        this.patients = patients;
    }

    public void saveCase(Patient patient, Symptom[] symptoms, List<Prescription> prescriptions) {
        for (Prescription prescription : prescriptions) {
            patient.addCase(new Case(LocalDateTime.now().toString(), symptoms, prescription));
        }
    }

    public Patient find(String id) {
        return Arrays.stream(patients)
                .filter(patient -> patient.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
    }

    public void setPatientSystem(PatientSystem patientSystem) {
        this.patientSystem = patientSystem;
    }
}
