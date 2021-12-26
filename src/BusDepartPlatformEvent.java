import java.util.List;
import java.util.Queue;

public class BusDepartPlatformEvent extends Event {
    private final Bus bus;

    public BusDepartPlatformEvent(Bus bus, int time) {
        super(time, EventType.BUS_DEPART_PLATFORM);
        this.bus = bus;
        bus.setDepartPlatformEvent(this);
    }

    @Override
    public String message() {
        return "Το λεωφορείο " + bus.getOrder() + " αναχώρησε από τον διάδρομο αναχώρησης.";
    }

    @Override
    public void process(Simulation sim) {
        List<Bus> platforms = sim.getPlatforms();
        Queue<Bus> warehouse = sim.getWarehouse();
        platforms.set(platforms.indexOf(bus), null);
        if (!warehouse.isEmpty())
            sim.getEventQueue().add(new BusSeizePlatformEvent(warehouse.remove(), time));
    }
}
