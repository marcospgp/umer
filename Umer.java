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

    private ArrayList vehicles = new ArrayList();
    private ArrayList drivers = new ArrayList();

    // Prevenir instanciação desta classe (googlar "java final class private constructor")
    private Umer() {
        throw new AssertionError();
    }

    /**
     * Creates a new vehicle
     *
     * @param x         The initial x position of the vehicle
     * @param y         The initial y position of the vehicle
     * @param type      The vehicle type
     * @param newDriver (optional) The new vehicle's driver. Should be null if not needed.
     */
    public void createVehicle(double x, double y, VehicleType type, Driver newDriver) {

        Vehicle newVehicle = new Vehicle(x, y, type, newDriver);
    }

    /**
     * Creates a new driver
     */
    public void createDriver(String email, String name, String password, String address, String birthdate) {

        Driver newDriver = new Driver(email, name, password, address, birthdate);
    }

    /**
     * Creates a new client
     */
    public void createClient(String email, String name, String password, String address, String birthdate, double posX, double posY) {

        Driver newClient = new Client(email, name, password, address, birthdate, posX, posY);
    }

    public static void main(String[] args) {
    }
}
