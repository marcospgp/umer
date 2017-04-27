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

    private static ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    private static ArrayList<Driver> drivers = new ArrayList<Driver>();
    private static ArrayList<Client> clients = new ArrayList<Client>();
    private static ArrayList<Trip> tripHistory = new ArrayList<Trip>();
    private static ArrayList<Trip> tripsUnderway = new ArrayList<Trip>();

    private static User loggedAs;

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
    private static Vehicle createVehicle(double x, double y, String identifier, VehicleType type, Driver newDriver) {

        Vehicle newVehicle = new Vehicle(x, y, identifier, type, newDriver);

        Umer.vehicles.add(newVehicle);

        return newVehicle;
    }

    /**
     * Cria um novo condutor
     */
    private static Driver createDriver(String email, String name, String password, String address, String birthdate) {

        Driver newDriver = new Driver(email, name, password, address, birthdate);

        Umer.drivers.add(newDriver);

        return newDriver;
    }

    /**
     * Cria um novo cliente
     */
    private static Client createClient(String email, String name, String password, String address, String birthdate, double posX, double posY) {

        Client newClient = new Client(email, name, password, address, birthdate, posX, posY);

        Umer.clients.add(newClient);

        return newClient;
    }

    /**
     * Ocupa um veículo com um dado condutor
     */
    private static void occupyVehicle(Vehicle theVehicle, Driver newDriver) {
        theVehicle.setDriver(newDriver);
    }

    /**
     * Tenta fazer login numa conta de utilizador
     *
     * @param email   O email do utilizador
     * @param passord A password do utilizador
     * @return        True se o login foi bem sucedido, caso contrário é retornado false
     */
    private static boolean login(String email, String password) {

        // Loopar pelos clientes
        for (int i = 0; i < Umer.clients.size(); i++) {

            if (Umer.clients.get(i).getEmail().equals(email) && Umer.clients.get(i).getPassword().equals(password)) {

                Umer.loggedAs = (User) Umer.clients.get(i);

                return true;
            }
        }

        // Loopar pelos condutores
        for (int i = 0; i < Umer.drivers.size(); i++) {

            if (Umer.drivers.get(i).getEmail().equals(email) && Umer.drivers.get(i).getPassword().equals(password)) {

                Umer.loggedAs = (User) Umer.drivers.get(i);

                return true;
            }
        }

        return false;
    }

    /**
     * Retorna o veículo pronto a viajar mais próximo do utilizador
     * logado atualmente, apenas se o utilizador for um cliente
     */
    private static Vehicle getNearestReadyVehicle() {

        if ( !(Umer.loggedAs instanceof Client) ) {
            throw new java.lang.Error("Tried to find nearest vehicle, but current user is not a client.");
        }

        Client a = (Client) Umer.loggedAs;

        return a.getNearestReadyVehicle(Umer.vehicles);
    }

    /**
     * Retorna o veículo com o ID específico ao utilizador
     * logado atualmente, apenas se o utilizador for um cliente
     */
    private static Vehicle getSpecificVehicle(String taxiIdentifier) {

        if ( !(Umer.loggedAs instanceof Client) ) {
            throw new java.lang.Error("Tried to find a specific vehicle, but current user is not a client.");
        }

        Client a = (Client) Umer.loggedAs;

        return a.getSpecificVehicle(Umer.vehicles, taxiIdentifier);
    }

    /**
     * Retorna o veículo com o ID específico ao utilizador
     * logado atualmente, apenas se o utilizador for um cliente
     */
    private static Trip getTrip(double userPosX, double userPosY, double destPosX, double destPosY, String taxiID) {

        Trip newTrip = null;

        if ( !(Umer.loggedAs instanceof Client) ) {
            throw new java.lang.Error("Tried to find a specific vehicle, but current user is not a client.");
        }

        Client a = (Client) Umer.loggedAs;
        newTrip = a.getTrip(Umer.vehicles, userPosX, userPosY, destPosX, destPosY, taxiID);
        // Viagem começa a decorrer
        Umer.tripsUnderway.add(newTrip);

        return newTrip;
    }

    public static void main(String[] args) {

        int index = 0;

        System.out.println("Starting test");
        //
        System.out.println("Creating user vitor with password gay at (0.5, 0.324)");

        Client vitor = createClient("vitor@hotmail.com", "vitor", "gay", "casa", "yesterday", (double) 0.5, (double) 0.324);
        //-----------------------------------
        System.out.println("Creating driver sergio with password gay at (2,3)");

        Driver sergio = createDriver("sergio@hotmail.com", "sergio", "gay", "casa", "couple weeks ago");
        //
        System.out.println("Creating driver marcos with password forte at (4,2)");

        Driver marcos = createDriver("marcos@hotmail.com", "marcos", "forte", "casa", "many a year ago");
        //-----------------------------------
        System.out.println("Setting driver marcos to available");

        marcos.toggleAvailable();
        //
        System.out.println("Logging in as vitor with password gay");

        Umer.login(vitor.getEmail(), vitor.getPassword());
        //-----------------------------------
        System.out.println("Finding nearest (ready to travel) vehicle to currently logged in user (vitor)");

        Vehicle nearestVehicle = Umer.getNearestReadyVehicle();

        System.out.println(nearestVehicle); // Dá null porque ainda não há veículos
        //
        System.out.println("Creating LIGHT taxi");

        Vehicle taxi1 = createVehicle((double) 1.0, (double) 2.3, "taxi primeiro", VehicleType.LIGHT, marcos);

        Vehicle taxi2 = createVehicle((double) 0.0, (double) 0.0, "taxi origem", VehicleType.LIGHT, marcos);

        System.out.println("Finding nearest (ready to travel) vehicle to currently logged in user (vitor)");

        nearestVehicle = Umer.getNearestReadyVehicle();

        System.out.println(nearestVehicle.getIdentifier()); // Dá "taxi origem"
        //-----------------------------------
        System.out.println("Finding vehicle with identifier to currently logged in user (vitor)");

        Vehicle identifierVehicle = Umer.getSpecificVehicle("taxi primeiro");

        System.out.println(identifierVehicle.getIdentifier()); // Dá "taxi primeiro"
        //
        System.out.println("Arranging trip to currently logged in user (vitor)");

        Trip viagem1 = Umer.getTrip(0.5, 0.7, 2.0, 2.0, ""); // Chama o mais proximo

        Trip viagem2 = Umer.getTrip(0.5, 0.7, 2.0, 2.0, "taxi primeiro"); // Chama pelo ID



        /*  --- TESTES DE ARRAYS ---
        // imprimir clientes
        System.out.println("\nFull list of clients:");
        for (index = 0; index < Umer.clients.size(); index++) {
            System.out.print(Umer.clients.get(index).getName() + ", ");
            System.out.print(Umer.clients.get(index).getEmail() + ", ");
            System.out.print(Umer.clients.get(index).getPassword() + ", ");
            System.out.print(Umer.clients.get(index).getAddress() + ", ");
            System.out.println(Umer.clients.get(index).getBirthdate() + ";");
        }

        // imprimir drivers
        System.out.println("\nFull list of drivers:");
        for (index = 0; index < Umer.drivers.size(); index++) {
            System.out.print(Umer.drivers.get(index).getName() + ", ");
            System.out.print(Umer.drivers.get(index).getEmail() + ", ");
            System.out.print(Umer.drivers.get(index).getPassword() + ", ");
            System.out.print(Umer.drivers.get(index).getAddress() + ", ");
            System.out.println(Umer.drivers.get(index).getBirthdate() + ";");
            System.out.println(Umer.drivers.get(index).getFulfillment() + ", ");
            System.out.println(Umer.drivers.get(index).getRating() + ", ");
            System.out.println(Umer.drivers.get(index).getKms() + ", ");
            System.out.println(Umer.drivers.get(index).isAvailable() + ";");
        }
        */
    }
}
