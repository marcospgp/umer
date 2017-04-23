import java.util.Scanner;

public abstract class User
{
  protected String email;
  protected String name;
  protected String password;
  protected String address;
  protected String birthdate;

  public String getEmail () {
        return this.email;
  } 
  
  public String getName (){
        return this.name;
  }
 
  public String getPassword () {
        return this.password;
  }
    
  public String getAddress () {
        return this.address;
  }
   
  public String getBirthdate () {
        return this.birthdate;
  }

  public void login() {
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
