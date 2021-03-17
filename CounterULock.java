public class CounterULock {
    public static int counter1 = 0;
    public static int counter2 = 0;

    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object(); 
    public static  void counterMeth1() {
        synchronized(lock1){
            counter1++;
        }
        
    }

  

    public static  void counterMeth2() {
        synchronized(lock2){
            counter2++;
        }
        
    }
    public static void noLocking() {

        Thread tr = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 100; i++)
                    counterMeth1();
            }
        });

        Thread tr2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 100; i++)
                    counterMeth2();
            }
        });

        tr.start();
        tr2.start();
        try {
            tr.join();
            tr2.join();
        } catch (InterruptedException exp) {
            exp.printStackTrace();

        }
        System.out.println(" The counter " + counter1);
        System.out.println(" The counter " + counter2);
    }

    public static void main(String[] args) {
        noLocking();
    }
}
