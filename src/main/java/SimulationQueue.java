import java.util.PriorityQueue;

/* Εξειδικευμένη PriorityQueue που δεν επιτρέπει την προσθήκη
γεγονότος με μικρότερο χρόνο από το τελευταίο γεγονός που
αφαιρέθηκε.  Δεν επιτρέπει δηλαδή την προσθήκη παρελθοντικών
γεγονότων στην ουρά.
Επίσης, η PriorityQueue κρατάει πάντα τα γεγονότα ταξινομημένα,
οπότε ανεξάρτητα από τη σειρά με την οποία προστίθενται τα
γεγονότα πάντα θα είναι σε χρονολογική σειρά. */
public class SimulationQueue extends PriorityQueue<Event> {
    private int currentTime;

    public SimulationQueue() {
        super();
        currentTime = 0;
    }

    @Override
    public Event remove() {
        Event removedEvent = super.remove();
        currentTime = removedEvent.getTime();
        return removedEvent;
    }

    public boolean add(Event event) throws RuntimeException {
        if (event.getTime() < currentTime) throw new RuntimeException("Tried to insert event earlier than the current one." +
                "Current time: " + currentTime + ", Event time: " + event.getTime());
        return super.add(event);
    }
}
