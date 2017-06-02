import java.util.ArrayList;
import java.io.Serializable;

/**
 * Classe que representa um qualquer utilizador.
 * Este utilizador pode ser um Condutor ou um Cliente,
 * classes depois definidas e que detalham o utilizador conforme necessário.
 * O User tem email, name, password, address e birthdate.
 */
public abstract class User implements Serializable{

    protected String email;
    protected String name;
    protected String password;
    protected String address;
    protected String birthdate;

    // Histórico de viagens
    protected ArrayList<Trip> tripHistory = new ArrayList<Trip>();

    public String getEmail () {
        return this.email;
    }

    public String getName () {
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

}
