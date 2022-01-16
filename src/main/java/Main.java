import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
    private static OutputStreamWriter writer;

    public static void main(String[] args) throws Exception {
        writer = new OutputStreamWriter(new FileOutputStream("data.log"));
        Simulation simulation = new Simulation();
        writer.close();
    }

    public static void log(String message, Object... args) throws IOException {
        System.out.printf(message, args);
        writer.write(String.format(message, args));
    }

    public static void logColor(String consoleMessage, String fileMessage, Object... args) throws IOException {
        System.out.printf(consoleMessage, args);
        writer.write(String.format(fileMessage, args));
    }
}
