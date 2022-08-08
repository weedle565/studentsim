package student_enrolment_system.classes;

import student_enrolment_system.FileLoader;
import student_enrolment_system.people.Student;
import student_enrolment_system.people.Teacher;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Class {

    private Teacher t;
    private int numStudents;
    private final Set<Student> students;
    private String name;
    private final int passingGrade;

    private final static int maxSize = 15;

    public Class() throws IOException {

        Random r = new Random();

        name = FileLoader.getName("classes");

        t = new Teacher();

        numStudents = r.nextInt(1, 15);

        students = new HashSet<>();

        passingGrade = r.nextInt(50,101);

    }

    public Class(String type) throws IOException{
        this();

        name = FileLoader.getName(type + ".txt");
    }

    public Teacher getT() {
        return t;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public String getName() {
        return name;
    }

    public int getAssignmentNum() {
        return 4;
    }

    public int getPassingGrade() {
        return passingGrade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    public void setT(Teacher t) {
        this.t = t;
    }

    public void addStudent(Student s){
        students.add(s);
    }

    public void removeStudent(Student s){
        students.remove(s);
    }
}
