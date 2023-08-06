package Base;

public abstract class DiseaseHandler {
    private DiseaseHandler next;

    protected Prescription handle(Patient patient, Symptom[] symptoms) {
        if (matched(patient,symptoms)){
            return doHandle();
        } else if (next != null) {
            return next.handle(patient, symptoms);
        }
        return null;
    }

    protected abstract boolean matched(Patient patient, Symptom[] symptoms);

    protected abstract Prescription doHandle();
}
