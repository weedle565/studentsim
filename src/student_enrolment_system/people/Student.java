package student_enrolment_system.people;

import student_enrolment_system.classes.Class;
import student_enrolment_system.classes.EducationUnit;
import student_enrolment_system.people.student.MajorEvents;
import student_enrolment_system.people.student.PreSchoolStudent;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Student extends People{

    private int year;
    private EducationUnit educationUnit;
    private Set<Class> classes;
    private float intellect;
    private float laziness;
    private float focus;

    private final HashMap<String, Integer> grades;

    public Student(EducationUnit educationUnit) throws IOException {
        super();

        year = -1;

        this.educationUnit = educationUnit;

        classes = new HashSet<>();

        intellect = R.nextFloat(0f, 100f);
        laziness = R.nextFloat(0f, 100f);
        focus = R.nextFloat(0f, 100f);

        grades = new HashMap<>();

    }

    public Student(Student student){
        super(student);
        this.year = student.year;
        this.educationUnit = student.educationUnit;;
        this.classes = student.classes;
        this.intellect = student.intellect;
        this.laziness = student.laziness;
        this.focus = student.focus;
        this.grades = student.grades;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public EducationUnit getEducationUnit() {
        return educationUnit;
    }

    public void setEducationUnit(EducationUnit educationUnit) {
        this.educationUnit = educationUnit;
    }

    public Set<Class> getClasses() {
        return classes;
    }

    public void setClasses(Set<Class> classes) {
        this.classes = classes;
    }

    public float getFocus() {
        return focus;
    }

    public float getIntellect() {
        return intellect;
    }

    public float getLaziness() {
        return laziness;
    }

    public void setFocus(float focus) {
        this.focus = focus;
    }

    public void setIntellect(float intellect) {
        this.intellect = intellect;
    }

    public void setLaziness(float laziness) {
        this.laziness = laziness;
    }

    public HashMap<String, Integer> getGrades() {
        return grades;
    }

    public void addClass(Class c){
        classes.add(c);
    }

    public void removeClass(Class c){
        classes.remove(c);
    }

    public void clearClasses(){
        classes.clear();
    }

    public void increaseYear(){

        if(this instanceof PreSchoolStudent){
            changeType();
        }

        clearClasses();

        year++;
    }

    public abstract Student changeType();

    public String majorEvent(){

        Random r = MajorEvents.RANDOM;

        if((r.nextInt(0, 100) >= 45 && r.nextInt(0, 100) <= 50)){

            return MajorEvents.randomEvent().name();

        }

        return "none";

    }

    public abstract float calculateGrade(Class c);

    public abstract void takeTest(Class c);

    @SuppressWarnings("check")
    @Override
    public boolean equals(Object o){
        return ((Student)o).getIdNumber() == this.getIdNumber();
    }

    @Override
    public String toString() {
        return "Student: " +
                "student number: " + getIdNumber() +
                ", full name: '" + getFullName() +
                ", year: '" + getYear() +
                ", education unit: " + getEducationUnit() +
                ", classes: " + getClasses();
    }
}
