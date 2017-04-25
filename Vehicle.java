public class Vehicle {

    private Point position;
    private VehicleType type;
    private Driver currentDriver;

    /**
     * Constructor for objects of class Vehicle
     */
    public Vehicle(double newPosX, double newPosY, VehicleType type, Driver newDriver) {
        this.position = new Point<Double>(newPosX, newPosY);
        this.type = type;
        this.currentDriver = newDriver;
    }

    public VehicleType getType() {
        return this.type;
    }

    public Driver getCurrentDriver() {
        return this.currentDriver;
    }

    public Point getPosition() {
        return this.position;
    }

    public double getAvgSpeed() {
        return this.type.getAvgSpeed();
    }

    public double getPricePerKm() {
        return this.type.getPricePerKm();
    }

    public double getReliability() {
        return this.type.getReliability();
    }
}
