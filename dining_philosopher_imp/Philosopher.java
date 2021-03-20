import java.util.Random;

public class Philosopher implements Runnable{
    private int id ;
    private volatile boolean full;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private Random random;
    private int eatingCounter;

    public Philosopher(int id,Chopstick lChopstick,Chopstick rChopstick)
    {
        this.id = id;
        this.leftChopstick = lChopstick;
        this.rightChopstick = rChopstick;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            while(!full)
            {
                think();
                if(leftChopstick.pickUp(this, State.LEFT))
                {
                    if(rightChopstick.pickUp(this, State.RIGHT))
                    {
                        eat();
                        rightChopstick.putdown(this, State.RIGHT);
                    }
                    leftChopstick.putdown(this,State.LEFT);
                }
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }
    private void think() throws InterruptedException
    {
        System.out.println(this+"Thinking ....");
        Thread.sleep(random.nextInt(1000));
    }
    private void eat() throws InterruptedException{
        System.out.println(this+" is eating ");
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }
    public void setFull(boolean full)
    {
        this.full = full;
    }
    public boolean getFull()
    {
        return this.full;
    }
    public int getEatingCounter() {
        return eatingCounter;
    }
    @Override
    public String toString()
    {
        return "Philosopher # "+id;
    }
}
