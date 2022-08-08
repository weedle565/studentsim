package student_enrolment_system.classes.education_types;

import student_enrolment_system.classes.EducationUnit;
import student_enrolment_system.people.Student;
import student_enrolment_system.classes.Class;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Cohort {

    private Set<Student> students;
    private Set<Class> classes;

    private int size;
    private int year;

    public Cohort(){

        students = new HashSet<>();
        classes = new HashSet<>();

        size = 0;
        year = -1;

    }

    public void clear(){
        students.clear();
    }

    public void addStudent(Student s){
        students.add(s);
        size++;
    }

    public void removeStudent(Student s){
        students.remove(s);
        size--;
    }

    public void addClass(Class c){
        classes.add(c);
    }

    public void upAYear(){
        classes.clear();

        year++;
    }

    public void printStudents(){
        for(Student s : students){
            System.out.println(s);
        }
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Student getRandomStudent(int rand){
        return (Student) Arrays.stream(students.toArray()).toList().get(rand);
    }

    public int getYear() {
        return year;
    }

    public int getSize() {
        return size;
    }
}
