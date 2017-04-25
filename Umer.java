import java.util.*;

/**
 * O Umer é um programa que permite efetuar a gerência de viagens entre clientes e motoristas.
 * Esta é a classe principal que gere o programa.
 *
 * @author Marcos Pereira, Sérgio Oliveira, Vítor Castro
 * @version 0.1
 * @since 2017-04-23
 */
public final class Umer {

    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    private ArrayList<Driver> drivers = new ArrayList<Driver>();
    private ArrayList<Client> clients = new ArrayList<Client>();

    // Prevenir instanciação desta classe (googlar "java final class private constructor")
    private Umer() {
        throw new AssertionError();
    }

    /**
     * Cria um novo veículo
     *
     * @param x         The initial x position of the vehicle
     * @param y         The initial y position of the vehicle
     * @param type      The vehicle type
     * @param newDriver (optional) The new vehicle's driver. Should be null if not needed.
     */
    private void createVehicle(double x, double y, VehicleType type, Driver newDriver) {

        Vehicle newVehicle = new Vehicle(x, y, type, newDriver);

        this.vehicles.add(newVehicle);
    }

    /**
     * Cria um novo condutor
     */
    private void createDriver(String email, String name, String password, String address, String birthdate) {

        Driver newDriver = new Driver(email, name, password, address, birthdate);

        this.drivers.add(newDriver);
    }

    /**
     * Cria um novo cliente
     */
    private void createClient(String email, String name, String password, String address, String birthdate, double posX, double posY) {

        Client newClient = new Client(email, name, password, address, birthdate, posX, posY);

        this.clients.add(newClient);
    }

    private void occupyVehicle(Vehicle theVehicle, Driver newDriver) {
        theVehicle.setDriver(newDriver);
    }

    public static void main(String[] args) {
    }
}
