import java.util.concurrent.Semaphore;
public class BoundedBufferTest {

    public static void main(String[] args) {
        FlexibleQueue queue = new FlexibleQueue(3);
        Semaphore wmutex = new Semaphore(1);
        Semaphore rmutex = new Semaphore(1);
        Semaphore full = new Semaphore(0);
        Thread wthread = new Thread(new Writer(wmutex,queue,"something",full));
        Thread wthread1 = new Thread(new Writer(wmutex,queue,"someotherthing1",full));
        Thread wthread2 = new Thread(new Writer(wmutex,queue,"something2",full));
        Thread wthread3 = new Thread(new Writer(wmutex,queue,"someotherthing3",full));
        Thread wthread4 = new Thread(new Writer(wmutex,queue,"thing4",full));
        Thread wthread5 = new Thread(new Writer(wmutex,queue,"othsomething5",full));
        Thread wthread6 = new Thread(new Writer(wmutex,queue,"something6",full));
        Thread wthread7 = new Thread(new Writer(wmutex,queue,"something7",full));

        Thread rthread = new Thread(new Reader(rmutex,queue,full , true));
        Thread rthread1 = new Thread(new Reader(rmutex,queue,full, true));
        Thread rthread2 = new Thread(new Reader(rmutex,queue,full, true));
        // Thread rthread3 = new Thread(new Reader(rmutex,queue,full, true));


        rthread.start();        
        wthread.start();
        wthread1.start();
        wthread2.start();
        wthread3.start();
        rthread1.start();
        rthread2.start();
        //rthread3.start();
        wthread4.start();
        wthread5.start();
        wthread6.start();
        wthread7.start();


        //for (int i = 0; i < 5; i++) {
        //    Thread rthread = new Thread(new Reader(wmutex, queue, mutex, full));
        //    try {
        //        rthread.start();
        //        rthread.join();
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //}
    }
}
