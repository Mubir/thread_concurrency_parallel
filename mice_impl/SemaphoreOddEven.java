import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreOddEven
{
    public static void main(String[] args) {
        SharedPrinterSe share = new SharedPrinterSe();
        ExecutorService exe = Executors.newFixedThreadPool(2);
        exe.execute(new OddS(share, 10));
        exe.execute(new EvenS(share, 10));
        exe.shutdown();
    }
}

class SharedPrinterSe
{
    Semaphore odd = new Semaphore(0);
    Semaphore even = new Semaphore(1);
    void printEven(int num)
    {
            try {
                even.acquire();
                
            } catch (Exception e) {
                
            }
        

        System.out.println("even: "+num);
        odd.release();
    }
  

    void printOdd(int num)
    {
        try {
            odd.acquire();
            
        } catch (Exception e) {
            
        }
    

    System.out.println("odd: "+num);
    even.release();
    }
}


class EvenS implements Runnable
{
    SharedPrinterSe sp ;
    int range;
    EvenS(SharedPrinterSe sp ,int range)
    {
        this.sp = sp;
        this. range =range;
    }

    @Override
   public void run()
   {
    for(int i=0;i<range;i+=2)
    {
        sp.printEven(i);
    }
   }
}

class OddS implements Runnable
{
    SharedPrinterSe sp ;
    int range;
    OddS(SharedPrinterSe sp ,int range)
    {
        this.sp = sp;
        this. range =range;
    }

    @Override
   public void run()
   {
    for(int i=1;i<range;i+=2)
    {
        sp.printOdd(i);
    }
   }
}
