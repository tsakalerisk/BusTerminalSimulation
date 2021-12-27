public class Generator {
    static long a = 1103515245;
    static long c = 12345;
    static long m = 2147483648L;
    static long z = 1077267865;

    public static double random() {
        double randomNumber = z / (double) m;
        z = calculateNewZ();
        return randomNumber;
    }

    public static int randomUniform(int from, int to) {
        return (int) (from + random() * (to - from));
    }

    private static long calculateNewZ() {
        return (a * z + c) % m;
    }
}
