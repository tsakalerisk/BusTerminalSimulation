import java.util.*;
import java.util.stream.Collectors;

public class Simulation {
    //Χρόνος εκτέλεσης της προσομοίωσης
    public static final int SIM_TIME = 1440;

    //Διαθέσιμοι πόροι
    public static final int N_PLATFORMS = 5;
    public static final int N_EXITS_NORTH = 1;
    public static final int N_EXITS_SOUTH = 2;

    //Χρονικά διαστήματα
    public static final Range busArrivalInterval = new Range(5, 10);
    public static final Range boardingInterval = new Range(25, 35);
    public static final Range exitCheckInterval = new Range(10, 15);

    //Εκκίνηση της γεννήτριας τυχαίων αριθμών με seed βασισμένο στο ρολόι του συστήματος
    public static final boolean randomized = false;

    //Ουρά με ανεξάρτητα γεγονότα ταξινομημένα σε χρονική σειρά
    private final SimulationQueue eventQueue = new SimulationQueue();
    //Λίστα με τα λεωφορεία
    private final List<Bus> buses = new ArrayList<>();
    //Αποθήκη του σταθμού για λεωφορεία που περιμένουν για διάδρομο
    private final Queue<Bus> warehouse = new LinkedList<>();
    //Διάδρομοι επιβίβασης
    private final List<Bus> platforms = Arrays.asList(new Bus[N_PLATFORMS]);
    //Ουρές για κάθε προορισμό (λεωφορεία που περιμένουν για βόρεια έξοδο και
    //λεωφορεία που περιμένουν για νότια)
    private final Map<Bus.Destination, Queue<Bus>> exitQueues = Map.of(
            Bus.Destination.NORTH, new LinkedList<>(),
            Bus.Destination.SOUTH, new LinkedList<>()
    );
    //Έξοδοι για κάθε προορισμό (βόρειες και νότιες)
    private final Map<Bus.Destination, List<Bus>> exits = Map.of(
            Bus.Destination.NORTH, Arrays.asList(new Bus[N_EXITS_NORTH]),
            Bus.Destination.SOUTH, Arrays.asList(new Bus[N_EXITS_SOUTH])
    );

    public Simulation() throws Exception {
        if (randomized) Generator.setSeed(System.currentTimeMillis());

        //Δημιουργία γεγονότων αφίξεων και προσθήκη τους στην ουρά γεγονότων
        generateBuses();
        eventQueue.addAll(getBusArrivals());

        //Επεξεργασία των γεγονότων με Μηχανισμό Επόμενου Γεγονότος
        while (!eventQueue.isEmpty() && eventQueue.peek().getTime() <= SIM_TIME)
            eventQueue.remove().run(this);

        //Εμφάνιση στατιστικών
        Main.log("%nΜέσος χρόνος αναμονής για διάδρομο επιβίβασης: %.3f λεπτά%n", calcAvgWaitForPlatform());
        Main.log("Μέσος χρόνος αναμονής για βόρεια έξοδο: %.3f λεπτά%n", calcAvgWaitForExit(Bus.Destination.NORTH));
        Main.log("Μέσος χρόνος αναμονής για νότια έξοδο: %.3f λεπτά%n", calcAvgWaitForExit(Bus.Destination.SOUTH));
    }

    //Υπολογισμός μέσης αναμονής για έξοδο
    public double calcAvgWaitForExit(Bus.Destination dest) {
        return buses.stream()
                .filter(i -> i.getDest().equals(dest) && i.getQueueForExitEvent() != null && i.getSeizeExitEvent() != null)
                .mapToInt(i -> i.getSeizeExitEvent().getTime() - i.getQueueForExitEvent().getTime())
                .average()
                .orElse(Double.NaN);
    }

    //Υπολογισμός μέσης αναμονής για διάδρομο
    public double calcAvgWaitForPlatform() {
        return buses.stream()
                .filter(i -> i.getArrivalEvent() != null && i.getSeizePlatformEvent() != null)
                .mapToInt(i -> i.getSeizePlatformEvent().getTime() - i.getArrivalEvent().getTime())
                .average()
                .orElse(Double.NaN);
    }

    private Collection<Event> getBusArrivals() {
        return buses.stream().map(Bus::getArrivalEvent).collect(Collectors.toList());
    }

    //Δημιουργία λεωφορείων όπου οι αφίξεις τους στον σταθμό απέχουν
    // ανάμεσα στο ελάχιστο και στο μέγιστο του διαστήματος άφιξης
    private void generateBuses() {
        int lastArrivalTime = Generator.randFromRange(busArrivalInterval);
        while (lastArrivalTime < SIM_TIME) {
            buses.add(new Bus(buses.size() + 1, lastArrivalTime));
            lastArrivalTime = lastArrivalTime + Generator.randFromRange(busArrivalInterval);
        }
    }

    public SimulationQueue getEventQueue() {
        return eventQueue;
    }

    public Queue<Bus> getWarehouse() {
        return warehouse;
    }

    public List<Bus> getPlatforms() {
        return platforms;
    }

    public Map<Bus.Destination, Queue<Bus>> getExitQueues() {
        return exitQueues;
    }

    public Map<Bus.Destination, List<Bus>> getExits() {
        return exits;
    }
}
