import java.util.concurrent.Semaphore;

public class Reader implements Runnable{
    private Semaphore rmutex,mutex,full;
    private FlexibleQueue queue;
    private int read_count;
    private Boolean blocking;

    public Reader(Semaphore rmutex, FlexibleQueue queue, Semaphore full, Boolean blocking) {
        this.rmutex = rmutex;
        // this.mutex = mutex;
        this.full = full;
        this.queue = queue;
        this.read_count = 0;
        this.blocking = blocking;
    }
    public void run() {
        try {
            if (blocking) {
                if (full.availablePermits() == 0)
                    System.out.println("Reader: waiting on full");
                full.acquire(); // comment for non-blocking
                rmutex.acquire();
                read_count++;
                System.out.println("Reader: removed '"+this.queue.remove()+"' from "+queue.toString());
                read_count--;
                System.out.println("Reader: queue size is "+this.queue.size());
            } else {
                //if (full.availablePermits() == 0)
                //    System.out.println("Reader: waiting on full");
                //full.acquire(); // comment for non-blocking
                rmutex.acquire();
                read_count++;
                System.out.println("Reader: removed '"+this.queue.remove()+"'");
                read_count--;
                System.out.println("Reader: queue size is "+this.queue.size());
            }
        } catch (InterruptedException e) {
            // Handle the interruption
        } finally {
            rmutex.release(); 
        }
    }
}

