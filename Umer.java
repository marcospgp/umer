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
    private ArrayList<Trip> tripHistory = new ArrayList<Trip>();
    private ArrayList<Trip> tripsUnderway = new ArrayList<Trip>();

    private User loggedAs;

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

    /**
     * Ocupa um veículo com um dado condutor
     */
    private void occupyVehicle(Vehicle theVehicle, Driver newDriver) {
        theVehicle.setDriver(newDriver);
    }

    /**
     * Tenta fazer login numa conta de utilizador
     *
     * @param email   O email do utilizador
     * @param passord A password do utilizador
     * @return        True se o login foi bem sucedido, caso contrário é retornado false
     */
    private boolean login(String email, String password) {

        // Loopar pelos clientes
        for (int i = 0; i < this.clients.size(); i++) {

            if (this.clients.get(i).getEmail().equals(email) && this.clients.get(i).getPassword().equals(password)) {

                this.loggedAs = (User) this.clients.get(i);

                return true;
            }
        }

        // Loopar pelos condutores
        for (int i = 0; i < this.drivers.size(); i++) {

            if (this.drivers.get(i).getEmail().equals(email) && this.drivers.get(i).getPassword().equals(password)) {

                this.loggedAs = (User) this.drivers.get(i);

                return true;
            }
        }

        return false;
    }

    /**
     * Retorna o veículo pronto a viajar mais próximo do utilizador
     * logado atualmente, apenas se o utilizador for um cliente
     */
    private Vehicle getNearestReadyVehicle() {

        if (!this.loggedAs instanceof Client) {
            throw new java.lang.Error("Tried to find nearest vehicle, but current user is not a client.");
        }

        return this.loggedAs.getNearestReadyVehicle();
    }

    public static void main(String[] args) {

        System.out.println("Starting test");

        System.out.println("Creating user vitor with password gay at (0.5, 0.324)");

        Client vitor = createClient("vitor@hotmail.com", "vitor", "gay", "casa", "yesterday", (double) 0.5, (double) 0.324);

        System.out.println("Creating driver sergio with password gay at (2,3)");

        Driver sergio = createDriver("sergio@hotmail.com", "sergio", "gay", "casa", "couple weeks ago", (double) 2, (double) 3);

        System.out.println("Creating driver marcos with password forte at (4,2)");

        Driver marcos = createDriver("marcos@hotmail.com", "marcos", "forte", "casa", "many a year ago", (double) 4, (double) 2);

        System.out.println("Setting driver marcos to available");

        marcos.toggleAvailable();

        System.out.println("Logging in as vitor with password gay");

        this.login(vitor.getEmail(), vitor.getPassword());

        System.out.println("Finding nearest (ready to travel) vehicle to currently logged in user (vitor)");

        Vehicle nearestVehicle = this.getNearestReadyVehicle();
    }
}
