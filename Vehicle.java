public class Vehicle {

    private VehicleType type;
    private Driver currentDriver;
    private Point position;

    /**
     * Constructor for objects of class Vehicle
     */
    public Vehicle(double newPosX, double newPosY) {
        this.position = new Point<Double>(newPosX, newPosY);
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

    public int getAvgSpeed() {
        return this.type.getAvgSpeed();
    }

    public int getPricePerKm() {
        return this.type.getPricePerKm();
    }

    public int getReliability() {
        return this.type.getReliability();
    }
}
