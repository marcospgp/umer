import java.util.*;

public class Vehicle {

    private Point position;
    private VehicleType type;
    private Driver currentDriver;

    // Histórico de viagens
    private ArrayList<Trip> tripHistory = new ArrayList<Trip>();

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

    public Driver getDriver() {
        return this.currentDriver;
    }

    public void setDriver(Driver newDriver) {
        this.currentDriver = newDriver;
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
