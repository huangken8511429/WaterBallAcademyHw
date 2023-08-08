package DiseaseHandler;
import Base.Disease;
import Base.Patient;
import Base.Prescription;
import Base.Symptom;

import java.util.Arrays;

public class Attractive extends Disease {
    @Override
    protected boolean matched(Patient patient, Symptom[] symptoms) {
        return patient.getAge() == 18 && patient.getGender().equalsIgnoreCase("FEMALE") && Arrays.stream(symptoms).anyMatch(symptom -> symptom == Symptom.SQUEEZE);
    }

    @Override
    protected Prescription doHandle() {
        return new Prescription("青春抑制劑","有人想你了","假鬢角、臭味","把假鬢角黏在臉的兩側，讓自己異性緣差一點，自然就不會有人想妳了。");
    }
}
