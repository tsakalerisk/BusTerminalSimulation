import java.util.*;
import java.util.stream.Collectors;

public class Simulation {
    public static final int N_BUSES = 10;
    public static final int N_PLATFORMS = 2;

    private final SimulationQueue eventQueue = new SimulationQueue();
    private final List<Bus> buses = new ArrayList<>();
    private final Queue<Bus> warehouse = new LinkedList<>();
    private final List<Bus> platforms = Arrays.asList(new Bus[N_PLATFORMS]);

    public Simulation() throws Exception {
        generateBuses(N_BUSES, 60, 90);
        eventQueue.addAll(getBusArrivals());
        while (!eventQueue.isEmpty()) {
            Event currentEvent = eventQueue.remove();
            currentEvent.process(this);
            currentEvent.log();
        }
    }

    private Collection<Event> getBusArrivals() {
        return buses.stream().map(Bus::getArrivalEvent).collect(Collectors.toList());
    }

    private void generateBuses(int n, int from, int to) {
        int lastArrivalTime = Generator.randomUniform(from, to);
        buses.add(new Bus(0, lastArrivalTime));
        for (int i = 1; i < n; i++) {
            lastArrivalTime = Generator.randomUniform(lastArrivalTime + from, lastArrivalTime + to);
            buses.add(new Bus(i, lastArrivalTime));
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
}
