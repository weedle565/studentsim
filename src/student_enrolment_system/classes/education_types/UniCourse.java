package student_enrolment_system.classes.education_types;

import student_enrolment_system.FileLoader;
import student_enrolment_system.classes.Class;

import java.io.IOException;
import java.util.Random;

public class UniCourse extends Class {

    private String name;
    private int years;

    public UniCourse() throws IOException {

        Random r = new Random();

        name = FileLoader.getName("courses");
        years = r.nextInt(2, 6);
    }
}
