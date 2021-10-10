public class OddEvenUsingWaitNotify
{
    public static void main(String[] args) {
        SharedPrinter share = new SharedPrinter();
        Thread odd = new Thread(new Odd(share,10));
        Thread even = new Thread(new Even(share,10));

        odd.start();
        even.start();
    }
}

class SharedPrinter
{
    boolean evenFlag= true;

    void printEven(int num)
    {
        synchronized(this){
            
            while(evenFlag){
            try {
                wait();
                
            } catch (Exception e) {
                //TODO: handle exception
            }
        }

        System.out.println("even: "+num);
        evenFlag=true;
        notify();
    }
    }

    void printOdd(int num)
    {
        synchronized(this){
            
            while(!evenFlag){
            try {
                wait();
                
            } catch (Exception e) {
                //TODO: handle exception
            }
        }

        System.out.println("Odd:  "+num);
        evenFlag=false;
        notify();
    }
    }
}

class Even implements Runnable
{
    SharedPrinter sp ;
    int range;
    Even(SharedPrinter sp ,int range)
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

class Odd implements Runnable
{
    SharedPrinter sp ;
    int range;
    Odd(SharedPrinter sp ,int range)
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