import java.util.ArrayList;
import java.io.Serializable;

public class Vehicle implements Serializable {

    private static final long serialVersionUID = -7388682787774554843L;
    private double finances;
    private Point position;
    private String identifier;
    private VehicleType type;
    private Driver currentDriver;
    private boolean waitingList;

    // Histórico de viagens
    private ArrayList<Trip> tripHistory = new ArrayList<Trip>();

    /**
     * Constructor for objects of class Vehicle
     */
    public Vehicle(double newPosX, double newPosY, String identifier, VehicleType type, boolean hasWaitingList) {
        this.position = new Point<Double>(newPosX, newPosY);
        this.identifier = identifier;
        this.type = type;
        this.currentDriver = null;
        this.type = type;
        this.finances = (double) 0.0;
        this.waitingList = hasWaitingList;
    }

    public void addToFinances(double tripCost) {
        this.finances += tripCost;
    }

    public double getFinances() {
        return (int) Math.round(this.finances * 100) / (double) 100;
    }

    public Point getPosition() {
        return this.position;
    }

    public Point setPosition(Point newPos) {
        return this.position = newPos;
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
        return ((double) distance / this.type.getAvgSpeed());
    }

    // aquela complicação toda é para obter um número que seja apenas com duas casas decimais
    public double getTripPrice(double distance) {
        return (int) Math.round((distance / this.type.getPricePerKm()) * 100) / (double) 100;
    }

    @Override
    public String toString() {
    return "<html>Identifier: " + this.identifier + ", "
         + "Type: " + this.type + ", "
         + "<br>Finances: " + this.finances + ", "
         + "Position: " + this.position + ", "
         + "<br>Wait List: " + this.waitingList + ", "
         + "Current Driver: " + this.currentDriver;
    }
}
