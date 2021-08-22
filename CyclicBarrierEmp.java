import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
class Worker implements Runnable{
    private int id;
    private CyclicBarrier cyclicBarrier;

    Worker(int i,CyclicBarrier cyclicBarrier)
    {
        this.id = i;
        this.countDownLatch = cyclicBarrier;
    }

    public void run()
    {
        work();
      
    }

    private void work()
    {
        System.out.println(" Start running "+id+" ");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            //TODO: handle exception
        }
        System.out.println(" Finished running "+id+" ");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            //TODO: handle exception
        }

    }
}
public class CyclicBarrierEmp {
    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(5);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,new Runnable(){
            @Override
            public void run()
            {
                System.out.println(" all finished ");
            }
        });

        for(int i=0;i<5;i++)
        {
            exe.execute(new Worker(i, cyclicBarrier));
        }

       

        System.out.println(" All task finshed ");
    }
}
