import java.text.Format;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayDeque;
import javax.swing.UIManager;
import java.awt.EventQueue;

/**
 * O Umer é um programa que permite efetuar a gerência de viagens entre clientes e motoristas.
 * Esta é a classe principal que gere o programa.
 *
 * @author Marcos Pereira, Sérgio Oliveira, Vítor Castro
 * @version 0.1
 * @since 2017-04-23
 */
public final class Umer {

    private static HashMap<String, Vehicle> vehicles = new HashMap<String, Vehicle>(); // vehicleId   -> Vehicle
    private static HashMap<String, Driver> drivers = new HashMap<String, Driver>();  // driverEmail -> Driver
    private static HashMap<String, Client> clients = new HashMap<String, Client>();  // clientEmail -> Client
    private static ArrayList<Trip> tripsCompleted = new ArrayList<Trip>();
    private static ArrayList<Trip> tripsUnderway = new ArrayList<Trip>();
    private static ArrayDeque<String> waitingList = new ArrayDeque<String>();

    private static int fastForwardValue = 0; // Segundos a adicionar ao getTime(), aumenta a partir de um botão de fast forward presente na GUI
    private static User loggedAs = null;
    private static boolean userUpdates = false;

    // Prevenir instanciação desta classe (googlar "java final class private constructor")
    private Umer() {
        throw new AssertionError();
    }


    /**
     * Cria um novo veículo
     *
     * @param x         	The initial x position of the vehicle
     * @param y         	The initial y position of the vehicle
     * @param identifier	The vehicle identifier
     * @param type      	The vehicle type
     * @param hasWaitingList Indicating if this vehicle has waitingList
     * @return Vehicle 		The new vehicle.
     */
    public static Vehicle createVehicle(double x, double y, String identifier, String type, boolean hasWaitingList) {

        // TODO - Usar hasWaitingList e usar try catch abaixo no VehicleType.valueOf

        // determinar o tipo de veículo (e se existe esse tipo)
        VehicleType typeKnown = VehicleType.valueOf(type);

        Vehicle newVehicle = new Vehicle(x, y, identifier, typeKnown, hasWaitingList);

        vehicles.put(identifier, newVehicle);

        return newVehicle;
    }


    /**
     * Cria um novo condutor
     *
     * @param email         The driver's account email
     * @param name         	The driver's name
     * @param password		The driver's account password
     * @param address      	The driver's address
     * @param birthdate 	The driver's birthdate
     * @return Driver 		The new driver.
     */
    public static Driver registerDriver(String email, String name, String password, String address, String birthdate) {

        Driver newDriver = new Driver(email, name, password, address, birthdate);

        drivers.put(email, newDriver);

        return newDriver;
    }


    /**
     * Cria um novo cliente
     *
     * @param email         The client's account email
     * @param name         	The client's name
     * @param password		The client's account password
     * @param address      	The client's address
     * @param birthdate 	The client's birthdate
     * @param x         	The initial x position of the client
     * @param y         	The initial y position of the client
     * @return Client 		The new client.
     */
    public static Client registerClient(String email, String name, String password, String address, String birthdate, double posX, double posY) {

        Client newClient = new Client(email, name, password, address, birthdate, posX, posY);

        clients.put(email, newClient);

        return newClient;
    }


    /**
     * Ocupa um veículo com um dado condutor.
     * @param driverEmail         O mail do condutor.
     * @param vehicleIdentifier   O identificador do veículo.
     */
    public static void assignDriverToVehicle(String driverEmail, String vehicleIdentifier) {

        Driver curDriver = drivers.get(driverEmail);

        if (curDriver == null || vehicles.get(vehicleIdentifier) == null) {
            return;
        }

        // Iterar pelos veículos para ver se aquele driver já está em algum. Se estiver, remove-o.

        Set vehicleSet = vehicles.entrySet();
        Iterator<Vehicle> i = vehicleSet.iterator();

        while (i.hasNext()) {

            Map.Entry entry = (Map.Entry) i.next();
            Vehicle curVehicle = (Vehicle) entry.getValue();

            if (curVehicle.getDriver() != null && curVehicle.getDriver().getEmail().equals(driverEmail)) {

                curVehicle.setDriver(null);
            }
        }

        // Associar o condutor ao veículo
        vehicles.get(vehicleIdentifier).setDriver(curDriver);

        // Reportar updates para o loop da GUI
        userUpdates = true;
    }


