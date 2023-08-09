package Base;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Base.PatientSystem.getFieldNames;
import static Base.PatientSystem.getPrescriptionFieldValues;

public class PrescribeTask {
    private List<Disease> diseasesHandler;
    private Patient patient;
    private Symptom[] symptoms;
    private final String fileName;
    private final String fileType;

    public PrescribeTask(List<Disease> diseasesHandler, Patient patient, Symptom[] symptoms, String fileName, String fileType) {
        this.diseasesHandler = diseasesHandler;
        this.patient = patient;
        this.symptoms = symptoms;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    List<Prescription> handle() throws InterruptedException {
        Thread.sleep(3000);
        List<Prescription> prescriptions = new ArrayList<>();
        for (Disease disease : diseasesHandler) {
            Prescription prescription = disease.handle(patient, symptoms);
            if (prescription != null) {
                prescriptions.add(prescription);
            }
        }
        return prescriptions;
    }

    public void exportData() {
        List<Prescription> prescriptions = patient.getCases().stream().map(Case::getPrescription).collect(Collectors.toList());

        String directoryPath = "data/" + patient.getId();
        Path path = Paths.get(directoryPath);

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String filePath = directoryPath + "/" + fileName + "." + fileType;
        try {
            switch (fileType) {
                case "json" -> {
                    String json = new ObjectMapper().writeValueAsString(prescriptions);
                    Files.write(Paths.get(filePath), json.getBytes());
                }
                case "csv" -> {
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
                }
                default -> System.out.println("Unsupported filetype: " + fileType);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Patient getPatient() {
        return patient;
    }

    public Symptom[] getSymptoms() {
        return symptoms;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }
}
