package Base;

public class Main {
    public static void main(String[] args) {
        PatientSystemFacade patientSystemFacade = new PatientSystemFacade();

        patientSystemFacade.importPatientData("src/File/patientData.json");
        patientSystemFacade.importSupportDiseases("src/File/supportDiseases");

        patientSystemFacade.prescribe("A123456789",new Symptom[]{Symptom.SQUEEZE,Symptom.HEADACHE,Symptom.COUGH},"Wang Xiao Ming","csv");
        patientSystemFacade.prescribe("B222312312",new Symptom[]{Symptom.SQUEEZE},"Xiao Hua","json");
        patientSystemFacade.prescribe("A103456789",new Symptom[]{Symptom.SQUEEZE,Symptom.HEADACHE,Symptom.COUGH,Symptom.SNORE},"pola","json");
    }
}