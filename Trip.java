import java.util.Date;

/**
 * Classe que guarda informação sobre uma viagem efetuada no programa.
 */
public class Trip {

    private Vehicle vehicle;
    private Driver driver;
    private Point origin;
    private Point destination;
    private double estimatedDuration;
    private double realDuration;
    private double cost;
    private boolean completed;
    private Date startingTime;
    private Date arrivingTime;

    public Trip(Vehicle vehicle,
                Driver driver,
                Point origin,
                Point destination,
                double estimatedDuration,
                double realDuration,
                double cost
    ) {
        this.vehicle = vehicle;
        this.driver = driver;
        this.origin = origin;
        this.destination = destination;
        this.estimatedDuration = estimatedDuration;
        this.realDuration = realDuration;
        this.cost = cost;
        this.completed = false;
        this.startingTime = null;
        this.arrivingTime = null;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public Point getOrigin() {
        return this.origin;
    }

    public Point getDestination() {
        return this.destination;
    }

    public double getEstimatedDuration() {
        return this.estimatedDuration;
    }

    public double getRealDuration() {
        return this.realDuration;
    }

    public Date getTimeStarted() {
        return this.startingTime;
    }

    public double getCost() {
        return this.cost;
    }

    public boolean getIsCompleted() {
        return this.completed;
    }

    public Date getArrivingTime() {
        return this.arrivingTime;
    }

    public void setTimeStarted() {
        Date startedAt = new Date();
        this.startingTime = startedAt;
    }

    public void setIsCompleted(boolean completo) {
        this.completed = completo;
    }

    // Define a hora de chegada esperada
    public void setArrivingTime() {
        Date currentDate = new Date();
        long currentTime = currentDate.getTime();
        long tripTime = (long) (this.getEstimatedDuration() * 60 * 60000);
        long finalTime = tripTime + currentTime;
        Date itemDate = new Date(finalTime);
        this.arrivingTime = itemDate;
    }
}
