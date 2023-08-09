package Base;

import DiseaseHandler.Attractive;
import DiseaseHandler.Covid19;
import DiseaseHandler.SleepApneaSyndrome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Prescriber extends Thread {
    private PatientSystem patientSystem;
    private final List<Disease> supportDiseases;
    private final BlockingQueue<PrescribeTask> queue;
    private final Map<String, Disease> map;

    public Prescriber() {
        supportDiseases = new ArrayList<>();
        queue = new ArrayBlockingQueue<>(10);
        map = new HashMap<>();
        map.put("COVID-19", new Covid19());
        map.put("Attractive", new Attractive());
        map.put("SleepApneaSyndrome", new SleepApneaSyndrome());
    }

    void importSupportDiseases(List<String> fileContents) {
        for (String fileContent : fileContents) {
            if (map.containsKey(fileContent)) {
                supportDiseases.add(map.get(fileContent));
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                PrescribeTask task = queue.take();
                System.out.printf("patient name: %s 正在診斷中\n", task.getPatient().getName());
                saveCase(task.getPatient(), task.getSymptoms(), task.handle());
                task.exportData();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                if (queue.isEmpty()) {
                    System.exit(0);
                }
            }
        }
    }

    public void prescribe(String id, Symptom[] symptoms, String fileName, String fileType) {
        Patient patient = patientSystem.findPatientById(id);
        System.out.printf("patient name: %s 正在排隊中\n", patient.getName());
        PrescribeTask prescribeTask = new PrescribeTask(supportDiseases, patient, symptoms, fileName, fileType);
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
