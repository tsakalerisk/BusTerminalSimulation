import java.util.List;

public class BusSeizeExitEvent extends Event {

    //Κατάληψη μίας εξόδου από ένα λεωφορείο
    protected BusSeizeExitEvent(Bus bus, int time) {
        super(bus, time);
        bus.setSeizeExitEvent(this);
    }

    @Override
    protected String message() throws Exception {
        return "Το λεωφορείο " + bus.getName() + " κατέλαβε " + bus.getDestName() + " έξοδο.";
    }

    @Override
    protected void process(Simulation sim) {
        //Το λεωφορείο καταλαμβάνει την πρώτη διαθέσιμη έξοδο που
        //αντιστοιχεί στον προορισμό του και δημιουργεί
        // ένα BusDepartExitEvent που προτίθεται στην ουρά γεγονότων της προσομοίωσης.

        List<Bus> exits = sim.getExits().get(bus.getDest());
        exits.set(exits.indexOf(null), bus);

        int departTime = getTime() + Generator.randInt(Simulation.exitCheckInterval.getMinimum(), Simulation.exitCheckInterval.getMaximum());
        sim.getEventQueue().add(new BusDepartExitEvent(bus, departTime));
    }
}
