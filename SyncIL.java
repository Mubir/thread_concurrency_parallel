public class SyncIL {
    
    public static int counter1 = 0;
    public static int counter2 = 0;

    
    public static synchronized void counterMeth1() {
        counter1++;
    }

  

    public static synchronized void counterMeth2() {
        counter2++;
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
        System.out.println(" The counter_1 " + counter1);
        System.out.println(" The counter_2 " + counter2);
    }

    public static void main(String[] args) {
        noLocking();
    }
}
