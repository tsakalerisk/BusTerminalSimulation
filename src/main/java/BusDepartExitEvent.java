import java.util.List;
import java.util.Queue;

public class BusDepartExitEvent extends Event {

    //Αναχώρηση ενός λεωφορείου από την έξοδο
    protected BusDepartExitEvent(Bus bus, int time) {
        super(bus, time);
        bus.setDepartExitEvent(this);
    }

    @Override
    protected String message() throws Exception {
        return "Το λεωφορείο " + bus.getName() + " αναχώρησε από την " + bus.getDestName() + " έξοδο.";
    }

    @Override
    protected void process(Simulation sim) throws Exception {
        //Το λεωφορείο απελευθερώνει την έξοδο που είχε καταλάβει.
        //Έπειτα δημιουργεί ένα BusSeizeExitEvent για το πρώτο λεωφορείο
        //στην ουρά για τον συγκεκριμένο προορισμό, το οποίο εκτελείται άμεσα.

        List<Bus> exits = sim.getExits().get(bus.getDest());
        Queue<Bus> exitQueue = sim.getExitQueues().get(bus.getDest());

        exits.set(exits.indexOf(bus), null);

        if (!exitQueue.isEmpty()) new BusSeizeExitEvent(exitQueue.remove(), getTime()).run(sim);
    }
}
