import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Work implements Runnable
{
    private int id;
    public Work(int id)
    {
        this.id = id;
    }
    @Override
    public void run()
    {
        System.out.println("Task id :: "+id+" thread :: "+Thread.currentThread());
        long duration = (long)Math.random()*5;

        try{
            //TimeUnit.SECONDS.sleep(duration);
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

public class FixedThreadPoolExecutor {
    public static void main(String[] args) {
        ExecutorService exc = Executors.newFixedThreadPool(2);

        for(int i=0;i<10 ;i++)
        {
            exc.execute(new Work(i+1));
        }
    }
}
