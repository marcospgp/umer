/**
 * Classe que guarda informação sobre uma viagem efetuada no programa.
 */
public class Trip {

    public Vehicle vehicle;
    public Driver driver;
    public Point origin;
    public Point destination;
    public double estimatedDuration;
    public double realDuration;
    public double timeStarted;
    public double cost;

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
    }
}
