import java.util.concurrent.Semaphore;

public class Reader implements Runnable{
    private Semaphore rw_mutex,mutex,full;
    private FlexibleQueue queue;
    private int read_count;

    public Reader(Semaphore rw_mutex, FlexibleQueue queue, Semaphore mutex, Semaphore full) {
        this.rw_mutex = rw_mutex;
        this.mutex = mutex;
        this.full = full;
        this.queue = queue;
        this.read_count = 0;
    }
    public void run() {
        try {
            if (full.availablePermits() == 0)
                System.out.println("Reader: waiting on full");
            full.acquire();
            mutex.acquire();
            read_count++;
            if (read_count == 1)
                rw_mutex.acquire();
            System.out.println("Reader: removed '"+this.queue.remove()+"'");

            read_count--;
            if (read_count == 0)
                rw_mutex.release();
            System.out.println("Reader: queue size is "+this.queue.size());
        } catch (InterruptedException e) {
            // Handle the interruption
        } finally {
            mutex.release(); 
        }
    }
}