    /**
     * Tenta fazer login numa conta de utilizador.
     *
     * @param email   O email do utilizador
     * @param passord A password do utilizador
     * @return        True se o user for um condutor, false se for um cliente,
     *                null caso o login tenha sido inválido.
     */
    public static Boolean login(String email, String password) {

        if (clients.get(email) != null && clients.get(email).getPassword().equals(password)) {

            loggedAs = clients.get(email);

            // Reportar updates para o loop da GUI
            userUpdates = true;

            return false;
        }

        if (drivers.get(email) != null && drivers.get(email).getPassword().equals(password)) {

            loggedAs = drivers.get(email);

            // Reportar updates para o loop da GUI
            userUpdates = true;

            return true;
        }

        return null;
    }


    /**
     * Tenta fazer logout de um utilizador
     *
     * @return        True se o logout foi bem sucedido, caso contrário é retornado false
     */
    public static boolean logout() {

        // Verificar se está algum utilizador logado
        if (Umer.loggedAs != null) {
            Umer.loggedAs = null;
            return true;
        }

        userUpdates = false;

        return false;
    }


    /**
     * Retorna true caso exista o veiculo com aquele ID e esteja disponível.
     * Serve de guarda para quando chamamos certo veiculo, para que nao seja
     * feito o pedido de viagem a um veiculo que nao existe ou esta indisponivel.
     * @param taxiID O identificador do taxi a pesquisar.
     */
    private static boolean isTaxiAvailable(String taxiID) {

        Vehicle taxi = vehicles.get(taxiID);

        if (taxi == null || taxi.getDriver() == null || taxi.getIsInUse()) {
            return false;
        }

        return taxi.getDriver().isAvailable();
    }


    /**
     * Retorna true caso exista algum veiculo disponível.
     * Serve de guarda para quando chamamos qualquer veiculo, para que nao seja
     * feito o pedido de viagem a um veiculo que nao existe ou esta indisponivel.
     */
    private static boolean isSomeTaxiAvailable() {

        Iterator i = vehicles.entrySet().iterator();

        while (i.hasNext()) {

            Map.Entry entry = (Map.Entry) i.next();
            Vehicle curVehicle = (Vehicle) entry.getValue();

            if (curVehicle.getDriver() != null && curVehicle.getDriver().isAvailable() && !curVehicle.getIsInUse()) {
                return true;
            }
        }

        return false;
    }


    /**
     * Retorna o veículo pronto a viajar mais próximo do utilizador
     * logado atualmente, apenas se o utilizador for um cliente.
     */
    private static Vehicle getNearestReadyVehicle() {

        if ( !(Umer.loggedAs instanceof Client) ) {
            throw new java.lang.Error("Tried to find nearest vehicle, but current user is not a client.");
        }

        Client client = (Client) Umer.loggedAs;

        return client.getNearestReadyVehicle(vehicles);
    }

    private static boolean waitingListCar(String taxiID) {

    	Vehicle taxi = vehicles.get(taxiID);

        if (taxi == null || taxi.getDriver() == null) {
            return false;
        }

        return taxi.getDriver().isAvailable();
    }


