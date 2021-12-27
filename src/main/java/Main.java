import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Main {
    public static OutputStreamWriter writer;

    public static void main(String[] args) throws Exception {
        writer = new OutputStreamWriter(new FileOutputStream("data.log"));
        Simulation simulation = new Simulation();
        writer.close();
    }
}
