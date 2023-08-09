package Base;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class PatientSystem {
    private PatientDataBase patientDataBase = new PatientDataBase();
    private Prescriber prescriber = new Prescriber();

    public PatientSystem() {
        patientDataBase.setPatientSystem(this);
        prescriber.setPatientSystem(this);
        prescriber.start();
    }

    public void importSupportDisease(String fileName) {
        prescriber.importSupportDiseases(fileParse(fileName));
    }

    public void importPatientData(String fileName) {
        Patient[] patients = null;
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(fileName);
        try {
            patients = objectMapper.readValue(file, Patient[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        patientDataBase.setPatients(patients);
    }

    public void prescribe(String id, Symptom[] symptoms, String fileName, String fileType) {
        CompletableFuture.runAsync(() -> prescriber.prescribe(id, symptoms, fileName, fileType));
    }

    Patient findPatientById(String id) {
        return patientDataBase.find(id);
    }

    static List<String> getFieldNames() {
        Field[] fields = Prescription.class.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    static List<String> getPrescriptionFieldValues(Prescription prescription, List<String> fieldNames) {
        List<String> fieldValues = new ArrayList<>();
        for (String fieldName : fieldNames) {
            switch (fieldName) {
                case "name" -> fieldValues.add(prescription.getName());
                case "potentialDisease" -> fieldValues.add(prescription.getPotentialDisease());
                case "medicines" -> fieldValues.add(prescription.getMedicines());
                case "usage" -> fieldValues.add(prescription.getUsage());
                default -> System.out.println("Unsupported field: " + fieldName);
            }
        }
        return fieldValues;
    }

    public void saveCase(Patient patient, Symptom[] symptoms, List<Prescription> prescriptions) {
        patientDataBase.saveCase(patient, symptoms, prescriptions);
    }


    public List<String> fileParse(String fileName) {
        FileReader fileReader = null;
        ArrayList<String> data = new ArrayList<>();
        try {
            fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                data.add(bufferedReader.readLine());
            }
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
