import java.util.List;

public class BusArrivalEvent extends Event {
    private final Bus bus;

    public BusArrivalEvent(Bus bus, int time) {
        super(time, EventType.BUS_ARRIVAL);
        this.bus = bus;
        bus.setArrivalEvent(this);
    }

    @Override
    public String message() {
        return "Το λεωφορείο " + bus.getOrder() + " μπήκε στην ουρά για διάδρομο αναχώρησης.";
    }

    @Override
    public void process(Simulation sim) {
        // Αν υπάρχει κενός διάδρομος, το λεωφορείο τον καταλαμβάνει
        if (sim.getPlatforms().contains(null)) {
            sim.getEventQueue().add(new BusSeizePlatformEvent(bus, time));
        }
        else {
            sim.getWarehouse().add(bus);
        }
    }
}
