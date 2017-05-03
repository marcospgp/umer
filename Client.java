import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.io.Serializable;

public class Client extends User implements Serializable {

    private Point position;
    private double moneySpent;

    /**
     * Constructor for objects of class Client
     */
    public Client(String newEmail, String newName, String newPassword, String newAddress, String newBirthdate, double newPosX, double newPosY) {
        this.email = newEmail;
        this.name = newName;
        this.password = newPassword;
        this.address = newAddress;
        this.birthdate = newBirthdate;
        this.position = new Point<Double>(newPosX, newPosY);
    }

    /**
      * Obter a posição do utilizador
      */
    public Point getPosition() {
        return this.position;
    }

    /**
      * Definir a posição do utilizador
      */
    public void setPosition(Point newPos) {
        this.position = newPos;
    }

    public double getMoneySpent () {
        return this.moneySpent;
    }

    /**
     * Obter o veículo mais próximo (pode estar vazio, ou o condutor pode não estar em serviço)
     */
    public Vehicle getNearestVehicle(HashMap vehicles) {

        if (vehicles.size() < 1) {
            return null;
        }

        Set vehicleSet = vehicles.entrySet();
        Iterator i = vehicleSet.iterator();

        Vehicle curVehicle = (Vehicle) i.next();

        double closestDistanceSoFar = this.position.distanceTo(curVehicle.getPosition());
        Vehicle closestVehicle = curVehicle;

        double newDistance;

        while (i.hasNext()) {

            curVehicle = (Vehicle) i.next();

            newDistance = this.position.distanceTo(curVehicle.getPosition());

            if (newDistance < closestDistanceSoFar) {

                closestDistanceSoFar = newDistance;
                closestVehicle = curVehicle;
            }
        }

        return closestVehicle;
    }

    /**
     * Obter o veículo mais próximo ocupado por um condutor em serviço
     */
    public Vehicle getNearestReadyVehicle(HashMap vehicles) {

        if (vehicles.size() < 1) {
            return null;
        }

        Set vehicleSet = vehicles.entrySet();
        Iterator i = vehicleSet.iterator();

        double closestDistanceSoFar = Double.MAX_VALUE;
        Vehicle closestVehicle = null;

        double newDistance;

        while (i.hasNext()) {

            Vehicle curVehicle = (Vehicle) i.next();

            newDistance = this.position.distanceTo(curVehicle.getPosition());

            if (newDistance < closestDistanceSoFar  &&
                curVehicle.getDriver() != null &&
                curVehicle.getDriver().isAvailable()
            ) {
                closestDistanceSoFar = newDistance;
                closestVehicle = curVehicle;
            }
        }

        return closestVehicle;
    }

    /**
     * Obtem uma viagem a pedido do cliente.
     * @param userPosX -> posição X do cliente
     * @param userPosY -> posição Y do cliente
     * @param taxiID -> se for "" é porque quer o nearestVehicle, senão quer com ID especifico
    */
    public Trip getTrip(HashMap vehicles, Point userPos, Point destPos, String taxiID) {

        Trip newTrip = null;
        Vehicle tripVehicle = null;
        Driver tripDriver = null;
        double distanceToClient = 0.0, distanceToDest = 0.0, totalDistance = 0.0;
        double timeToClient = 0.0, timeToDest = 0.0, totalTime = 0.0;
        double tripPrice = 0.0;

        // Para o caso de querer o taxi mais próximo
        if (taxiID.equals("")) {
            tripVehicle = this.getNearestReadyVehicle(vehicles);
            tripDriver = tripVehicle.getDriver();
            distanceToClient = userPos.distanceTo(tripVehicle.getPosition());
            distanceToDest = userPos.distanceTo(destPos);
            totalDistance = distanceToClient + distanceToDest;
            timeToClient = tripVehicle.getTripTime(distanceToClient);
            timeToDest = tripVehicle.getTripTime(distanceToDest);
            totalTime = timeToClient + timeToDest;
            tripPrice = tripVehicle.getTripPrice(totalDistance);
        }

        // introduziu identificador para um taxi
        else{
            tripVehicle = (Vehicle) vehicles.get(taxiID);
            tripDriver = tripVehicle.getDriver();
            distanceToClient = userPos.distanceTo(tripVehicle.getPosition());
            distanceToDest = userPos.distanceTo(destPos);
            totalDistance = distanceToClient + distanceToDest;
            timeToClient = tripVehicle.getTripTime(distanceToClient);
            timeToDest = tripVehicle.getTripTime(distanceToDest);
            totalTime = timeToClient + timeToDest;
            tripPrice = tripVehicle.getTripPrice(totalDistance);
        }

        // O segundo totalTime é o tempo real da viagem, que ainda não se implementou por isso vai ser certo;
        newTrip = new Trip(tripVehicle, tripDriver, userPos, destPos, totalTime, totalTime, tripPrice);
        newTrip.setIsCompleted(false); // viagem ainda não está completa

        return newTrip;
    }
        @Override
        public String toString() {
        return "Email: " + this.email + ", "
             + "Name: " + this.name + ", "
             + "Password: " + this.password + ", "
             + "Address: " + this.address + ", "
             + "Birthdate: " + this.birthdate + ", "
             + "Location: " + this.position;
    }
}
