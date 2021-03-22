import java.util.stream.IntStream;

class PrimeserialVSparallel
{
    public static void main(String[] args) {
        System.out.println("Serial execution: ");
        long current  = System.currentTimeMillis();
        long count = IntStream.rangeClosed(0, 10000000).filter(PrimeserialVSparallel::isPrime).count();
        System.out.println(count);
        System.out.println("times # "+(System.currentTimeMillis()-current)+" ms");
        System.out.println("Parallel execution: ");
        current  = System.currentTimeMillis();
        count = IntStream.rangeClosed(0, 10000000).parallel().filter(PrimeserialVSparallel::isPrime).count();
        System.out.println(count);
        System.out.println("times # "+(System.currentTimeMillis()-current)+" ms");
    }


   public static boolean isPrime(int x)
    {
        if( x==1 )
            return true;

        if(x<=0 || x%2==0)
            return false;
        int div = (int)Math.sqrt(x);
        for(int i=3;i<=div;i++)
        {
            if(x%i==0)
                return false;
            
        }
        return true;
    }
}