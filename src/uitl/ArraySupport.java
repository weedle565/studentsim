package uitl;

public class ArraySupport {

    private static final int SOFT_MAX = Integer.MAX_VALUE - 8;

    public static int checkSize(int prevLength, int newLength, int preferredGrowth){

        int preferredLength = Math.max(prevLength, preferredGrowth);

        if(0 < preferredLength && preferredLength <= SOFT_MAX){
            return preferredLength;
        } else {
            return hugeLength(prevLength, newLength);
        }

    }

    private static int hugeLength(int oldLength, int minGrowth){

        int minLength = oldLength + minGrowth;

        if(minLength < 0){

            throw new OutOfMemoryError("Array Size to big! " + minLength);

        } else if(minLength <= SOFT_MAX){
            return SOFT_MAX;
        } else {
            return minLength;
        }
    }
}
