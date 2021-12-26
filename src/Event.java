import java.io.IOException;

public abstract class Event implements Comparable<Event> {
    public int time;
    public EventType type;

    public enum EventType {
        BUS_ARRIVAL,
        BUS_SEIZE_PLATFORM,
        BUS_DEPART_PLATFORM
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    protected Event(int time, EventType type) {
        this.time = time;
        this.type = type;
    }

    public abstract String message();

    protected void log() {
        System.out.printf("[" + ANSI_GREEN + "t = %06d" + ANSI_RESET + "] %s%n", time, message());
        try {
            Main.writer.write(String.format("[t = %06d] %s%n", time, message()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void process(Simulation sim) throws Exception;

    @Override
    public int compareTo(Event o) {
        return time - o.time;
    }
}

