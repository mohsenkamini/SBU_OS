import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        FlexibleQueue<String> queue = new FlexibleQueue<String>();
        Semaphore rw_mutex = new Semaphore(1);
        Semaphore mutex = new Semaphore(1);
        Semaphore full = new Semaphore(0);
        Thread wthread = new Thread(new Writer(rw_mutex,queue,"something",full));
        Thread wthread1 = new Thread(new Writer(rw_mutex,queue,"something1",full));
        Thread wthread2 = new Thread(new Writer(rw_mutex,queue,"something2",full));
        Thread wthread3 = new Thread(new Writer(rw_mutex,queue,"something3",full));
        Thread rthread = new Thread(new Reader(rw_mutex,queue,mutex,full));
        Thread rthread1 = new Thread(new Reader(rw_mutex,queue,mutex,full));
        Thread rthread2 = new Thread(new Reader(rw_mutex,queue,mutex,full));
        Thread rthread3 = new Thread(new Reader(rw_mutex,queue,mutex,full));

        
        wthread.start();
        wthread1.start();
        wthread2.start();
        wthread3.start();
        rthread.start();
        rthread1.start();
        rthread2.start();
        rthread3.start();


        //for (int i = 0; i < 5; i++) {
        //    Thread rthread = new Thread(new Reader(rw_mutex, queue, mutex, full));
        //    try {
        //        rthread.start();
        //        rthread.join();
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //}
    }
}
