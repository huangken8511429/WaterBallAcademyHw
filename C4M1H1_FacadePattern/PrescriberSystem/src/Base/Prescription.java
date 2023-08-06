package Base;

public class Prescription {
    private String name;
    private String potentialDisease;
    private String medicines;
    private String usage;

    public Prescription(String name, String potentialDisease, String medicines, String usage) {
        this.name = name;
        this.potentialDisease = potentialDisease;
        this.medicines = medicines;
        this.usage = usage;
    }

    public String getName() {
        return name;
    }

    public String getPotentialDisease() {
        return potentialDisease;
    }

    public String getMedicines() {
        return medicines;
    }

    public String getUsage() {
        return usage;
    }
}
