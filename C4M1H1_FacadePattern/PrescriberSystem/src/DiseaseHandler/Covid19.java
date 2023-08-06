package DiseaseHandler;

import Base.DiseaseHandler;
import Base.Patient;
import Base.Prescription;
import Base.Symptom;

import java.util.Arrays;
import java.util.List;

public class Covid19 extends DiseaseHandler {
    @Override
    protected boolean matched(Patient patient, Symptom[] symptoms) {
        return Arrays.stream(symptoms).anyMatch(symptom -> symptom == Symptom.HEADACHE)
                && Arrays.stream(symptoms).anyMatch(symptom -> symptom == Symptom.COUGH)
                && Arrays.stream(symptoms).anyMatch(symptom -> symptom == Symptom.SQUEEZE);
    }

    @Override
    protected Prescription doHandle() {
        return new Prescription("清冠一號","新冠肺炎","清冠一號","將相關藥材裝入茶包裡，使用500 mL 溫、熱水沖泡悶煮1~3 分鐘後即可飲用。");
    }
}
