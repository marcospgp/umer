public class Driver extends User{
    
    public Driver(String newEmail, String newName, String newPassword, String newAddress, String newBirthdate) {
		this.email = newEmail;
        this.name = newName;
		this.password = newPassword;
		this.address = newAddress;
		this.birthdate = newBirthdate;
	}
    // grau de cumprimento de horário estabelecido com o cliente, dado por um factor entre 0 e 100
    public int cumprimento;
    // classificação do motorista, dado numa escala de 0 a 100, calculada com base na classificação dada pelo cliente no final da viagem;
    public int rating;
    // historico de viagens array?
    
    // kms feitos na empresa
    public int kms;
    // informacao se está disponivel ou não, a trabalhar ou não
    public boolean available;
} 