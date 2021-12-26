import java.util.PriorityQueue;

public class SimulationQueue extends PriorityQueue<Event> {
    private int currentTime;

    public SimulationQueue() {
        super();
        currentTime = 0;
    }

    @Override
    public Event remove() {
        Event removedEvent = super.remove();
        currentTime = removedEvent.time;
        return removedEvent;
    }

    public boolean add(Event event) throws RuntimeException {
        if (event.time < currentTime) throw new RuntimeException("Tried to insert event earlier than the current one." +
                "Current time: " + currentTime + ", Event time: " + event.time);
        return super.add(event);
    }
}
