public class BusQueueForExitEvent extends Event {

    //Εισαγωγή ενός λεωφορείου στην ουρά για έξοδο
    protected BusQueueForExitEvent(Bus bus, int time) {
        super(bus, time);
        bus.setQueueForExitEvent(this);
    }

    @Override
    protected String message() throws Exception {
        return "Το λεωφορείο " + bus.getName() + " μπήκε στην ουρά για " + bus.getDestName() + " έξοδο.";
    }

    @Override
    protected void process(Simulation sim) throws Exception {
        //Αν υπάρχει διαθέσιμη άδεια έξοδος προς τον προορισμό του λεωφορείου
        //δημιουργείται ένα BusSeizeExitEvent που εκτελείται άμεσα.
        //Αλλιώς το λεωφορείο προτίθεται στην ουρά που αντιστοιχεί στον προορισμό του.

        if (sim.getExits().get(bus.getDest()).contains(null))
            new BusSeizeExitEvent(bus, getTime()).run(sim);
        else
            sim.getExitQueues().get(bus.getDest()).add(bus);
    }
}
