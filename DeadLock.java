import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class DeadLock {
    public Lock lock1 = new ReentrantLock(true);
    public Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();

        new Thread(deadLock::worker1,"buddy1").start();
        new Thread(deadLock::worker2,"buddy2").start();
    }

    public void worker1()
    {
        lock1.lock();
        System.out.println("worker 1 gets lock-1");
        try {
            Thread.sleep(300);// giving time to thread 2 two acquire lock.
        } catch (Exception e) {
            //TODO: handle exception
        }

        lock2.lock();
        System.out.println("worker 1 gets lock-2");
        lock1.unlock();
        lock2.unlock();
    }

    public void worker2()
    {
        lock2.lock();
        System.out.println("worker 2 gets lock-2");
        try {
            Thread.sleep(300);// giving time to thread 2 two acquire lock.
        } catch (Exception e) {
            //TODO: handle exception
        }

        lock1.lock();
        System.out.println("worker 2 gets lock-1");
        lock1.unlock();
        lock2.unlock();
    }
}
