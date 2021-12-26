public class Bus {
    private final int order;
    private BusArrivalEvent arrivalEvent;
    private BusSeizePlatformEvent seizePlatformEvent;
    private BusDepartPlatformEvent departPlatformEvent;

    public Bus(int order, int arrivalTime) {
        this.order = order;
        arrivalEvent = new BusArrivalEvent(this, arrivalTime);
    }

    public int getOrder() {
        return order;
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
}
