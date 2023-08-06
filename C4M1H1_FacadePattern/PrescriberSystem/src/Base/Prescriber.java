package Base;

import DiseaseHandler.Attractive;
import DiseaseHandler.Covid19;
import DiseaseHandler.SleepApneaSyndrome;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Prescriber {
    private PatientSystem patientSystem;
    private List<DiseaseHandler> diseasesHandler;
    private BlockingQueue<PrescribeTask> queue;

    public Prescriber() {
        diseasesHandler = new ArrayList<>();
        queue = new ArrayBlockingQueue(10);
    }

    void importSupportDiseases(List<String> fileContents) {
        for (String fileContent : fileContents) {
            if (fileContent.equalsIgnoreCase("COVID-19")) {
                diseasesHandler.add(new Covid19());
            } else if (fileContent.equalsIgnoreCase("Attractive")) {
                diseasesHandler.add(new Attractive());
            } else if (fileContent.equalsIgnoreCase("SleepApneaSyndrome")) {
                diseasesHandler.add(new SleepApneaSyndrome());
            }
        }
    }
    public List<Prescription> run() {
        while (true) {
            try {
                PrescribeTask task = queue.take();
                System.out.printf("patient name: %s 正在診斷中\n", task.getPatient().getName());
                saveCase(task.getPatient(), task.getSymptoms(), task.handle());
                return task.handle();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void prescribe(Patient patient, Symptom[] symptoms)  {
        PrescribeTask prescribeTask = new PrescribeTask(diseasesHandler, patient, symptoms);
        try {
            queue.put(prescribeTask);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void setPatientSystem(PatientSystem patientSystem) {
        this.patientSystem = patientSystem;
    }
    private void saveCase(Patient patient, Symptom[] symptoms, List<Prescription> prescriptions) {
        patientSystem.saveCase(patient, symptoms, prescriptions);
    }
}