    /**
     * Inicia uma viagem, se possível. Se não receber um taxiID, procura pelo mais próximo.
     * Caso receba alguma taxiID, procura por esse. Se não for possível a viagem, não a inicia.
     * Pode também adicionar na waitingList de certo veículo, caso seja possível.
     * Atualiza posições e a viagem, adiciona a históricos e informa veículo e cliente ocupados.
     * @param taxiID Identificador do taxi
     * @param destPosX Coordenada x de destino escolhido
     * @param destPosY Coordenada y de destino escolhido
     */
    public static void startTrip(String taxiID, double destPosX, double destPosY) {

        Trip newTrip = null;
        Point destPos = new Point(destPosX, destPosY);

        if ( !(Umer.loggedAs instanceof Client) ) {
            throw new java.lang.Error("Tried to find a specific vehicle, but current user is not a client.");
        }

        // procura por qualquer taxi caso seja string vazia ou por especifico caso nao seja
        if ( (taxiID.equals("") && isSomeTaxiAvailable()) || (isTaxiAvailable(taxiID)) ) {
            Client a = (Client) Umer.loggedAs;
            newTrip = a.getTrip(vehicles, a.getPosition(), destPos, taxiID);
            // Viagem adicionada ao histórico do cliente
            a.tripHistory.add(newTrip);
            // Viagem adicionada às viagens under rating
            a.underEvalDrivers.add(newTrip.getDriver().getEmail());
            // Viagem começa a decorrer
            Umer.tripsUnderway.add(newTrip);
            // Atualizar posição do cliente
            a.setPosition(destPos);
            // Adicionar custo da viagem ao cliente
            a.addMoneySpent(newTrip.getCost());
            // Viagem adicionada ao histórico do condutor
            newTrip.getDriver().tripHistory.add(newTrip);
            // Atualizar posição do veículo
            newTrip.getVehicle().setPosition(destPos);
            // Adicionar custo da viagem ao veículo
            newTrip.getVehicle().addToFinances(newTrip.getCost());
            // Set veículo "em uso"
            newTrip.getVehicle().setInUse(true);
            // Trip started at:
            newTrip.setTimeStarted(fastForwardValue);
            // Trip ends at:
            newTrip.setArrivingTime(fastForwardValue);

            /*
            System.out.println("Vehicle: " + newTrip.getVehicle().getIdentifier());
            System.out.println("Driver: " + newTrip.getDriver().getName());
            System.out.println("Origin: " + "(" + newTrip.getOrigin().getX() + "," + newTrip.getOrigin().getY() + ")");
            System.out.println("Destination: " + "(" + newTrip.getDestination().getX() + "," + newTrip.getDestination().getY() + ")");
            System.out.println("Estimated duration: " + newTrip.getEstimatedDuration());
            System.out.println("Real Duration: " + newTrip.getRealDuration());
			newTrip.setTimeStarted(fastForwardValue);
            System.out.println("Time Started: " + newTrip.getTimeStarted());
            newTrip.setArrivingTime(fastForwardValue);
            System.out.println("Arriving time: " + newTrip.getArrivingTime());
            System.out.println("Cost: " + newTrip.getCost());
            System.out.println("Pos atualizada client: " + a.getPosition());
            System.out.println("Pos atualizada driver: " + newTrip.getVehicle().getPosition());
            System.out.println("Profit atual veiculo: " + newTrip.getVehicle().getFinances());
            System.out.println("Gasto atual do cliente: " + a.getMoneySpent());
            System.out.println("Driver rating: " + newTrip.getDriver().getRating());
            */
        }

        // nao existe aquele veiculo ou nao há nenhum disponível
        else {

        	// Se aquele veiculo existe
        	if (vehicles.get(taxiID) != null) {

        		// Se o veículo tem waiting list
        		if (vehicles.get(taxiID).getHasWaitingList()) {
        			System.out.println("Estou a adicionar à lista de viagens em espera de UMER.");
        			waitingList.addLast(taxiID); // adicionar a string do taxi a lista

        			// let us print all the elements available in deque
					for (String nome : waitingList) {
						System.out.println("Taxi = " + nome);
					}

        			// o que quero mesmo fazer é adicionar o id do cliente para depois o taxi fazer a startTrip
        			Client a = (Client) Umer.loggedAs;
        			QueueInfo queuedTripInfo = new QueueInfo(a.getName(), destPosX, destPosY);
        			System.out.println("Estou a adicionar a informação da viagem à lista de viagens em espera do veículo.");
        			(vehicles.get(taxiID)).getWaitingQueue().addLast(queuedTripInfo);
        			// let us print all the elements available in deque of vehicle
        			System.out.println("Na lista do veículo:");
					for (QueueInfo tripInfo : (vehicles.get(taxiID)).getWaitingQueue()) {
						System.out.println("Client = " + tripInfo.getClientName());
					}



        		}
        	}
        	else {
        		System.out.println("O veículo " + taxiID + " não existe ou não está disponível, e/ou não tem waitingList.");
        	}
        }

        // Reportar updates para o loop da GUI
        userUpdates = true;

    }


