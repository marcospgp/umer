
public class Taxi
{
  private int velocity;
  private int price;
  // factor de fiabilidade, que determina a capacidade da viatura cumprir o 
  // tempo acordado com o cliente. Sempre que se realiza uma viagem é 
  // calculado (através da invocação de um random()) a capacidade de o 
  // veículo cumprir com o tempo acordado com o cliente. 
  // Este factor tem um efeito multiplicador sobre o tempo fornecido ao cliente.
  private int reliability;
  // LOCALIZACAO

  public int getVelocity () {
        return this.velocity;
  } 
  
  public int getPrice (){
        return this.price;
  }
 
  public int getReliability () {
        return this.reliability;
  }
    
  
  //construtor dos objetos
  public Taxi(int newVelocity, int newPrice, int newReliability) {
		this.velocity = newVelocity;
        this.price = newPrice;
		this.reliability = newReliability;
	}
  }