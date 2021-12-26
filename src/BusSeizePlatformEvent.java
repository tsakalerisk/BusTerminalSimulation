import java.util.*;

public class BusSeizePlatformEvent extends Event {
    private final Bus bus;

    protected BusSeizePlatformEvent(Bus bus, int time) {
        super(time, EventType.BUS_SEIZE_PLATFORM);
        this.bus = bus;
        bus.setSeizePlatformEvent(this);
    }

    @Override
    public String message() {
        return "Το λεωφορείο " + bus.getOrder() + " κατέλαβε έναν διάδρομο επιβίβασης.";
    }

    @Override
    public void process(Simulation sim) {
        List<Bus> platforms = sim.getPlatforms();
        platforms.set(platforms.indexOf(null), bus);
        sim.getEventQueue().add(new BusDepartPlatformEvent(bus, Generator.randomUniform(time + 25, time + 35)));
    }
}