    /**
     * Retorna a lista de viagens do utilizador logado
     * em forma de Array de Strings.
     */
    public static String[] getTripHistory () {

        String[] userHistory = new String[Umer.loggedAs.tripHistory.size()];

        for (int i = 0; i < Umer.loggedAs.tripHistory.size(); i++) {
            userHistory[i] = Umer.loggedAs.tripHistory.get(i).toString();
        }

        return userHistory;
    }


        public static String getTop10SpendingClients() {

        ArrayList<Client> clientsList = new ArrayList<Client>();

        for(Client c: clients.values()) {
            clientsList.add(c);
        }

        // ordena o array clients tendo em conta os top spending
        Collections.sort(clientsList, new Comparator<Client>() {
            public int compare(Client client1, Client client2) {
                return Double.compare(client2.getMoneySpent(),client1.getMoneySpent());
        }
        });

        // Cria um linkedhashmap com os nomes dos primeiros 10 clientes

        LinkedHashMap<String,Double> hashmap = new LinkedHashMap<String,Double>();
        for (int i = 0; i < clientsList.size() && i < 10; i++) {
            hashmap.put(clientsList.get(i).getName(),clientsList.get(i).getMoneySpent());
        }

        // Transforma hashmap em String
        String str = hashmap.toString();
        return str;

    }

    /**
     * Retorna a lista de condutores menos fiáveis,
     * sob a forma de String.
     */
    public static String getTop5LessReliableDrivers() {

        ArrayList<Driver> driversList = new ArrayList<Driver>();
        for(Driver d: drivers.values()) {
            driversList.add(d);
        }

        // ordena o array drivers tendo em conta os less reliable
        Collections.sort(driversList, new Comparator<Driver>() {
            public int compare(Driver driver1, Driver driver2) {
                return Double.compare(driver1.getRating(),driver2.getRating());
        }
        });

        // Cria um linkedhashmap com os nomes dos primeiros 5 drivers

        LinkedHashMap<String,Double> hashmap = new LinkedHashMap<String,Double>();
        for (int i = 0; i < driversList.size() && i < 5; i++) {
            hashmap.put(driversList.get(i).getName(),driversList.get(i).getRating());
        }

        // Transforma hashmap em String
        String str = hashmap.toString();
        return str;
    }

    /**
     * Avança o tempo no programa.
     * @param seconds	Quantos segundos devemos avançar.
     */
    public static void fastForward(double seconds) {

        fastForwardValue += seconds;

        // Reportar updates para o loop da GUI
        userUpdates = true;

        return;
    }


    /**
     * Recolhe o rating do driver em relação àquele serviço.
     * @param driverEmail 	O mail identificador do condutor.
     * @param rating 		A avaliação dada.
     */
    public static void rateDriver(String driverEmail, double rating) {

        Driver underRatingDriver = drivers.get(driverEmail);

        underRatingDriver.addRating(rating);

        // Já foi avaliado, por isso tirar do arraylist de avaliações
        Client a = (Client) Umer.loggedAs;
        a.underEvalDrivers.remove(driverEmail);

        // Reportar updates para o loop da GUI
        userUpdates = true;
    }


    /**
     * Disponibiliza ou não um condutor para serviço.
     */
    public static void toggleAvailability() {

        Driver a = (Driver) Umer.loggedAs;

        a.setAvailability(!a.isAvailable());

        // Reportar updates para o loop da GUI
        userUpdates = true;
    }


    /**
     * Devolve os lucros totais daquele veículo, sob a forma de String.
     * @param vehicleId 	Identificador do veículo a pesquisar.
     */
    public static String getVehicleFinances(String vehicleId) {

        Vehicle taxi = vehicles.get(vehicleId);

        return String.valueOf(taxi.getFinances());
    }


