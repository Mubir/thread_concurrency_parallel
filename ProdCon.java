
import java.util.List;
import java.util.ArrayList;
class Helper
{
    public  List<Integer> arr = new ArrayList<>();
    public static int counter = 0;
    public static int value = 0;
    public static final Object lock = new Object();

    public void Producer() throws InterruptedException {

        synchronized (lock) {
            while (true) {
                if(arr.size()==5)
                {
                    System.out.println("Stop adding and removing ..............");
                    lock.wait();
                }else
                {
                    arr.add(value);
                    System.out.println(" value "+value);
                    value++;
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void Consumer() throws InterruptedException {

        synchronized (lock) {
            while (true) {
                if(arr.size()==0)
                {
                    System.out.println("Stop consumer ..............");
                    value =0;
                    lock.wait();
                }else
                {
                    arr.remove(arr.size()-1);
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}
public class ProdCon {
    
    public static void main(String[] args) {
        Helper helper = new Helper();
        Thread tr = new Thread(new Runnable(){
            @Override
            public void run()
            {
                try{
                    helper.Producer();
                }catch(InterruptedException exp)
                {
                    System.out.println(exp);
                }
                
            }
        });


        Thread trT = new Thread(new Runnable(){
            @Override
            public void run()
            {
                try{
                    helper.Consumer();
                }catch(InterruptedException exp)
                {
                    System.out.println(exp);
                }
                
            }
        });

        tr.start();
        trT.start();
    }
}
