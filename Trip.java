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

    /**
     * Obtem uma viagem a pedido do cliente.
     * @param posX -> posição X do cliente
     * @param posY -> posição Y do cliente
     * @param taxiID -> se for "" é porque quer o nearestVehicle, senão quer com ID especifico
    */
    public Trip getTrip(double posX, double posY, String taxiID) {

    }
}