    /**
     * Retorna a lista de viagens a decorrer,
     * sob a forma de Array de Strings.
     */
    public static String[] getTripsUnderWay () {

    	// Eliminar as viagens que já terminaram
    	endsTripsUnderway();
    	// Começar viagens em fila de espera
    	startOnWaitingListTrips();

        String[] tripsGoingOn = new String[tripsUnderway.size() + tripsCompleted.size()];
        int j = 0;

        for (int i = 0; i < tripsUnderway.size(); i++, j++) {
            tripsGoingOn[j] = tripsUnderway.get(i).toString();
        }

        for (int i = 0; i < tripsCompleted.size(); i++, j++) {
            tripsGoingOn[j] = tripsCompleted.get(i).toString();
        }

        return tripsGoingOn;
    }


    /**
     * Retorna a lista de clients,
     * sob a forma de Array de Strings.
     */
    public static String[] getAllClients () {

        String[] clientsRegistered = new String[clients.size()];
        int index = 0;
        Iterator i = clients.entrySet().iterator();

        while (i.hasNext()) {

            Map.Entry entry = (Map.Entry) i.next();
            Client curClient = (Client) entry.getValue();

            clientsRegistered[index] = curClient.infoString();
            index++;
        }

        return clientsRegistered;
    }


    /**
     * Retorna a lista de condutores,
     * sob a forma de Array de Strings.
     */
    public static String[] getAllDrivers () {

        String[] driversRegistered = new String[drivers.size()];
        int index = 0;
        Iterator i = drivers.entrySet().iterator();

        while (i.hasNext()) {

            Map.Entry entry = (Map.Entry) i.next();
            Driver curDriver = (Driver) entry.getValue();

            driversRegistered[index] = curDriver.infoString();
            index++;
        }

        return driversRegistered;
    }


    /**
     * Retorna a lista de veículos,
     * sob a forma de Array de Strings.
     */
    public static String[] getAllVehicles () {

        String[] vehiclesRegistered = new String[vehicles.size()];
        int index = 0;
        Iterator i = vehicles.entrySet().iterator();

        while (i.hasNext()) {

            Map.Entry entry = (Map.Entry) i.next();
            Vehicle curVehicle = (Vehicle) entry.getValue();

            vehiclesRegistered[index] = curVehicle.toString();
            index++;
        }

        return vehiclesRegistered;
    }


    /**
     * Retorna o tempo agora,
     * sob a forma de String.
     */
    public static String getTimeNow() {

        Date now = new Date();
        now.setSeconds(now.getSeconds() + fastForwardValue);

        String instantString = "" + now.getTime();
        double instant = Double.parseDouble(instantString);

        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reportNow = formatter.format(instant);

        return reportNow;
    }


    /**
     * Retorna informação completa sobre o cliente logado,
     * sob a forma de String.
     */
    public static String getLoggedClientInfo() {

        Client logged;

        if (Umer.loggedAs instanceof Client) {
            logged = (Client) Umer.loggedAs;
        }
        else {
            return null;
        }

        if (logged == null) {
            return null;
        }

        return logged.toString();
    }

    /**
     * Retorna a lista de viagens à espera de avaliação,
     * sob a forma de Array de Strings.
     */
    public static String[] getUnderEvalTrips() {
        // System.out.println("Comecei getUnderEvalTrips");

        Client logged;

        if (Umer.loggedAs instanceof Client) {
            logged = (Client) Umer.loggedAs;
            // System.out.println("Estou no get e chamei underEvalTrips");
            System.out.println("Pedidos de avaliação: " + Arrays.toString(logged.underEvalTrips()));

            return logged.underEvalTrips();
        }

        // Não há nenhum para ser avaliado
        return new String[0];
    }


    /**
     * Retorna informação completa sobre o condutor logado,
     * sob a forma de String.
     */
    public static String getLoggedDriverInfo() {

        Driver logged;

        if (Umer.loggedAs instanceof Driver) {
            logged = (Driver) Umer.loggedAs;
        }
        else {
            return null;
        }

        if (logged == null) {
            return null;
        }

        return logged.toString();
    }

