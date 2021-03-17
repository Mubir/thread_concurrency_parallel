import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum SinToneDownload
{
    INSTANCE;

    private Semaphore semaphore = new Semaphore(3,true);

    public void Downloader(){
        try {
            semaphore.acquire();
            Load();
        } catch (Exception e) {
            //TODO: handle exception
        }finally{
            semaphore.release();
        }
    }
    public void Load()
    {
        try {
            System.out.println(" Started downloading ........");
            Thread.sleep(3000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}

public class SemaphoreExm {
    public static void main(String[] args) {
        ExecutorService eService = Executors.newCachedThreadPool();

        for(int i=0;i<12;i++)
        {
            eService.execute(new Runnable(){
                public void run()
                {
                    SinToneDownload.INSTANCE.Downloader();
                }
            });
        }
    }
}
