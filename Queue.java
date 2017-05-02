import java.util.Deque;

public class Queue {
    
    // Método que adiciona objeto veículo à tail da lista
    public static void add(Vehicle v,Deque<Vehicle> l) {
        l.add(v);
    }
    
    // Método que remive objeto veículo da cabeça da lista
    public static void remove(Deque<Vehicle> l) {
        l.removeFirst();
    }
    
    // Método que retorna o número de elementos na fila de espera
    public static int size(Deque<Vehicle> l) {
        int sizearray = l.size();
        return sizearray;
    }
}
