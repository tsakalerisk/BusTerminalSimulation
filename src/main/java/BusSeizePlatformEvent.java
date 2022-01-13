import java.util.*;

public class BusSeizePlatformEvent extends Event {

    //Κατάληψη ενός διαδρόμου επιβίβασης από ένα λεωφορείο
    protected BusSeizePlatformEvent(Bus bus, int time) {
        super(bus, time);
        bus.setSeizePlatformEvent(this);
    }

    @Override
    protected String message() {
        return "Το λεωφορείο " + bus.getName() + " κατέλαβε έναν διάδρομο επιβίβασης.";
    }

    @Override
    protected void process(Simulation sim) {
        //Το λεωφορείο καταλαμβάνει τον πρώτο κενό διάδρομο και δημιουργεί
        //ένα BusDepartPlatformEvent που προτίθεται στην ουρά γεγονότων της προσομοίωσης.

        List<Bus> platforms = sim.getPlatforms();
        platforms.set(platforms.indexOf(null), bus);

        int departTime = getTime() + Generator.randInt(Simulation.boardingInterval.getMinimum(), Simulation.boardingInterval.getMaximum());
        sim.getEventQueue().add(new BusDepartPlatformEvent(bus, departTime));
    }
}
