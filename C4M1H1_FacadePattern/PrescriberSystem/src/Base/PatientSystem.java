package Base;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatientSystem {
    private PatientDataBase patientDataBase = new PatientDataBase();
    private Prescriber prescriber = new Prescriber();

    public PatientSystem() {
        patientDataBase.setPatientSystem(this);
        prescriber.setPatientSystem(this);
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

    public void prescribe(String id, Symptom[] symptoms) {
        Patient patient = patientDataBase.find(id);
        prescriber.prescribe(patient, symptoms);
    }

    public void exportData(String id, String saveFileName, String fileType, List<Prescription> prescriptions) {
        Patient patient = patientDataBase.find(id);
        String directoryPath = "data/" + patient.getId();
        Path path = Paths.get(directoryPath);

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String filePath = directoryPath + "/" + saveFileName + "." + fileType;
        try {
            switch (fileType) {
                case "json":
                    String json = new ObjectMapper().writeValueAsString(prescriptions);
                    Files.write(Paths.get(filePath), json.getBytes());
                    break;

                case "csv":
                    FileWriter csvFileWriter = new FileWriter(filePath);

                    List<String> fieldNames = getFieldNames();

                    List<List<String>> data = prescriptions.stream()
                            .map(prescription -> getPrescriptionFieldValues(prescription, fieldNames))
                            .collect(Collectors.toList());

                    csvFileWriter.append(String.join(",", fieldNames)).append("\n");

                    for (List<String> row : data) {
                        csvFileWriter.append(String.join(",", row)).append("\n");
                    }

                    csvFileWriter.flush();
                    csvFileWriter.close();
                    break;

                default:
                    System.out.println("Unsupported filetype: " + fileType);
                    break;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> getFieldNames() {
        Field[] fields = Prescription.class.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    private List<String> getPrescriptionFieldValues(Prescription prescription, List<String> fieldNames) {
        List<String> fieldValues = new ArrayList<>();
        for (String fieldName : fieldNames) {
            switch (fieldName) {
                case "name":
                    fieldValues.add(prescription.getName());
                    break;
                case "potentialDisease":
                    fieldValues.add(prescription.getPotentialDisease());
                    break;
                case "medicines":
                    fieldValues.add(prescription.getMedicines());
                    break;
                case "usage":
                    fieldValues.add(prescription.getUsage());
                    break;
                default:
                    System.out.println("Unsupported field: " + fieldName);
                    break;
            }
        }
        return fieldValues;
    }

    public void saveCase(Patient patient, Symptom[] symptoms, List<Prescription> prescriptions) {
        patientDataBase.saveCase(patient, symptoms, prescriptions);
    }

    public List<Prescription> run() {
        return prescriber.run();
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
