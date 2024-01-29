import java.util.concurrent.Semaphore;
import java.util.Random;

public class Writer implements Runnable {
    private Semaphore wmutex,full;
    private FlexibleQueue queue;
    private String input;
    public Writer(Semaphore wmutex,FlexibleQueue queue,String input,Semaphore full) {
        this.wmutex = wmutex;
        this.full = full;
        this.queue = queue;
        this.input = input;
    }
    public void run() {
        try {
            Random random = new Random();
            int waitTime = random.nextInt(5); 
            wmutex.acquire(); 
            Thread.sleep(waitTime*1000);
            this.queue.add(this.input);
            System.out.println("Writer: added '"+this.input+"'");
            System.out.println("Writer: queue size is "+this.queue.size());
        } catch (InterruptedException e) {
            // Handle the interruption
        } finally {
            full.release();
            wmutex.release(); 
        }
    }
}

