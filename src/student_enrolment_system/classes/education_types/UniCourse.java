package student_enrolment_system.classes.education_types;

import student_enrolment_system.FileLoader;
import student_enrolment_system.classes.Class;

import java.io.IOException;
import java.util.Random;

public class UniCourse extends Class {

    private int years;

    public UniCourse() throws IOException {
        super("courses");

        Random r = new Random();

        years = r.nextInt(2, 6);
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }
}
