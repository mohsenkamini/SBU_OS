import java.util.LinkedList;
import java.util.Queue;

public class FlexibleQueue<T> {
    private final Queue<T> queue;
    private final int capacity;
    private final boolean isBounded;
    private int size;

    public FlexibleQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be a positive integer");
        }
        this.capacity = capacity;
        this.isBounded = true;
        this.queue = new LinkedList<>();
        this.size = 0;
    }

    public FlexibleQueue() {
        this.capacity = 0;
        this.isBounded = false;
        this.queue = new LinkedList<>();
        this.size = 0;
    }

    public void add(T element) {
        if (isBounded && size >= capacity) {
            throw new IllegalStateException("Queue capacity exceeded");
        }
        queue.offer(element);
        size++;
    }

    public T remove() {
        T element = queue.poll();
        if (element != null) {
            size--;
        }
        return element;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}