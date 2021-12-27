public class BusArrivalEvent extends BusEvent {
    public BusArrivalEvent(Bus bus, int time) {
        super(bus, time, EventType.BUS_ARRIVAL);
        bus.setArrivalEvent(this);
    }

    @Override
    protected String message() {
        return "Το λεωφορείο " + bus.getName() + " μπήκε στην ουρά για διάδρομο επιβίβασης.";
    }

    @Override
    protected void process(Simulation sim) throws Exception {
        // Αν υπάρχει κενός διάδρομος, το λεωφορείο τον καταλαμβάνει
        if (sim.getPlatforms().contains(null)) {
            new BusSeizePlatformEvent(bus, getTime()).run(sim);
        }
        else {
            sim.getWarehouse().add(bus);
        }
    }
}
