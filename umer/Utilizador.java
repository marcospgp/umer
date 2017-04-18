import java.util.Scanner;
public class Utilizador
{
  private String email;
  private String nome;
  private String password;
  private String morada;
  private String datanascimento;

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
        return this.datanascimento;
  }
  /*  
  public Utilizador() {
    email = new String();
    nome = new String();
    password = new String();
    morada = new String();
    datanascimento = new String();
  }
  */
  
  public Utilizador(String email, String nome, String password, String morada, String datanascimento) {
		this.email = email;
        this.nome = nome;
		this.password = password;
		this.morada = morada;
		this.datanascimento = datanascimento;
	}

  public void Login() {
    Scanner teclado = new Scanner (System.in);
    String inputuser = teclado.nextLine();
    String inputpass = teclado.nextLine();

    if (inputuser.equals(email) && inputpass.equals(password)) {
        System.out.print("Login feito com sucesso!");
    } else {
        System.out.print("Erro de autenticação!");
    }
  }
} 
