package Base;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PatientSystemFacade {
    private PatientSystem patientSystem = new PatientSystem();

    public void importPatientData(String fileName) {
        patientSystem.importPatientData(fileName);
    }

    public void importSupportDiseases(String fileName) {
        patientSystem.importSupportDisease(fileName);
    }

    public void prescribe(String id, Symptom[] symptoms, String saveFileName, String fileType) {
        CompletableFuture.runAsync(() -> patientSystem.prescribe(id, symptoms));
        List<Prescription> run = patientSystem.run();
        patientSystem.exportData(id, saveFileName, fileType, run);
    }
}
