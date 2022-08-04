package student_enrolment_system;

import java.io.*;
import java.util.Random;

public class FileLoader {

    private static RandomAccessFile getFile(String name) throws FileNotFoundException {

        return new RandomAccessFile(name + ".txt", "r");
    }

    public static String getName(String file) throws IOException {

        RandomAccessFile p = getFile(file + ".txt");

        final long fileSize = p.length();

        Random r = new Random();
        int pos = r.nextInt(0, (int)fileSize);

        p.seek(pos);

        String line = p.readLine();

        p.close();

        return line;
    }

}
