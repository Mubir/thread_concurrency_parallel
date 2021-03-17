import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntranceLockBasic {
    public static int counter =0;
    public static Lock lock = new ReentrantLock();

    public static void incremnt()
    {
        lock.lock();
        try{
            for(int i=0;i<10000;i++)
                counter++;
        }finally{
            lock.unlock();
        }
        
        
    }

    public static void main(String[] args) {
        Thread tr1= new Thread(new Runnable(){
            @Override
            public void run()
            {
                incremnt();
            }
        });
        Thread tr2= new Thread(new Runnable(){
            @Override
            public void run()
            {
                incremnt();
            }
        });

        tr1.start();
        tr2.start();


        try{
            tr1.join();
            tr2.join();
        }catch(InterruptedException e)
        {
            System.out.println(e);
        }

        System.out.println(counter);
    }
}
