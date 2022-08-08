package student_enrolment_system.people.student;

import student_enrolment_system.classes.Class;
import student_enrolment_system.classes.EducationUnit;
import student_enrolment_system.people.Student;

import java.io.IOException;

public class HighSchoolStudent extends Student {

    private int testsTaken;
    private int finalMark;

    public HighSchoolStudent(EducationUnit educationUnit) throws IOException {
        super(educationUnit);

        testsTaken = 0;
    }

    public HighSchoolStudent(Student s){
        super(s);

    }

    @Override
    public Student changeType() {

        if(getYear() != 12){

            return null;

        }

        if(R.nextInt(0, 11) < 2){

            return null;
            //doesnt go to uni. destroy

        }

        return new UnderGrad(this);
    }

    @Override
    public boolean calculateGrade(Class c) {
        float grade = 0;

        for(int i = 1; i <= c.getAssignmentNum(); i++){

            grade += super.getGrades().get(c.getName() + " " + i)/4f;

        }

        return grade >= 50;
    }

    @Override
    public void takeTest(Class c) {

        if(c.getAssignmentNum() == testsTaken){
            return;
        }

        float modifier = R.nextFloat(0, 100);

        if(modifier < 1){
            super.getGrades().put(c.getName() + " " + testsTaken, 0);
            return;
        }

        float grade;

        if(getYear() == 12 && testsTaken == 2){

            grade = (c.getT().getTeacherAbility() * getIntellect() + getFocus()) / getLaziness() * (modifier/100) * 2;

            super.getGrades().put(c.getName() + " " + testsTaken, (int)grade);

            testsTaken += 2;

            return;
        } else {
            grade = (c.getT().getTeacherAbility() * getIntellect() + getFocus()) / getLaziness() * (modifier / 100);
        }

        super.getGrades().put(c.getName() + " " + testsTaken, (int)grade);

        testsTaken++;

    }
}
