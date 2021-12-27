public class Bus {
    private final int order;
    private final Destination dest;
    private BusArrivalEvent arrivalEvent;
    private BusSeizePlatformEvent seizePlatformEvent;
    private BusDepartPlatformEvent departPlatformEvent;
    private BusQueueForExitEvent queueForExitEvent;
    private BusSeizeExitEvent seizeExitEvent;
    private BusDepartExitEvent departExitEvent;

    public enum Destination {
        NORTH,
        SOUTH
    }

    public Bus(int order, int arrivalTime) {
        this.order = order;
        arrivalEvent = new BusArrivalEvent(this, arrivalTime);
        dest = Generator.random() < 0.25 ? Destination.NORTH : Destination.SOUTH;
    }

    public String getName() {
        return order + (dest.equals(Destination.NORTH) ? "B" : "N");
    }

    public Destination getDest() {
        return dest;
    }

    public String getDestName() throws Exception {
        String destName;
        switch (dest) {
            case NORTH -> destName = "βόρεια";
            case SOUTH -> destName = "νότια";
            default -> throw new Exception("Destination not named");
        }
        return destName;
    }

    public BusArrivalEvent getArrivalEvent() {
        return arrivalEvent;
    }

    public void setArrivalEvent(BusArrivalEvent arrivalEvent) {
        this.arrivalEvent = arrivalEvent;
    }

    public BusSeizePlatformEvent getSeizePlatformEvent() {
        return seizePlatformEvent;
    }

    public void setSeizePlatformEvent(BusSeizePlatformEvent seizePlatformEvent) {
        this.seizePlatformEvent = seizePlatformEvent;
    }

    public BusDepartPlatformEvent getDepartPlatformEvent() {
        return departPlatformEvent;
    }

    public void setDepartPlatformEvent(BusDepartPlatformEvent departPlatformEvent) {
        this.departPlatformEvent = departPlatformEvent;
    }

    public BusQueueForExitEvent getQueueForExitEvent() {
        return queueForExitEvent;
    }

    public void setQueueForExitEvent(BusQueueForExitEvent queueForExitEvent) {
        this.queueForExitEvent = queueForExitEvent;
    }

    public BusSeizeExitEvent getSeizeExitEvent() {
        return seizeExitEvent;
    }

    public void setSeizeExitEvent(BusSeizeExitEvent seizeExitEvent) {
        this.seizeExitEvent = seizeExitEvent;
    }

    public BusDepartExitEvent getDepartExitEvent() {
        return departExitEvent;
    }

    public void setDepartExitEvent(BusDepartExitEvent departExitEvent) {
        this.departExitEvent = departExitEvent;
    }
}
