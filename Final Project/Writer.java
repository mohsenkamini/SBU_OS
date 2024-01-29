import java.util.concurrent.Semaphore;
import java.util.Random;

public class Writer implements Runnable {
    private Semaphore rw_mutex,full;
    private FlexibleQueue<String> queue;
    private String input;
    public Writer(Semaphore rw_mutex,FlexibleQueue<String> queue,String input,Semaphore full) {
        this.rw_mutex = rw_mutex;
        this.full = full;
        this.queue = queue;
        this.input = input;
    }
    public void run() {
        try {
            Random random = new Random();
            int waitTime = random.nextInt(5); 
            rw_mutex.acquire(); 
            Thread.sleep(waitTime*1000);
            this.queue.add(this.input);
            System.out.println("Writer: added '"+this.input+"'");
            System.out.println("Writer: queue size is "+this.queue.size());
        } catch (InterruptedException e) {
            // Handle the interruption
        } finally {
            full.release();
            rw_mutex.release(); 
        }
    }
}

