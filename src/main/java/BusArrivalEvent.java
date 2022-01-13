public class BusArrivalEvent extends Event {

    //Άφιξη ενός λεωφορείου στον σταθμό
    public BusArrivalEvent(Bus bus, int time) {
        super(bus, time);
        bus.setArrivalEvent(this);
    }

    @Override
    protected String message() {
        return "Το λεωφορείο " + bus.getName() + " μπήκε στην ουρά για διάδρομο επιβίβασης.";
    }

    @Override
    protected void process(Simulation sim) throws Exception {
        // Αν υπάρχει κενός διάδρομος, δημιουργείται ένα BusSeizePlatformEvent που εκτελείται άμεσα
        // Αλλιώς, το λεωφορείο προτίθεται στην αποθήκη του σταθμού.

        if (sim.getPlatforms().contains(null))
            new BusSeizePlatformEvent(bus, getTime()).run(sim);
        else
            sim.getWarehouse().add(bus);
    }
}
