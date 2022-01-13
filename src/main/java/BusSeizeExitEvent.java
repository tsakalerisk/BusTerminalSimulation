import java.util.List;

public class BusSeizeExitEvent extends BusEvent {
    protected BusSeizeExitEvent(Bus bus, int time) {
        super(bus, time, EventType.BUS_SEIZE_EXIT);
        bus.setSeizeExitEvent(this);
    }

    @Override
    protected String message() throws Exception {
        return "Το λεωφορείο " + bus.getName() + " κατέλαβε " + bus.getDestName() + " έξοδο.";
    }

    @Override
    protected void process(Simulation sim) {
        List<Bus> exits = sim.getExits().get(bus.getDest());
        exits.set(exits.indexOf(null), bus);
        int departTime = getTime() + Generator.randInt(Simulation.exitCheckInterval.getMinimum(), Simulation.exitCheckInterval.getMaximum());
        sim.getEventQueue().add(new BusDepartExitEvent(bus, departTime));
    }
}
