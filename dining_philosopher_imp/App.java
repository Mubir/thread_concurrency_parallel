import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App 
{
    public static void main(String[] args) throws InterruptedException{
        ExecutorService executorService = null;
        Philosopher[] philosophers=null;
        Chopstick[] chopsticks=null;

        try {
            philosophers = new Philosopher[Constants.COUNT_PHILOSOPHER];
            chopsticks = new Chopstick[Constants.COUNT_CHOPSTICK];

            for(int i=0;i<Constants.COUNT_CHOPSTICK;++i)
            {
                chopsticks[i] = new Chopstick(i);
            }

            executorService = Executors.newFixedThreadPool(Constants.COUNT_PHILOSOPHER);

            for(int i=0;i<Constants.COUNT_PHILOSOPHER;++i)
            {
                philosophers[i]= new Philosopher(i,chopsticks[i],chopsticks[(i+1)%Constants.COUNT_CHOPSTICK]);
                executorService.execute(philosophers[i]);
            }

            Thread.sleep(Constants.RUNNING_TIME);

            for(Philosopher x:philosophers)
            {
                x.setFull(true);
            }
        } finally
        {
            executorService.shutdown();
            while(!executorService.isTerminated())
                {Thread.sleep(1000);}
            

            for(Philosopher x:philosophers)
            {
                    System.out.println(x+" eat # "+x.getEatingCounter()+" times");
            } 


        }
    }
} 