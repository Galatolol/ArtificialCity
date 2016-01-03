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
			if (cp.timerVal % 50 == 0)
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
			}
			
		}
	}
}




