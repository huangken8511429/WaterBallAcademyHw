package Base;

public abstract class Disease {

    protected Prescription handle(Patient patient, Symptom[] symptoms) {
        if (matched(patient,symptoms)){
            return doHandle();
        }
        return null;
    }

    protected abstract boolean matched(Patient patient, Symptom[] symptoms);

    protected abstract Prescription doHandle();
}
