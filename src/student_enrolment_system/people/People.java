package student_enrolment_system.people;

import student_enrolment_system.FileLoader;

import java.io.IOException;
import java.util.Random;

public abstract class People {

    private int idNumber;
    private String fullName;
    public static final Random R = new Random();
    private int age;

    public People() throws IOException {

        idNumber = R.nextInt(100, 999);

        fullName = FileLoader.getName("students");

        age = R.nextInt(0, 6);

    }

    public People(People p){

        this.fullName = p.getFullName();
        this.idNumber = p.getIdNumber();
        this.age = p.age;

    }

    public int getIdNumber() {
        return idNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }
}
