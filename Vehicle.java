import java.util.*;
import java.io.Serializable;

public class Vehicle implements Serializable {

    private Point position;
    private String identifier;
    private VehicleType type;
    private Driver currentDriver;

    // Histórico de viagens
    private ArrayList<Trip> tripHistory = new ArrayList<Trip>();

    /**
     * Constructor for objects of class Vehicle
     */
    public Vehicle(double newPosX, double newPosY, String identifier, VehicleType type, Driver newDriver) {
        this.position = new Point<Double>(newPosX, newPosY);
        this.identifier = identifier;
        this.type = type;
        this.currentDriver = newDriver;
    }

    public Point getPosition() {
        return this.position;
    }

    public String getIdentifier() {
        return this.identifier;
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

    public double getAvgSpeed() {
        return this.type.getAvgSpeed();
    }

    public double getPricePerKm() {
        return this.type.getPricePerKm();
    }

    public double getReliability() {
        return this.type.getReliability();
    }

    public double getTripTime(double distance) {
        return (distance / this.type.getAvgSpeed());
    }

    // aquela complicação toda é para obter um número que seja apenas com duas casas decimais
    public double getTripPrice(double distance) {
        return (int) Math.round((distance / this.type.getPricePerKm()) * 100) / (double) 100;
    }
}
