
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
            //e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
public class ThreadShutDown {
    public static void main(String[] args) {
        ExecutorService exc = Executors.newFixedThreadPool(2);

        for(int i=0;i<10;i++)
        {
            exc.execute(new Work(i+1));
        }
        // we prevent executor to execute any further task.
        // but we also need to terminate the running tasks
        exc.shutdown();

        try {
            // awaits blocks unitll all task is completed or time out complted
            if(!exc.awaitTermination(1000, TimeUnit.MILLISECONDS))
            {
                // if 1000 mil sec has passed but task is running then it will terminalte at once
                // 
                //  but if a task is in sleeping mood and we call this then we need 
                // to handle the interrupt in the calling try under run-> try
                // catch write Thread.currentThread().interrupt()
                exc.shutdownNow();
            }
        } catch (InterruptedException e) {
            // if we want to finish all task  then comment the if in try;
            // and run this.
          exc.shutdownNow();
        }
        
    }
}
