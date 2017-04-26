import java.util.*;

public class Client extends User {

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

    public String getemail() {
      return email;
    }

    public String getname() {
      return name;
    }

    public String getpassword() {
      return password;
    }

    public String getaddress() {
      return address;
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
}
