package student_enrolment_system.people.student;

import java.util.*;

public enum MajorEvents {

    DEATH,
    MINOR_INJURY,
    MAJOR_INJURY,
    LOSS,
    SURGERY;

    private static final List<MajorEvents> eventsList = List.of(values());
    private static final int SIZE = eventsList.size();
    public static final Random RANDOM = new Random();

    public static MajorEvents randomEvent(){

        return eventsList.get(RANDOM.nextInt(SIZE));
    }
}
