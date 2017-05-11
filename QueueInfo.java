public class QueueInfo {

	private String client;
	private double posDestX;
	private double posDestY;

	/**
     * Constructor for objects of class Driver
     */
    public QueueInfo(String clientName, double posX, double posY) {
        this.client = clientName;
        this.posDestX = posX;
        this.posDestY = posY;
    }

    public String getClientName() {
    	return this.client;
    }

    public double getDestX() {
    	return this.posDestX;
    }

    public double getDestY() {
    	return this.posDestY;
    }

    public void setClientName(String nome) {
    	this.client = nome;
    }

    public void setDestX(double posX) {
    	this.posDestX = posX;
    }

    public void setDestY(double posY) {
    	this.posDestY = posY;
    }

}