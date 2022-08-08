package student_enrolment_system.people;

import student_enrolment_system.FileLoader;

import java.io.IOException;
import java.util.Random;

public abstract class People {

    private int idNumber;
    private String firstName;
    private String lastName;
    public static final Random R = new Random();
    private int age;

    public People() throws IOException {

        idNumber = R.nextInt(100, 999);

        firstName = FileLoader.getName("names");
        lastName = FileLoader.getName("lastNames");

        age = R.nextInt(3, 6);

    }

    public People(People p){

        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.idNumber = p.getIdNumber();
        this.age = p.age;

    }

    public int getIdNumber() {
        return idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }
}
