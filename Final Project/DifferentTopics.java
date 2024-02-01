import java.util.concurrent.Semaphore;

public class DifferentTopics {

    public static void main(String[] args) {
        FlexibleQueue queue = new FlexibleQueue();
        FlexibleQueue topic1 = new FlexibleQueue();
        FlexibleQueue topic2 = new FlexibleQueue();
        //FlexibleQueue queue = new FlexibleQueue(3);
        Semaphore wmutex = new Semaphore(1);
        Semaphore rmutex = new Semaphore(1);
        Semaphore full = new Semaphore(0);
        Thread wthread = new Thread(new Writer(wmutex,queue,"something",full));
        Thread wthread1 = new Thread(new Writer(wmutex,topic1,"someotherthing1",full));
        Thread wthread2 = new Thread(new Writer(wmutex,topic1,"something2",full));
        Thread wthread3 = new Thread(new Writer(wmutex,topic2,"someotherthing3",full));
        Thread wthread4 = new Thread(new Writer(wmutex,topic2,"thing4",full));
        Thread wthread5 = new Thread(new Writer(wmutex,queue,"othsomething5",full));
        Thread wthread6 = new Thread(new Writer(wmutex,queue,"something6",full));
        Thread wthread7 = new Thread(new Writer(wmutex,queue,"something7",full));
        
        Thread rthread = new Thread(new Reader(rmutex,queue,full , true));
        Thread rthread1 = new Thread(new Reader(rmutex,topic1,full, true));
        Thread rthread2 = new Thread(new Reader(rmutex,topic2,full, true));
        Thread rthread3 = new Thread(new Reader(rmutex,queue,full, true));

        rthread.start();        
        wthread.start();
        wthread1.start();
        wthread2.start();
        wthread3.start();
        
        try {
            wthread1.join();
            wthread2.join();
            wthread3.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        rthread1.start();
        rthread2.start();
        rthread3.start();
        wthread4.start();
        wthread5.start();
        wthread6.start();
        wthread7.start();

    }

}
