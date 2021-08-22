import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker implements Runnable{
    private int id;
    private CountDownLatch countDownLatch;

    Worker(int i,CountDownLatch countDownLatch)
    {
        this.id = i;
        this.countDownLatch = countDownLatch;
    }

    public void run()
    {
        work();
        // decrementing counter
        countDownLatch.countDown();
    }

    private void work()
    {
        System.out.println(" Thread running "+id+" ");
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
public class LatchEmp {
    public static void main(String[] args) {
        ExecutorService exe = Executors.newSingleThreadExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for(int i=0;i<5;i++)
        {
            exe.submit(new Worker(i,countDownLatch));
        }

        try {
            // main thread is paused utill all task in latch is finish
            countDownLatch.await();
        } catch (Exception e) {
            //TODO: handle exception
        }

        System.out.println(" All task finshed ");
    }
}
