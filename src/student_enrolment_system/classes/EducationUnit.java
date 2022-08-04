package student_enrolment_system.classes;

import student_enrolment_system.FileLoader;
import student_enrolment_system.people.Student;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class EducationUnit {

    private String name;
    private int score;

    private final Set<Student> students;

    public static final Random R = new Random();

    public EducationUnit() throws IOException {

        name = FileLoader.getName("schools");
        score = R.nextInt(0, 100);

        students = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void addStudent(Student s){

        students.add(s);
    }

    @Override
    public String toString(){

        return "School: " + name;

    }
}
