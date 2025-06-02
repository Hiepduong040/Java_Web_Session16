package ra.utils;

public enum BusType {
    STANDARD("Standard"),
    LUXURY("Luxury"),
    VIP("VIP");

    private final String type;

    BusType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
