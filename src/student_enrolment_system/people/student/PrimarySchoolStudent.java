package student_enrolment_system.people.student;

import student_enrolment_system.classes.Class;
import student_enrolment_system.classes.EducationUnit;
import student_enrolment_system.people.Student;

import java.io.IOException;
import java.text.DecimalFormat;

public class PrimarySchoolStudent extends Student {

    public PrimarySchoolStudent(EducationUnit educationUnit) throws IOException {
        super(educationUnit);

    }

    public PrimarySchoolStudent(Student student) throws IOException {
        super(student);

        addClass(new Class());
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

        float grade = Math.abs((((c.getT().getTeacherAbility() + getIntellect()) + getFocus())) / getLaziness() * (modifier/100));

        while(grade > 100){

            modifier = R.nextFloat(0, 100);

            grade = grade/modifier;

        }

   //     System.out.println("Student: " + getFullName() + " achieved the grade: " + grade + "!");

        super.getGrades().put(c.getName() + " " + size, (int)grade);

    }

    @Override
    public Student changeType() {

        int failed = 0;

        if(getYear() != 6){
            increaseYear();
            System.out.println("Student: " + getFullName() + " has moved up to year: " + getYear());
            return this;
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

        System.out.println("Student: " + getFullName() + " is moving to high schools.txt!");

        this.increaseYear();

        return new HighSchoolStudent(this);

    }

    private float getGrade(){

        float grade = 0;

        for(String key : getGrades().keySet()){

            System.out.println(getGrades().get(key));

            grade = getGrades().get(key);

        }

        return grade;
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
    public String toString(){

        return super.toString() + " " + String.format("%.2f", getGrade());

    }
}
