public abstract class User {

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
}
