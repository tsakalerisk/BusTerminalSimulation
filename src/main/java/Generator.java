import java.util.Random;

public class Generator {
    static Random generator = new Random(1);

    public static void setSeed(long seed) {
        generator.setSeed(seed);
    }

    public static int randInt(int from, int to) {
        return generator.nextInt(to - from + 1) + from;
    }
}
