public class BusQueueForExitEvent extends BusEvent {
    protected BusQueueForExitEvent(Bus bus, int time) {
        super(bus, time, EventType.BUS_QUEUE_FOR_EXIT);
        bus.setQueueForExitEvent(this);
    }

    @Override
    protected String message() throws Exception {
        return "Το λεωφορείο " + bus.getName() + " μπήκε στην ουρά για " + bus.getDestName() + " έξοδο.";
    }

    @Override
    protected void process(Simulation sim) throws Exception {
        if (sim.getExits().get(bus.getDest()).contains(null)) {
            new BusSeizeExitEvent(bus, getTime()).run(sim);
        }
        else {
            sim.getExitQueues().get(bus.getDest()).add(bus);
        }
    }
}
