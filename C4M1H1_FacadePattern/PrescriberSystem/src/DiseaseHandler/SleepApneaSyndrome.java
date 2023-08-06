package DiseaseHandler;

import Base.DiseaseHandler;
import Base.Patient;
import Base.Prescription;
import Base.Symptom;

import java.util.Arrays;

public class SleepApneaSyndrome extends DiseaseHandler {
    @Override
    protected boolean matched(Patient patient, Symptom[] symptoms) {
        return patient.getBMI() > 26 && Arrays.stream(symptoms).anyMatch(symptom -> symptom == Symptom.SNORE);
    }

    @Override
    protected Prescription doHandle() {
        return new Prescription("打呼抑制劑","睡眠呼吸中止症","一捲膠帶","睡覺時，撕下兩塊膠帶，將兩塊膠帶交錯黏在關閉的嘴巴上，就不會打呼了。");
    }
}
