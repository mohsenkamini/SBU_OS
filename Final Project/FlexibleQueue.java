import java.util.LinkedList;
import java.util.Queue;

public class FlexibleQueue {
    private final Queue<String> queue;
    private final int capacity;
    private final boolean isBounded;
    private int size;
    private int StringLengthLimit;

    public FlexibleQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be a positive integer");
        }
        this.capacity = capacity;
        this.isBounded = true;
        this.queue = new LinkedList<>();
        this.size = 0;
    }

    public FlexibleQueue(int capacity,int StringLengthLimit) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be a positive integer");
        }
        this.capacity = capacity;
        this.isBounded = true;
        this.queue = new LinkedList<>();
        this.size = 0;
        this.StringLengthLimit = StringLengthLimit;
    }


    public FlexibleQueue() {
        this.capacity = 0;
        this.isBounded = false;
        this.queue = new LinkedList<>();
        this.size = 0;
    }

    public void add(String element) {
        if (isBounded && size >= capacity) {
            throw new IllegalStateException("Queue capacity exceeded");
        }
        if (element.length() > this.StringLengthLimit)
            throw new IllegalArgumentException("String length exceedes the limit");
        queue.offer(element);
        size++;
    }

    public String remove() {
        String element = queue.poll();
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

    public int sumOfLength() {
        int result = 0;
        for (String element : queue) {
            result += element.length();
        }
        return result;
    }
}