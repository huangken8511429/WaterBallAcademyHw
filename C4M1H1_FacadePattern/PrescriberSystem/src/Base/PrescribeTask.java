package Base;

import java.util.ArrayList;
import java.util.List;

public class PrescribeTask {
    private List<DiseaseHandler> diseasesHandler;
    private Patient patient;
    private Symptom[] symptoms;

    public PrescribeTask(List<DiseaseHandler> diseasesHandler, Patient patient, Symptom[] symptoms) {
        this.diseasesHandler = diseasesHandler;
        this.patient = patient;
        this.symptoms = symptoms;
    }

    List<Prescription> handle() {
        List<Prescription> prescriptions = new ArrayList<>();
        try {
            Thread.sleep(3000);
            for (DiseaseHandler diseaseHandler : diseasesHandler) {
                Prescription prescription = diseaseHandler.handle(patient, symptoms);
                if (prescription != null) {
                    prescriptions.add(prescription);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return prescriptions;
    }

    public Patient getPatient() {
        return patient;
    }

    public Symptom[] getSymptoms() {
        return symptoms;
    }
}
