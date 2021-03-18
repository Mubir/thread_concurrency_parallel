import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable
{
    private int id;
    public Task(int id)
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

public class SingleThreaDExecutor {

    public static void main(String[] args) {
        // single thread execute task sequentially
        ExecutorService exc = Executors.newSingleThreadExecutor();

        for(int i=0;i<5;i++)
        {
            exc.execute(new Task(i));
        }
    }
}
