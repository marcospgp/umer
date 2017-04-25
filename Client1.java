import java.io.Serializable;

public class Client1 implements Serializable{

    private String um;
	private String dois;

	public Client1() {
	}

	public Client1(String newum, String newdois) {
		this.um = newum;
		this.dois = newdois;
	}

	public String getum() {
		return um;
	}

	public String getdois() {
		return dois;
	}

}