    /**
     * Retorna disponibilidade do condutor,
     * sob a forma de boolean.
     */
    public static boolean getLoggedDriverAvailability() {

        Driver logged = (Driver) Umer.loggedAs;

        return logged.isAvailable();
    }

    /**
     * Retorna true caso hajam updates para a GUI e false caso não.
     */
    public static boolean userHasUpdates() {
        return userUpdates;
    }

    public static void userUpdated() {
        userUpdates = false;
    }

    /**
     * Guarda os dados do programa.
     */
    public static void saveAppState() {
        IO io;
        io = new IO();
        io.WriteHashMap(vehicles,1);
        io.WriteHashMap(drivers,2);
        io.WriteHashMap(clients,3);
        io.WriteArrayList(tripsCompleted,1);
        io.WriteArrayList(tripsUnderway,2);
        io.WriteArrayDeque(waitingList);
    }

    /**
     * Verifica se viagens hora de chegada da viagem já passou e,
     * caso isso se verifique, termina a viagem, adicionando à lista de viagens terminadas,
     * tornando o veículo disponível novamente e retirando a viagem das tripsUnderway.
     */
    private static void endsTripsUnderway() {

        // Data atual
        Date atualTime = new Date();
        atualTime.setSeconds(atualTime.getSeconds() + fastForwardValue);

        // Percorrer a lista
        for (int i = 0; i < tripsUnderway.size(); i++) {
            // Ver se o atualTime é maior que o arriving time, se for acaba a viagem -> tira da tripsUnderWay, trip setIsCompleted(true) e vehicle setInUse(false);
            // System.out.println(tripsUnderway.get(i)); -> para ver se esta a reconhecer a Trip
            if (atualTime.after(tripsUnderway.get(i).getArrivingTime())) {
            	tripsUnderway.get(i).setIsCompleted(true);
            	tripsUnderway.get(i).getVehicle().setInUse(false);
            	// adicionar à lista de terminadas
            	tripsCompleted.add(tripsUnderway.get(i));
            	// remover da lista de correntes
            	tripsUnderway.remove(i);

            }
        }

    }

    /**
     * Verifica se os veículos com nome na lista de espera têm condições para iniciar viagem nova.
     * caso isso se verifique, procede à criação da viagem.
     */
    private static void startOnWaitingListTrips() {

    	Vehicle curVehicle = null;
    	QueueInfo queuedTripInfo = null;

    	// Percorrer todos os taxis com lista de espera com algo
		for (String taxiID : waitingList) {
			// O taxi a verificar é este
			System.out.println("Taxi a verificar: " + taxiID);
			curVehicle = vehicles.get(taxiID);

			// Verificar se tem condições para iniciar a viagem
			if (curVehicle.getDriver() != null && curVehicle.getDriver().isAvailable() && !curVehicle.getIsInUse()) {

				// O utilizador no primeiro lugar da fila é este. O poll já o remove da lista de espera do veículo.
				queuedTripInfo = curVehicle.getWaitingQueue().pollFirst();
				System.out.println("Para o utilizador na fila: " + queuedTripInfo.getClientName());

				startQueuedTrip(taxiID, queuedTripInfo.getClientName() ,(double) queuedTripInfo.getDestX(), (double) queuedTripInfo.getDestY());

				// Remover aquele taxiID da waitingList da UMER (a primeira ocorrência)
				waitingList.remove(taxiID);
			}

		}

    }

