
/**
 * Classe genérica que representa um ponto.
 * Pode ser instanciada com valores de qualquer tipo que
 * herde to tipo Number. No entanto, para todos os cálculos efetuados
 * pelos métodos desta classe, os valores são convertidos para double.
 */
public class Point <T extends Number> {

    private T x;
    private T y;

    /*
     * Main constructor for objects of class Point
     */
    public Point(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point<T> p) {
        this(p.getX(), p.getY());
    }

    public Point() {
        throw new java.lang.Error("Cannot instantiate new point without providing initial coordinates.");
    }

    /**
     * Returns the X coordinate of the point
     *
     * @return     the x coordinate
     */
    public T getX() {
        return this.x;
    }

    /**
     * Returns the Y coordinate of the point
     *
     * @return     the y coordinate
     */
    public T getY() {
        return this.y;
    }

    /**
     * Sets the X coordinate of the point
     *
     * @return     the x coordinate
     */
    public void setX(T x) {
        this.x = x;
    }

    /**
     * Sets the Y coordinate of the point
     *
     * @return     the y coordinate
     */
    public void setY(T y) {
        this.y = y;
    }

    /**
     * Returns the distance from this point to another
     * The double value of both points is used for the calculation.
     *
     * @param  b   point to calculate the distance to
     * @return     the distance between this point and point b
     */
    public double distanceTo(Point b) {

        double dX = Math.abs( b.getX().doubleValue() - this.x.doubleValue() );
        double dY = Math.abs( b.getY().doubleValue() - this.y.doubleValue() );

        return Math.sqrt( Math.pow(dX, 2) + Math.pow(dY, 2) );
    }
}
