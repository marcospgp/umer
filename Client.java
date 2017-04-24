public class Client extends User {

    private double posX;
    private double posY;

    /**
     * Constructor for objects of class Client
     */
    public Client(String newEmail, String newName, String newPassword, String newAddress, String newBirthdate, double newPosX, double newPosY) {
        this.email = newEmail;
        this.name = newName;
        this.password = newPassword;
        this.address = newAddress;
        this.birthdate = newBirthdate;
        this.posX = newPosX;
        this.posY = newPosY;
    }

}
