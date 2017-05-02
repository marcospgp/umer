import java.util.Deque;

public class Queue {

    public static void add(Object o,Deque<Object> l) {
        l.add(o);
    }
    public static void remove(Deque<Object> l) {
        l.removeFirst();
    }
}
