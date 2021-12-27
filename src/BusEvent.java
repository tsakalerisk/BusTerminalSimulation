public abstract class BusEvent extends Event {
    protected Bus bus;

    protected BusEvent(Bus bus, int time, EventType type) {
        super(time, type);
        this.bus = bus;
    }
}
