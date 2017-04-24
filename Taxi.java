public class Taxi {

    private VehicleType type;
    private Driver currentDriver;
    private double posX;
    private double posY;

    public int getAvgSpeed () {
        return this.type.getAvgSpeed();
    }

    public int getPricePerKm (){
        return this.type.getPricePerKm();
    }

    public int getReliability () {
        return this.type.getReliability();
    }

    /**
     * Constructor for objects of class Taxi
     */
    public Taxi(double newPosX, double newPosY) {
  		this.posX = newPosX;
        this.posY = newPosY;
  	}
}
