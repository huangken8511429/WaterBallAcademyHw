package Base;

public class Case {
    private String caseTime;
    private Symptom[] symptoms;
    private Prescription prescription;

    public Case(String caseTime, Symptom[] symptoms, Prescription prescription) {
        this.caseTime = caseTime;
        this.symptoms = symptoms;
        this.prescription = prescription;
    }
}
