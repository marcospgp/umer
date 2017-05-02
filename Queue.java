import java.util.Deque;

public class Queue {

    public static void add(Vehicle v,Deque<Vehicle> l) {
        l.add(v);
    }
    public static void remove(Deque<Vehicle> l) {
        l.removeFirst();
    }
}
