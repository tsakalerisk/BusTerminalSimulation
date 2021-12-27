import java.util.List;
import java.util.Queue;

public class BusDepartPlatformEvent extends BusEvent {
    public BusDepartPlatformEvent(Bus bus, int time) {
        super(bus, time, EventType.BUS_DEPART_PLATFORM);
        bus.setDepartPlatformEvent(this);
    }

    @Override
    protected String message() {
        return "Το λεωφορείο " + bus.getName() + " αναχώρησε από τον διάδρομο επιβίβασης.";
    }

    @Override
    protected void process(Simulation sim) throws Exception {
        List<Bus> platforms = sim.getPlatforms();
        Queue<Bus> warehouse = sim.getWarehouse();
        platforms.set(platforms.indexOf(bus), null);
        new BusQueueForExitEvent(bus, getTime()).run(sim);
        if (!warehouse.isEmpty()) new BusSeizePlatformEvent(warehouse.remove(), getTime()).run(sim);
    }
}