    /**
     * Retorna o veículo com o ID específico ao utilizador
     * logado atualmente, apenas se o utilizador for um cliente.
     */
    private static void startQueuedTrip(String taxiID, String clientID, double destPosX, double destPosY) {

        Trip newTrip = null;
        Point destPos = new Point(destPosX, destPosY);


        Client a = (Client) clients.get(clientID);
        newTrip = a.getTrip(vehicles, a.getPosition(), destPos, taxiID);
        // Viagem adicionada ao histórico do cliente
        a.tripHistory.add(newTrip);
        // Viagem adicionada às viagens under rating
        a.underEvalDrivers.add(newTrip.getDriver().getEmail());
        // Viagem começa a decorrer
        Umer.tripsUnderway.add(newTrip);
        // Atualizar posição do cliente
        a.setPosition(destPos);
        // Adicionar custo da viagem ao cliente
        a.addMoneySpent(newTrip.getCost());
        // Viagem adicionada ao histórico do condutor
        newTrip.getDriver().tripHistory.add(newTrip);
        // Atualizar posição do veículo
        newTrip.getVehicle().setPosition(destPos);
        // Adicionar custo da viagem ao veículo
        newTrip.getVehicle().addToFinances(newTrip.getCost());
        // Set veículo "em uso"
        newTrip.getVehicle().setInUse(true);
        // Trip started at:
        newTrip.setTimeStarted(fastForwardValue);
        // Trip ends at:
        newTrip.setArrivingTime(fastForwardValue);

        /*
        System.out.println("Vehicle: " + newTrip.getVehicle().getIdentifier());
        System.out.println("Driver: " + newTrip.getDriver().getName());
        System.out.println("Origin: " + "(" + newTrip.getOrigin().getX() + "," + newTrip.getOrigin().getY() + ")");
        System.out.println("Destination: " + "(" + newTrip.getDestination().getX() + "," + newTrip.getDestination().getY() + ")");
        System.out.println("Estimated duration: " + newTrip.getEstimatedDuration());
        System.out.println("Real Duration: " + newTrip.getRealDuration());
		newTrip.setTimeStarted(fastForwardValue);
        System.out.println("Time Started: " + newTrip.getTimeStarted());
        newTrip.setArrivingTime(fastForwardValue);
        System.out.println("Arriving time: " + newTrip.getArrivingTime());
        System.out.println("Cost: " + newTrip.getCost());
        System.out.println("Pos atualizada client: " + a.getPosition());
        System.out.println("Pos atualizada driver: " + newTrip.getVehicle().getPosition());
        System.out.println("Profit atual veiculo: " + newTrip.getVehicle().getFinances());
        System.out.println("Gasto atual do cliente: " + a.getMoneySpent());
        System.out.println("Driver rating: " + newTrip.getDriver().getRating());
        */


        // Reportar updates para o loop da GUI
        userUpdates = true;

    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    IO io;
                    io = new IO();
                    io.ReadHashMap(vehicles, 1);
                    io.ReadHashMap(drivers, 2);
                    io.ReadHashMap(clients, 3);
                    io.ReadArrayList(tripsCompleted, 1);
                    io.ReadArrayList(tripsUnderway, 2);
                    io.ReadArrayDeque(waitingList);

                    GUI frame = new GUI();
                    frame.setVisible(true);
                    frame.startGUILoop();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        /*
        int index = 0;

        System.out.println("Starting test");

        System.out.println("Creating user vitor with password gay at (0.5, 0.324)");

        Client vitor = registerClient("vitor@hotmail.com", "vitor", "gay", "casa", "yesterday", (double) 0.5, (double) 0.324);

        System.out.println("Creating user vitor with password gay at (0.5, 0.324)");

        Client joao = registerClient("joao@hotmail.com", "joao", "gay", "casa", "yesterday", (double) 0.5, (double) 0.324);

        System.out.println("Creating driver sergio with password gay at (2,3)");

        Driver sergio = registerDriver("sergio@hotmail.com", "sergio", "gay", "casa", "couple weeks ago");

        System.out.println("Creating driver marcos with password forte at (4,2)");

        Driver marcos = registerDriver("marcos@hotmail.com", "marcos",  "forte", "casa", "many a year ago");

        */
        /* AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
        System.out.println("Setting driver marcos to available");

        marcos.toggleAvailable();
        //-----------------------------------
        System.out.println("Setting driver sergio to available");

        sergio.toggleAvailable();
        //
        /* AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
        System.out.println("Logging in as vitor with password gay");

        Umer.login(vitor.getEmail(), vitor.getPassword());
        //-----------------------------------
        System.out.println("Finding nearest (ready to travel) vehicle to currently logged in user (vitor)");

        Vehicle nearestVehicle = Umer.getNearestReadyVehicle();

        System.out.println(nearestVehicle); // Dá null porque ainda não há veículos
        //
        System.out.println("Creating LIGHT taxi");

        Vehicle taxi1 = createVehicle((double) 1.0, (double) 2.3, "taxi primeiro", "LIGHT", false);
        System.out.println(taxi1);
        assignDriverToVehicle("sergio@hotmail.com", "taxi primeiro");
        System.out.println(taxi1);

        Vehicle taxi2 = createVehicle((double) 0.0, (double) 0.0, "taxi origem", "LIGHT", false);
        assignDriverToVehicle("marcos@hotmail.com", "taxi origem");


        /* AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
        System.out.println("Finding nearest (ready to travel) vehicle to currently logged in user (vitor)");

        nearestVehicle = Umer.getNearestReadyVehicle();

        System.out.println(nearestVehicle.getIdentifier()); // Dá "taxi origem"
        //-----------------------------------
        System.out.println("Finding vehicle with identifier to currently logged in user (vitor)");

        Vehicle identifierVehicle = (Vehicle) vehicles.get("taxi primeiro");

        System.out.println(identifierVehicle.getIdentifier()); // Dá "taxi primeiro"
        //
        System.out.println("Arranging trip to currently logged in user (vitor)");
        String taxiName = null;

        Trip viagem1 = null;
        taxiName = "";
        System.out.println("\nViagem que chama o mais próximo:");
        // Chama a startTrip que verifica se existe veiculo e a sua disponibilidade e tal
        viagem1 = Umer.startTrip(taxiName, 2.0, 2.0); // Chama o mais proximo


        Trip viagem2 = null;
        taxiName = "taxi primeiro";
        System.out.println("\nViagem que chama o: 'taxi primeiro'");
        // Chama a startTrip que verifica se existe veiculo e a sua disponibilidade e tal
        viagem2 = Umer.startTrip(taxiName, 3.2, 2.4); // Chama pelo ID
        System.out.println("Total faturado pelo veículo (igual a profit atual do veiculo): " + getVehicleFinances(taxiName));
        rateDriver("sergio@hotmail.com", 80.8);
        System.out.println("Rating do condutor após viagem: " + viagem2.getDriver().getRating());


        Trip viagem3 = null;
        taxiName = "taxi que nao existe";
        System.out.println("\nViagem que chama o: 'taxi que nao existe'");
        // Chama a startTrip que verifica se existe veiculo e a sua disponibilidade e tal
        viagem3 = Umer.startTrip(taxiName, 3.2, 2.4); // Chama pelo ID

        //----------------------------------- HISTORICO DE VIAGENS ------------------------------
        System.out.println("\nHISTORICO DE VIAGENS:");
        System.out.println(Arrays.toString(getTripHistory()));



        /*  --- TESTES DE ARRAYS ---
        // imprimir clientes
        System.out.println("\nFull list of clients:");
        for (index = 0; index < clients.size(); index++) {
            System.out.print(clients.get(index).getName() + ", ");
            System.out.print(clients.get(index).getEmail() + ", ");
            System.out.print(clients.get(index).getPassword() + ", ");
            System.out.print(clients.get(index).getAddress() + ", ");
            System.out.println(clients.get(index).getBirthdate() + ";");
        }

        // imprimir drivers
        System.out.println("\nFull list of drivers:");
        for (index = 0; index < drivers.size(); index++) {
            System.out.print(drivers.get(index).getName() + ", ");
            System.out.print(drivers.get(index).getEmail() + ", ");
            System.out.print(drivers.get(index).getPassword() + ", ");
            System.out.print(drivers.get(index).getAddress() + ", ");
            System.out.println(drivers.get(index).getBirthdate() + ";");
            System.out.println(drivers.get(index).getFulfillment() + ", ");
            System.out.println(drivers.get(index).getRating() + ", ");
            System.out.println(drivers.get(index).getKms() + ", ");
            System.out.println(drivers.get(index).isAvailable() + ";");
        }
        */


        /* AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
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

       System.out.println("TOP10 clients: " + getTop10SpendingClients());
       System.out.println("TOP5 drivers: " + getTop5LessReliableDrivers());

       */ //AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII

       //login("sergio@hotmail.com", "gay");

    }
}
