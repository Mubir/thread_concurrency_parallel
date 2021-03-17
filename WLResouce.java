class WLResouce
{
	public static int counter=0;
	public static void noLocking()
	{
		

		Thread tr = new Thread(new Runnable(){
		
				@Override
				public void run()
				{
					for(int i =0 ;i<100;i++)
						counter++;
				}
			});

		Thread tr2 = new Thread(new Runnable(){
		
			@Override
			public void run()
			{
				for(int i =0 ;i<100;i++)
					counter++;
			}
		});

		tr.start();
		tr2.start();
		try{
			tr.join();
		tr2.join();
	}catch(InterruptedException exp)
	{
		exp.printStackTrace();
		
	}
	System.out.println(" The counter "+counter);
	}

	public static void main(String[] args) {
		noLocking();
			}
}