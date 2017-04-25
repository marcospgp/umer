public class Driver extends User {

    // Grau de cumprimento de horário estabelecido com o cliente, dado por um factor entre 0 e 100
    private double fulfillment;

    // Classificação do motorista, dado numa escala de 0 a 100,
    // calculada com base na classificação dada pelo cliente no final de cada viagem
    private double rating;

    // Total de kms feitos na empresa
    private int kms;

    // Se o motorista se encontra disponível
    private boolean available;

    /**
     * Constructor for objects of class Driver
     */
    public Driver(String newEmail, String newName, String newPassword, String newAddress, String newBirthdate) {
        this.email = newEmail;
        this.name = newName;
        this.password = newPassword;
        this.address = newAddress;
        this.birthdate = newBirthdate;
        this.fulfillment = (double) 100;
        this.rating = (double) 100;
        this.kms = (double) 0;
        this.available = false;
    }
}
