package Base;

public class Case {
    private final String caseTime;
    private final Symptom[] symptoms;
    private final Prescription prescription;

    public Case(String caseTime, Symptom[] symptoms, Prescription prescription) {
        this.caseTime = caseTime;
        this.symptoms = symptoms;
        this.prescription = prescription;
    }
}
