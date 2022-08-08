package student_enrolment_system;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class FileLoader {

    private static File getFile(String name) {

        return new File("src/files/" + name + ".txt");
    }

    public static String getName(String file) throws IOException {

        String res = null;

        File f = getFile(file);

        Random r = new Random();

        int n = 0;

        for(Scanner s = new Scanner(f); s.hasNext(); ){

            ++n;
            String line = s.nextLine();
            if(r.nextInt(n) == 0){
                res = line;
            }

        }

        return res;

    }

}
