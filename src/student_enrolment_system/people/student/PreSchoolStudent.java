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

        increaseYear();

        System.out.println(getFirstName() + " " + getLastName() + " has left pre school!");
        try {
            return new PrimarySchoolStudent(this);
        } catch (Exception e){
            e.printStackTrace();

            throw new RuntimeException();
        }
    }

    @Override
    public boolean calculateGrade(Class s) {
        return true;
    }

    //Unused as this type doesnt take tests
    @Override
    public void takeTest(Class c){

    }

    @Override
    public String toString() {
        return super.toString() + " Pre School";
    }
}
