/**
 * O Umer é um programa que permite efetuar a gerência de viagens entre clientes e motoristas.
 * Esta é a classe principal que gere o programa.
 *
 * @author Marcos Pereira, Sérgio Oliveira, Vítor Castro
 * @version 0.1
 * @since 2017-04-23
 */
public final class Umer {
    // instance variables - replace the example below with your own
    private int x;

    // Prevenir instanciação desta classe (googlar "java final class private constructor")
    private Umer() {
        throw new AssertionError();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }

    public static void main(String[] args) {
    }
}
