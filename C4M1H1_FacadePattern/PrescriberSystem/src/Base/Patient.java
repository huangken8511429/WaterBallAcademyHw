package Base;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Patient {
    private String id;
    private String name;
    private String gender;
    private int age;
    private float height;
    private float weight;
    private final List<Case> cases;

    public Patient() {
        cases = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public float getBMI() {
        return weight / (height/100 * height/100);
    }

    public void addCase(Case newCase) {
        cases.add(newCase);
    }

    public void setId(String id) {
        String regex = "^[A-Z]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(id).matches()) throw new IllegalArgumentException("ID must be in format of A123456789");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String regex = "^[A-Za-z\\s]{1,30}$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(name).matches()) throw new IllegalArgumentException("Name must be English");
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        if (age < 1 || age > 180) throw new IllegalArgumentException("Age must be between 1 and 180");
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        if (height > 500 || height < 1) throw new IllegalArgumentException("Height must be between 1 and 500");

        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        if (weight > 500 || weight < 1) throw new IllegalArgumentException("Weight must be between 1 and 500");
        this.weight = weight;
    }

    public List<Case> getCases() {
        return cases;
    }

}
