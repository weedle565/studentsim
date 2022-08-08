package student_enrolment_system;

import student_enrolment_system.classes.Class;
import student_enrolment_system.classes.EducationUnit;
import student_enrolment_system.classes.education_types.Cohort;
import student_enrolment_system.people.Student;
import student_enrolment_system.people.student.PreSchoolStudent;

import java.io.IOException;
import java.util.*;

public class StudentSim {

    private final ArrayList<EducationUnit> educationUnits;

    private final Random r;

    private int term;
    private boolean examSeason;

    public StudentSim(){

        educationUnits = new ArrayList<>();

        term = 1;
        examSeason = false;

        r = new Random();
    }

    private void start() throws IOException {

        fill();

        mainMenu();

    }

    private void fill() throws IOException {

        int numStudents = r.nextInt(15, 50);
        int numSchools = r.nextInt(1, 5);

        for(int i = 0; i <= numSchools; i++){

            educationUnits.add(new EducationUnit());

        }

        if(educationUnits.isEmpty())
            checkFail();

        for(EducationUnit eu : educationUnits) {

            Cohort c = new Cohort();

            for(int i = 0; i <= numStudents; i++){
                c.addStudent(new PreSchoolStudent(eu));
            }

            eu.addCohort(c);

        }


    }

    private void randomInformation() throws IOException {

        int times = r.nextInt(2, 8);

        int i = 0;

        Scanner s = new Scanner(System.in);

        do{

            String message = FileLoader.getName("info");

            System.out.println("Student " + educationUnits.get(r.nextInt(0, educationUnits.size())).getRandomStudent().getFullName() + " " + message);

            i++;

            s.nextLine();
        }while(i != times);

    }

    private void printMenu(){

        System.out.println("""
                Commands:\040
                1. Move forward in time.
                2. Print all cohorts
                3. Force event on random student
                4. Print top bottom and average grades from cohorts
                5. View a students grades/information (requires student number)
                6. Exit and do not save to file
                7. Exit and save all data to file\s
                """);

    }

    private void tests() throws IOException {

        for(EducationUnit eu : educationUnits){

            for(Cohort c : eu.getCohorts()){

                for(Student s : c.getStudents()){

                    for(Class cl : s.getClasses()){
                        s.takeTest(cl);
                    }

                }
            }

        }

        System.out.println("\nAll students have suffered through exams.");

        System.out.println("\nTerm " + term + " holidays have began\n");

        term++;
        examSeason = false;

        randomInformation();

    }

    private void moveTimeForward() throws IOException {

        Set<Student> set = new HashSet<>();

        if(term == 2){

            for(EducationUnit eu : educationUnits){

                for(Cohort c : eu.getCohorts()){

                    for(Student s : c.getStudents()){

                        s.setAge(s.getAge() + 1);

                        set.add(s.changeType());

                    }

                    c.clear();

                    c.setStudents(set);
                }

            }

            term = 1;
        }

        if(examSeason){
            System.out.println("Term: " + term + " exams have begun!");
            tests();
        } else {
            System.out.println("Term: " + term + " has started.");
            examSeason = true;
            randomInformation();
        }

    }

    private void printAllCohorts(){

        for(EducationUnit eu : educationUnits){

            for(Cohort c : eu.getCohorts()){

                System.out.println("\n" + eu + ", Cohorts are: ");
                c.printStudents();

            }

        }

    }

    private void forceEvent(){

    }

    private void printStudentGrades(){

    }

    private void viewStudentInfo(){

    }

    private void mainMenu() throws IOException {

        Scanner s = new Scanner(System.in);

        String command;

        do{
            printMenu();

            command = s.nextLine();

            switch (command) {
                case "1" -> moveTimeForward();
                case "2" -> printAllCohorts();
                case "3" -> forceEvent();
                case "4" -> printStudentGrades();
                case "5" -> viewStudentInfo();
            }


        }while (!command.equals("6") && !command.equals("7"));

    }

    void checkFail(){
        throw new RuntimeException("Randomiser failed!");
    }

    public static void main(String[] args) {

        StudentSim sm = new StudentSim();

        try{
            sm.start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
