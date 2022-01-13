import java.util.List;
import java.util.Queue;

public class BusDepartPlatformEvent extends Event {

    //Αναχώρηση ενός λεωφορείου από τον διάδρομο επιβίβασης
    public BusDepartPlatformEvent(Bus bus, int time) {
        super(bus, time);
        bus.setDepartPlatformEvent(this);
    }

    @Override
    protected String message() {
        return "Το λεωφορείο " + bus.getName() + " αναχώρησε από τον διάδρομο επιβίβασης.";
    }

    @Override
    protected void process(Simulation sim) throws Exception {
        //Το λεωφορείο απελευθερώνει τον διάδρομο επιβίβασης που είχε καταλάβει
        //και δημιουργεί ένα BusQueueForExitEvent που εκτελείται άμεσα.
        //Έπειτα δημιουργεί ένα BusSeizePlatformEvent για το πρώτο λεωφορείο
        //στην αποθήκη του σταθμού, το οποίο εκτελείται άμεσα.

        List<Bus> platforms = sim.getPlatforms();
        Queue<Bus> warehouse = sim.getWarehouse();

        platforms.set(platforms.indexOf(bus), null);
        new BusQueueForExitEvent(bus, getTime()).run(sim);

        if (!warehouse.isEmpty()) new BusSeizePlatformEvent(warehouse.remove(), getTime()).run(sim);
    }
}
