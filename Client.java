import java.util.*;
import java.io.Serializable;

public class Client extends User implements Serializable {

    private Point position;

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
     * Obter o veículo mais próximo (pode estar vazio, ou o condutor pode não estar em serviço)
     */
    public Vehicle getNearestVehicle(ArrayList<Vehicle> vehicles) {

        double closestDistanceSoFar = this.position.distanceTo(vehicles.get(0).getPosition());
        Vehicle closestVehicle = vehicles.get(0);

        double newDistance;

        for (int i = 1; i < vehicles.size(); i++) {

            newDistance = this.position.distanceTo(vehicles.get(i).getPosition());

            if (newDistance < closestDistanceSoFar) {

                closestDistanceSoFar = newDistance;
                closestVehicle = vehicles.get(i);
            }
        }

        return closestVehicle;
    }

    /**
     * Obter o veículo mais próximo ocupado por um condutor em serviço
     */
    public Vehicle getNearestReadyVehicle(ArrayList<Vehicle> vehicles) {

        double closestDistanceSoFar = Double.MAX_VALUE;
        Vehicle closestVehicle = null;

        double newDistance;
        double currentDriver;

        for (int i = 0; i < vehicles.size(); i++) {

            newDistance = this.position.distanceTo(vehicles.get(i).getPosition());

            if (newDistance < closestDistanceSoFar  &&
                vehicles.get(i).getDriver() != null &&
                vehicles.get(i).getDriver().isAvailable()
            ) {
                closestDistanceSoFar = newDistance;
                closestVehicle = vehicles.get(i);
            }
        }

        return closestVehicle;
    }

    /**
     * Obter o veículo com o identificador
     */
    public Vehicle getSpecificVehicle(ArrayList<Vehicle> vehicles, String taxiID) {

        Vehicle idVehicle = null;

        for (int i = 0; i < vehicles.size(); i++) {

            if (vehicles.get(i).getIdentifier().equals(taxiID) && vehicles.get(i).getDriver().isAvailable()) {
                idVehicle = vehicles.get(i);
                return idVehicle;
            }
        }

        return idVehicle;
    }

    /**
     * Obtem uma viagem a pedido do cliente.
     * @param userPosX -> posição X do cliente
     * @param userPosY -> posição Y do cliente
     * @param taxiID -> se for "" é porque quer o nearestVehicle, senão quer com ID especifico
    */
    public Trip getTrip(ArrayList<Vehicle> vehicles, double userPosX, double userPosY, double destPosX, double destPosY, String taxiID) {

        Trip newTrip = null;;
        Point userPos = new Point(userPosX, userPosY);
        Point destPos = new Point(destPosX, destPosY);
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
            tripVehicle = this.getSpecificVehicle(vehicles, taxiID);
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
