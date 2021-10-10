import java.util.Random;

public class Students implements Runnable{

    private int id;
    private Books[] book;

    public Students(int id,Books[] books)
    {
        this.id = id;
        this.book = books;
    }

    @Override
    public void run() {
        Random rand = new Random();

        while(true)
        {
            int bookId = rand.nextInt(Constant.NUMBER_OF_BOOKS);
            try {
                book[bookId].read(this);
            } catch (InterruptedException e) {
                //TODO: handle exception
            }

        }
        
    }
    @Override
    public String toString()
    {
        return "Std "+id;
    }
    
}
