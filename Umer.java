import java.util.*;
import java.util.ArrayDeque;

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


    private static User loggedAs = null;

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
    private static Vehicle createVehicle(double x, double y, String identifier, String type) {

        // determinar o tipo de veículo (e se existe esse tipo)
        VehicleType typeKnown = VehicleType.valueOf(type);

        Vehicle newVehicle = new Vehicle(x, y, identifier, typeKnown);

        Umer.vehicles.add(newVehicle);

        return newVehicle;
    }

    /**
     * Cria um novo condutor
     */
    private static Driver registerDriver(String email, String name, String password, String address, String birthdate) {

        Driver newDriver = new Driver(email, name, password, address, birthdate);

        Umer.drivers.add(newDriver);

        return newDriver;
    }

    /**
     * Cria um novo cliente
     */
    private static Client registerClient(String email, String name, String password, String address, String birthdate, double posX, double posY) {

        Client newClient = new Client(email, name, password, address, birthdate, posX, posY);

        Umer.clients.add(newClient);

        return newClient;
    }

    /**
     * Ocupa um veículo com um dado condutor
     */
    private static void assignDriverToVehicle(String driverEmail, String vehicleIdentifier) {

        Driver curDriver = null;

        // Loopar pelos condutores
        for (int i = 0; i < Umer.drivers.size(); i++) {

            if (Umer.drivers.get(i).getEmail().equals(driverEmail)) {

                curDriver = Umer.drivers.get(i);

            }
        }

        // Loopar pelos veículos para ver se aquele driver já está em algum. Se estiver, remove-o.
        // Também adiciona o Driver ao veículo escolhido.
        for (int i = 0; i < Umer.vehicles.size(); i++) {

            if ((Umer.vehicles.get(i).getDriver() != null) && (Umer.vehicles.get(i).getDriver().getEmail().equals(curDriver.getEmail()))) {

                Umer.vehicles.get(i).setDriver(null);

            }

            if (Umer.vehicles.get(i).getIdentifier().equals(vehicleIdentifier)) {

                Umer.vehicles.get(i).setDriver(curDriver);

            }
        }
    }

    /**
     * Tenta fazer login numa conta de utilizador
     *
     * @param email   O email do utilizador
     * @param passord A password do utilizador
     * @return        True se o login foi bem sucedido, caso contrário é retornado false
     */
    private static Boolean login(String email, String password) {

        // Loopar pelos clientes
        for (int i = 0; i < Umer.clients.size(); i++) {

            if (Umer.clients.get(i).getEmail().equals(email) && Umer.clients.get(i).getPassword().equals(password)) {

                Umer.loggedAs = (User) Umer.clients.get(i);

                return false;
            }
        }

        // Loopar pelos condutores
        for (int i = 0; i < Umer.drivers.size(); i++) {

            if (Umer.drivers.get(i).getEmail().equals(email) && Umer.drivers.get(i).getPassword().equals(password)) {

                Umer.loggedAs = (User) Umer.drivers.get(i);

                return true;
            }
        }

        return null;
    }

    /**
     * Tenta fazer logout de um utilizador
     *
     * @return        True se o logout foi bem sucedido, caso contrário é retornado false
     */
    private static boolean logout() {

        // Verificar se está algum utilizador logado
        if (Umer.loggedAs != null) {
            Umer.loggedAs = null;
            return true;
        }

        return false;
    }

    /**
     * Retorna true caso exista o veiculo com aquele ID e esteja disponível.
     * Serve de guarda para quando chamamos certo veiculo, para que nao seja
     * feito o pedido de viagem a um veiculo que nao existe ou esta indisponivel.
     */
    private static boolean taxiIsAvailable(String taxiID) {

        for (int i = 0; i < vehicles.size(); i++) {

            if (vehicles.get(i).getIdentifier().equals(taxiID) && vehicles.get(i).getDriver().isAvailable()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retorna true caso exista algum veiculo disponível.
     * Serve de guarda para quando chamamos qualquer veiculo, para que nao seja
     * feito o pedido de viagem a um veiculo que nao existe ou esta indisponivel.
     */
    private static boolean someTaxiIsAvailable() {

        for (int i = 0; i < vehicles.size(); i++) {

            if (vehicles.get(i).getDriver().isAvailable()) {
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
        // Viagem adicionada ao histórico
        a.tripHistory.add(newTrip);
        // Viagem começa a decorrer
        Umer.tripsUnderway.add(newTrip);

        return newTrip;
    }

    /**
     * Retorna a lista de viagens do utilizador logado
     * em forma de ArrayList de Strings
     */
    private static ArrayList<String> getTripHistory () {

        ArrayList<String> trips = new ArrayList<String>();

        for (int i = 0; i < Umer.loggedAs.tripHistory.size(); i++) {
            trips.add(Umer.loggedAs.tripHistory.get(i).toString());
        }

        return trips;
    }

    public static void main(String[] args) {
        int index = 0;

        System.out.println("Starting test");
        //
        System.out.println("Creating user vitor with password gay at (0.5, 0.324)");

        Client vitor = registerClient("vitor@hotmail.com", "vitor", "gay", "casa", "yesterday", (double) 0.5, (double) 0.324);

        /* TESTING WRITING/READING CLIENTS
        IO ioclients;
        ioclients = new IO();
        ioclients.Write(clients,3);
        ioclients.Read(clients,3);
         //-----------------------------------//
         //-----------------------------------//
        */

        System.out.println("Creating driver sergio with password gay at (2,3)");

        Driver sergio = registerDriver("sergio@hotmail.com", "sergio", "gay", "casa", "couple weeks ago");
        //
        System.out.println("Creating driver marcos with password forte at (4,2)");

        Driver marcos = registerDriver("marcos@hotmail.com", "marcos", "forte", "casa", "many a year ago");

        /*
        TESTING WRITING/READING DRIVERS
        IO iodrivers;
        iodrivers = new IO();
        iodrivers.Write(drivers,2);
        iodrivers.Read(drivers,2);
        -----------------------------------
        -----------------------------------
        */

        System.out.println("Setting driver marcos to available");

        marcos.toggleAvailable();
        //-----------------------------------
        System.out.println("Setting driver sergio to available");

        sergio.toggleAvailable();
        //
        System.out.println("Logging in as vitor with password gay");

        Umer.login(vitor.getEmail(), vitor.getPassword());
        //-----------------------------------
        System.out.println("Finding nearest (ready to travel) vehicle to currently logged in user (vitor)");

        Vehicle nearestVehicle = Umer.getNearestReadyVehicle();

        System.out.println(nearestVehicle); // Dá null porque ainda não há veículos
        //
        System.out.println("Creating LIGHT taxi");

        Vehicle taxi1 = createVehicle((double) 1.0, (double) 2.3, "taxi primeiro", "LIGHT");
        System.out.println(taxi1);
        assignDriverToVehicle("sergio@hotmail.com", "taxi primeiro");
        System.out.println(taxi1);

        Vehicle taxi2 = createVehicle((double) 0.0, (double) 0.0, "taxi origem", "LIGHT");
        assignDriverToVehicle("marcos@hotmail.com", "taxi origem");

        /*
        TESTING WRITING/READING VEHICLES
        IO iovehicles;
        iovehicles = new IO();
        iovehicles.Write(vehicles,1);
        iovehicles.Read(vehicles,1);
        -----------------------------------
        -----------------------------------
        */

        System.out.println("Finding nearest (ready to travel) vehicle to currently logged in user (vitor)");

        nearestVehicle = Umer.getNearestReadyVehicle();

        System.out.println(nearestVehicle.getIdentifier()); // Dá "taxi origem"
        //-----------------------------------
        System.out.println("Finding vehicle with identifier to currently logged in user (vitor)");

        Vehicle identifierVehicle = Umer.getSpecificVehicle("taxi primeiro");

        System.out.println(identifierVehicle.getIdentifier()); // Dá "taxi primeiro"
        //
        System.out.println("Arranging trip to currently logged in user (vitor)");
        String taxiName = null;

        Trip viagem1 = null;
        taxiName = "";
        System.out.println("\nViagem que chama o mais próximo:");
        // caso exista o taxi e esteja disponivel
        if (Umer.someTaxiIsAvailable()) {
            viagem1 = Umer.getTrip(0.5, 0.7, 2.0, 2.0, taxiName); // Chama o mais proximo
            System.out.println("Vehicle: " + viagem1.getVehicle().getIdentifier());
            System.out.println("Driver: " + viagem1.getDriver().getName());
            System.out.println("Origin: " + "(" + viagem1.getOrigin().getX() + "," + viagem1.getOrigin().getY() + ")");
            System.out.println("Destination: " + "(" + viagem1.getDestination().getX() + "," + viagem1.getDestination().getY() + ")");
            System.out.println("Estimated duration: " + viagem1.getEstimatedDuration());
            System.out.println("Real Duration: " + viagem1.getRealDuration());
            viagem1.setTimeStarted();
            System.out.println("Time Started: " + viagem1.getTimeStarted());
            viagem1.setArrivingTime();
            System.out.println("Arriving time: " + viagem1.getArrivingTime());
            System.out.println("Cost: " + viagem1.getCost());
        }
        // o taxi nao esta disponivel
        else {
            System.out.println("O veículo " + taxiName + " não existe ou não está disponível.");
        }


        Trip viagem2 = null;
        taxiName = "taxi primeiro";
        System.out.println("\nViagem que chama o: 'taxi primeiro'");
        // caso exista o taxi e esteja disponivel
        if (Umer.taxiIsAvailable(taxiName)) {
            viagem2 = Umer.getTrip(1.0, 2.3, 3.2, 2.4, taxiName); // Chama pelo ID
            System.out.println("Vehicle: " + viagem2.getVehicle().getIdentifier());
            System.out.println("Driver: " + viagem2.getDriver().getName());
            System.out.println("Origin: " + "(" + viagem2.getOrigin().getX() + "," + viagem2.getOrigin().getY() + ")");
            System.out.println("Destination: " + "(" + viagem2.getDestination().getX() + "," + viagem2.getDestination().getY() + ")");
            System.out.println("Estimated duration: " + viagem2.getEstimatedDuration());
            System.out.println("Real Duration: " + viagem2.getRealDuration());
            viagem2.setTimeStarted();
            System.out.println("Time Started: " + viagem2.getTimeStarted());
            viagem2.setArrivingTime();
            System.out.println("Arriving time: " + viagem2.getArrivingTime());
            System.out.println("Cost: " + viagem2.getCost());
        }
        // o taxi nao esta disponivel
        else {
            System.out.println("O veículo " + taxiName + " não existe ou não está disponível.");
        }


        Trip viagem3 = null;
        taxiName = "taxi que nao existe";
        System.out.println("\nViagem que chama o: 'taxi que nao existe'");
        // caso exista o taxi e esteja disponivel
        if (Umer.taxiIsAvailable(taxiName)) {
            viagem3 = Umer.getTrip(1.0, 2.3, 3.2, 2.4, taxiName); // Chama pelo ID
            System.out.println("Vehicle: " + viagem3.getVehicle().getIdentifier());
            System.out.println("Driver: " + viagem3.getDriver().getName());
            System.out.println("Origin: " + "(" + viagem3.getOrigin().getX() + "," + viagem3.getOrigin().getY() + ")");
            System.out.println("Destination: " + "(" + viagem3.getDestination().getX() + "," + viagem3.getDestination().getY() + ")");
            System.out.println("Estimated duration: " + viagem3.getEstimatedDuration());
            System.out.println("Real Duration: " + viagem3.getRealDuration());
            viagem3.setTimeStarted();
            System.out.println("Time Started: " + viagem3.getTimeStarted());
            viagem3.setArrivingTime();
            System.out.println("Arriving time: " + viagem3.getArrivingTime());
            System.out.println("Cost: " + viagem3.getCost());
        }
        // o taxi nao esta disponivel
        else {
            System.out.println("O veículo " + taxiName + " não existe ou não está disponível.");
        }

        //----------------------------------- HISTORICO DE VIAGENS ------------------------------
        System.out.println("\nHISTORICO DE VIAGENS:");
        System.out.println(getTripHistory());



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

        /* TESTING WRITING/READING TRIPHISTORY
        IO iotriphistory;
        iotriphistory = new IO();
        iotriphistory.Write(tripHistory,4);
        iotriphistory.Read(clients,4);
        //-----------------------------------//
        //-----------------------------------//

        /* TESTING WRITING/READING TRIPSUNDERWAY
        IO iotripsunderway;
        iotripsunderway = new IO();
        iotripsunderway.Write(tripsUnderway,5);
        iotripsunderway.Read(tripsUnderway,5);
        //-----------------------------------//
        //-----------------------------------//
        */

       //--------------- QUEUE -------------//
       Queue queue ;
       queue = new Queue();

       // Cria um ArrayDeque que serve como fila
       ArrayDeque<Vehicle> list = new ArrayDeque<>();
       // Insere o Taxi na cauda da fila de espera
       queue.add(taxi1,list);
       queue.add(taxi2,list);
       // Print do array com os taxis inseridos
       System.out.println(list);
       // Tamanho do array
       System.out.println("Tamanho da fila de espera: " + queue.size(list));
       // Remove o Taxi da cabeça da fila de espera
       queue.remove(list);
       // Print do array com 1 taxi removido
       System.out.println(list);
    }
}
