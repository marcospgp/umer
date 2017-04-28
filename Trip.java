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
    private double timeStarted;
    private double cost;
    private boolean completed;
    private Date arrivingTime;

    public Trip(Vehicle vehicle,
                Driver driver,
                Point origin,
                Point destination,
                double estimatedDuration,
                double realDuration,
                double timeStarted,
                double cost
    ) {
        this.vehicle = vehicle;
        this.driver = driver;
        this.origin = origin;
        this.destination = destination;
        this.estimatedDuration = estimatedDuration;
        this.realDuration = realDuration;
        this.timeStarted = timeStarted;
        this.cost = cost;
        this.completed = false;
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

    public double getTimeStarted() {
        return this.timeStarted;
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

    public void setIsCompleted(boolean completo) {
        this.completed = completo;
    }

    // Define a hora de chegada esperada
    public void setArrivingTime (Trip viagem) {
        // TENHO DE USAR JODA!

        long itemLong = (long) (this.getEstimatedDuration() * 1000);
        Date itemDate = new Date(itemLong);
        this.arrivingTime = itemDate;
    }
}
