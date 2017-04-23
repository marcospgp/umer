
public class Client extends User {
    
    private double posX;
    private double posY;
    
    // Construtor da classe Client
    public Client(String newEmail, String newName, String newPassword, String newAddress, String newBirthdate, double posX, double posY) {
        this.email = newEmail;
        this.name = newName;
        this.password = newPassword;
        this.address = newAddress;
        this.birthdate = newBirthdate;
        this.posX = posX;
        this.posY = posY;
    }
    public String historicodeviagens;
    // O cliente está sempre numa determinada localização (expressa em x e y, isto é, num espaço 2D) e 
    // escolhe um táxi específico ou então solicita o táxi mais perto que esteja disponível. 
    // O cliente tem também uma relação de todas as viagens que fez, com toda a informação relativa à viagem.
    // TEMOS DE VER COMO DEFINIMOS A localizacao / ponto

}
