
/**
 * Enum dos tipos de táxis
 */
public enum VehicleType {
    LIGHT      (40, 1.5, 87),
    NINESEAT   (30, 2.5, 84),
    MOTORCYCLE (50, 1, 95);

    private final double avgSpeed; // Average speed in km/h
    private final double pricePerKm; // Price per km in euros

    // Value from 0-100 that represents the vehicle type's
    // capacity to fulfill the estimated trip time
    private final double reliability;

    /**
     * Constructor for VehicleType enums
     */
    VehicleType(double avgSpeed, double pricePerKm, double reliability) {
        this.avgSpeed = avgSpeed;
        this.pricePerKm = pricePerKm;
        this.reliability = reliability;
    }

    public double getAvgSpeed() {
        return this.avgSpeed;
    }

    public double getPricePerKm() {
        return this.pricePerKm;
    }

    public double getReliability() {
        return this.reliability;
    }
}
