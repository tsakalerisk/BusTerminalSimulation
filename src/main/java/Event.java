/* Αφηρημένη κλάση που μοντελοποιεί ένα γεγονός. Κάθε υποκλάση
θα πρέπει να δώσει υλοποίηση στις message() και process(). */
public abstract class Event implements Comparable<Event> {
    //Το λεωφορείο που αφορά το γεγονός
    protected Bus bus;
    //Ο χρόνος στον οποίο το γεγονός συμβαίνει
    private final int time;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    protected Event(Bus bus, int time) {
        this.bus = bus;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    //Το μήνυμα που εκτυπώνεται στα logs όταν τρέχει το γεγονός
    protected abstract String message() throws Exception;

    /*Τι συμβαίνει κατά την εκτέλεση του γεγονότος, πχ. κατάληψη/απελευθέρωση
    πόρων, δημιουργία εξαρτημένων/ανεξάρτητων γεγονότων κλπ. */
    protected abstract void process(Simulation sim) throws Exception;

    public void run(Simulation sim) throws Exception {
        Main.logColor("[" + ANSI_GREEN + "t = %04d" + ANSI_RESET + "] %s%n",
                "[t = %04d] %s%n",
                time, message());
        process(sim);
    }

    @Override
    public int compareTo(Event o) {
        return time - o.time;
    }
}

