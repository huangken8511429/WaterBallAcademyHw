package Base;

import java.io.File;
import java.util.List;

public class CsvExporter {
    public void export(String saveFileName, Patient patient, List<Prescription> prescriptions) {
        File file = new File(saveFileName + ".csv");

    }
}
