package student_enrolment_system.people.student;

import student_enrolment_system.classes.Class;
import student_enrolment_system.classes.EducationUnit;
import student_enrolment_system.people.Student;

import java.io.IOException;

public class UnderGrad extends Student {

    private String course;

    public UnderGrad(EducationUnit educationUnit) throws IOException {
        super(educationUnit);
    }

    public UnderGrad(Student s) {
        super(s);

    }

    @Override
    public Student changeType() {
        return null;
    }

    @Override
    public boolean calculateGrade(Class c) {
        return true;
    }

    @Override
    public void takeTest(Class c) {

    }
}
