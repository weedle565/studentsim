package student_enrolment_system.people;

import java.io.IOException;

public class Teacher extends People {

    private float teacherAbility;

    public Teacher() throws IOException {
        super();

        teacherAbility = R.nextFloat(0f, 100f);
    }

    public float getTeacherAbility() {
        return teacherAbility;
    }

    public void setTeacherAbility(float teacherAbility) {
        this.teacherAbility = teacherAbility;
    }
}
