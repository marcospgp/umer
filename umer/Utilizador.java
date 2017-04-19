import java.util.Scanner;

public class Utilizador
{
  private String email;
  private String nome;
  private String password;
  private String morada;
  private String data;

  public String getEmail () {
        return this.email;
  } 
  
  public String getNome (){
        return this.nome;
  }
 
  public String getPassword () {
        return this.password;
  }
    
  public String getMorada () {
        return this.morada;
  }
   
  public String getDataNascimento () {
        return this.data;
  }
  
  // construtor dos objetos
  public Utilizador(String newEmail, String newNome, String newPassword, String newMorada, String newDataNascimento) {
		this.email = newEmail;
        this.nome = newNome;
		this.password = newPassword;
		this.morada = newMorada;
		this.data = newDataNascimento;
	}
  

  public void Login() {
    Scanner teclado = new Scanner (System.in);
    System.out.println("Login:");
    System.out.println("Username/Email: ");
    String inputuser = teclado.nextLine();
    System.out.println("Password: ");
    String inputpass = teclado.nextLine();

    if (inputuser.equals(email) && inputpass.equals(password)) {
        System.out.print("Login feito com sucesso!");
    } else {
        System.out.print("Erro de autenticação!");
    }
  }
} 
