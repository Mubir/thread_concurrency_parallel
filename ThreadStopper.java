class MyThread implements Runnable
{
    public boolean isRunning=true;
    @Override
    public void run()
    {
        while(isRunning)
        {
            System.out.println("running....");
            try{
                Thread.sleep(350);
            }catch(Exception exception)
            {
                System.out.println(exception);
            }
            
        }
    }

    public void setRunning(boolean bool)
    {
        isRunning = bool;
    }
}

public class ThreadStopper {


    public static void main(String[] args) {
        MyThread tr = new MyThread();
    Thread thread = new Thread(tr);

    thread.start();

    try{
        Thread.sleep(5000);
    }catch(Exception exception)
    {
        System.out.println(exception);
    }

    tr.setRunning(false);
    System.out.println("Stopped...");
    }
    
}
