import java.util.*;

public class BusSeizePlatformEvent extends BusEvent {
    protected BusSeizePlatformEvent(Bus bus, int time) {
        super(bus, time, EventType.BUS_SEIZE_PLATFORM);
        bus.setSeizePlatformEvent(this);
    }

    @Override
    protected String message() {
        return "Το λεωφορείο " + bus.getName() + " κατέλαβε έναν διάδρομο επιβίβασης.";
    }

    @Override
    protected void process(Simulation sim) {
        List<Bus> platforms = sim.getPlatforms();
        platforms.set(platforms.indexOf(null), bus);
        int departTime = getTime() + Generator.randomUniform(Simulation.boardingInterval.getMinimum(), Simulation.boardingInterval.getMaximum());
        sim.getEventQueue().add(new BusDepartPlatformEvent(bus, departTime));
    }
}
