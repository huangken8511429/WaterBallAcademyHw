package Base;

public abstract class DiseaseHandler {

    protected Prescription handle(Patient patient, Symptom[] symptoms) {
        if (matched(patient,symptoms)){
            return doHandle();
        }
        return null;
    }

    protected abstract boolean matched(Patient patient, Symptom[] symptoms);

    protected abstract Prescription doHandle();
}
