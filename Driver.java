import java.io.Serializable;

public class Driver extends User implements Serializable {

    // Grau de cumprimento de horário estabelecido com o cliente, dado por um factor entre 0 e 100
    private double fulfillment;

    // Classificação do motorista, dado numa escala de 0 a 100,
    // calculada com base na classificação dada pelo cliente no final de cada viagem
    private double rating;

    // Total de kms feitos na empresa
    private double kms;

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
    
    public double getFulfillment() {
        return this.fulfillment;
    }
    public double getRating() {
        return this.rating;
    }
    public double getKms() {
        return this.kms;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailability(boolean availability) {
        this.available = availability;
    }

    public void toggleAvailable() {
        this.available = !this.available;
    }
    @Override
    public String toString() {
    return "Email: " + this.email + ", "
         + "Name: " + this.name + ", "
         + "Password: " + this.password + ", "
         + "Address: " + this.address + ", "
         + "Birthdate: " + this.birthdate + ", "
         + "Fulfillment: " + this.fulfillment + ", "
         + "Rating: " + this.rating + ", "
         + "Kms: " + this.kms + ", "
         + "Available: " + this.available;
    }
}
