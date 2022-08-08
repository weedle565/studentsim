package student_enrolment_system.classes;

import student_enrolment_system.FileLoader;
import student_enrolment_system.classes.education_types.Cohort;
import student_enrolment_system.people.Student;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class EducationUnit {

    private String name;
    private int score;

    private final Set<Cohort> cohorts;

    public static final Random R = new Random();

    public EducationUnit() throws IOException {

        name = FileLoader.getName("schools");
        score = R.nextInt(0, 100);

        cohorts = new HashSet<>();
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

    public Set<Cohort> getCohorts() {
        return cohorts;
    }

    public void addCohort(Cohort c){

        cohorts.add(c);
    }

    public Student getRandomStudent(){

        int rand = R.nextInt(0, cohorts.size());

        int i = 0;

        for(Cohort c : cohorts){

            if(i == rand) {
                return c.getRandomStudent(R.nextInt(0, c.getSize()));
            }

            i++;
        }

        return null;

    }

    @Override
    public String toString(){

        return "School: " + name;

    }
}
