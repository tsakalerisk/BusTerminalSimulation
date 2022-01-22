import java.util.Random;

public class Generator {
    static Random generator = new Random(1);

    public static void setSeed(long seed) {
        generator.setSeed(seed);
    }

    public static double random() {
        return generator.nextDouble();
    }

    public static int randFromRange(Range range) {
        return generator.nextInt(range.max() - range.min() + 1) + range.min();
    }
}
