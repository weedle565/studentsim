package student_enrolment_system.people.student;

import student_enrolment_system.classes.Class;
import student_enrolment_system.classes.EducationUnit;
import student_enrolment_system.people.Student;

import java.io.IOException;

public class PreSchoolStudent extends Student {

    public PreSchoolStudent(EducationUnit educationUnit) throws IOException {
        super(educationUnit);
    }

    @Override
    public Student changeType() {

        this.increaseYear();
        System.out.println(getFullName() + " has left pre school!");
        return new PrimarySchoolStudent(this);

    }

    @Override
    public float calculateGrade(Class s) {
        return 0;
    }

    //Unused as this type doesnt take tests
    @Override
    public void takeTest(Class c){

    }
}
