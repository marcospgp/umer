
public class Taxi
{
  private int velmedia;
  private int precobase;
  // factor de fiabilidade, que determina a capacidade da viatura cumprir o 
  // tempo acordado com o cliente. Sempre que se realiza uma viagem é 
  // calculado (através da invocação de um random()) a capacidade de o 
  // veículo cumprir com o tempo acordado com o cliente. 
  // Este factor tem um efeito multiplicador sobre o tempo fornecido ao cliente.
  private int fiabilidade;
  // LOCALIZACAO

  public int getVelocidadeMedia () {
        return this.velmedia;
  } 
  
  public int getPrecobase (){
        return this.precobase;
  }
 
  public int getFiabilidade () {
        return this.fiabilidade;
  }
    
  
  //construtor dos objetos
  public Taxi(int newVelmedia, int newPrecobase, int newFiabilidade) {
		this.velmedia = newVelmedia;
        this.precobase = newPrecobase;
		this.fiabilidade = newFiabilidade;
	}
  }