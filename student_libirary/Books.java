import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Books {
    private int id;
    private Lock lock;

    public Books(int id)
    {
        this.id = id;
        lock = new ReentrantLock();
    }
    public void read(Students students) throws InterruptedException
    {
        lock.tryLock(10,TimeUnit.MILLISECONDS);
        System.out.println("reading # "+students+" book # "+this);
        Thread.sleep(1000);
        lock.unlock();
        System.out.println("Freed  book # "+this);
    }

    @Override
    public String toString()
    {
        return " "+id;
    }

}
