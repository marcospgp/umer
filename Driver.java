public class Driver extends User {

    // grau de cumprimento de horário estabelecido com o cliente, dado por um factor entre 0 e 100
    private double fulfillment;

    // classificação do motorista, dado numa escala de 0 a 100, calculada com base na classificação dada pelo cliente no final de cada viagem
    private double rating;

    // total de kms feitos na empresa
    private int kms;

    // Se o motorista se encontra disponível
    private boolean available;

    /**
     * Constructor for objects of class Driver
     */
    public Driver(String newEmail, String newName, String newPassword, String newAddress, String newBirthdate, double newFulfillment, double newRating, int newKms, boolean newAvailable) {
        this.email = newEmail;
        this.name = newName;
        this.password = newPassword;
        this.address = newAddress;
        this.birthdate = newBirthdate;
        this.fulfillment = newFulfillment;
        this.rating = newRating;
        this.kms = newKms;
        this.available = newAvailable;
    }
}
