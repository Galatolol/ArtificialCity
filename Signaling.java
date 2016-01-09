class Signaling extends Thread 
{
	private ContentPanel cp;
	private boolean on;
	
	public Signaling(ContentPanel _cp)
	{
		cp = _cp;
	}
		
	public void run()
	{
		while(true)
		{
			if (cp.timerValue % 50 == 0)
			{
				if (on)
				{
					System.out.println("wylaczam");
				}
				else
				{
					System.out.println("wlaczam");
				}
				on = !on;
		        synchronized(cp) 
		        {
			        try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			}
		}
	}
}




