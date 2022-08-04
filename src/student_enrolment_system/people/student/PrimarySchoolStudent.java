package student_enrolment_system.people.student;

import student_enrolment_system.classes.Class;
import student_enrolment_system.classes.EducationUnit;
import student_enrolment_system.people.Student;

import java.io.IOException;

public class PrimarySchoolStudent extends Student {

    public PrimarySchoolStudent(EducationUnit educationUnit) throws IOException {
        super(educationUnit);

    }

    public PrimarySchoolStudent(Student student) {
        super(student);
    }

    @Override
    public void takeTest(Class c){

        if(this.getYear() <= 3){
            return;
        }

        int size = 1;

        if(!super.getGrades().isEmpty()){
            for(String s : super.getGrades().keySet()){
                if(s.contains(c.getName())){
                    size++;
                }
            }
        }

        if(c.getAssignmentNum() == size){
            return;
        }

        float modifier = R.nextFloat(0, 100);

        if(modifier < 1){
            super.getGrades().put(c.getName() + " " + size, 0);
            return;
        }

        float grade = (c.getT().getTeacherAbility() * getIntellect() + getFocus()) / getLaziness() * (modifier/100);

        super.getGrades().put(c.getName() + " " + size, (int)grade);

    }

    @Override
    public Student changeType() {

        int failed = 0;

        if(getYear() != 6){
            System.out.println("Student: " + getFullName() + " attempted to move up early! " + getYear());
            return null;
        }

        for(Class cl : getClasses()){

            for(String i : super.getGrades().keySet()){

                if(cl.getName().equals(i) && !(super.getGrades().get(i) > cl.getPassingGrade())){

                    failed++;

                }

            }

        }

        if(failed > 1){
            System.out.println("Student: " + getFullName() + " failed to many subjects!");
            return null;
        }

        System.out.println("Student: " + getFullName() + " is moving to high school!");

        return new HighSchoolStudent(this);

    }

    @Override
    public float calculateGrade(Class c) {

        float grade = 0;

        for(int i = 1; i <= c.getAssignmentNum(); i++){

            grade += super.getGrades().get(c.getName() + " " + i)/4f;

        }

        return grade;
    }
}
