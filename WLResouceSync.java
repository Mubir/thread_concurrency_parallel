class WLResouceSync {
    public static int counter = 0;

    public static synchronized void counterMeth() {
        counter++;
        
    }

    public static void noLocking() {

        Thread tr = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 1000; i++)
                    counterMeth();

                    System.out.println("Thread 1 finished");
            }
        });

        Thread tr2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 1000; i++)
                   { counterMeth(); 
                    try{Thread.sleep(1); } catch(Exception e){ System.out.println(e);}
                    
                   }
                    System.out.println("Thread 2 finished");
            }
            
        });

        tr.start();
        
        tr2.start();
        try {
            tr.join();
            System.out.println(" ************ ");
             tr2.join();
            
        } catch (InterruptedException exp) {
            exp.printStackTrace();

        }
        System.out.println(" The counter " + counter);
    }

    public static void main(String[] args) {
        noLocking();
    }
}