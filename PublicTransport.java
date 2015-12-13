public class PublicTransport extends Vehicle 
{
	String direction;
	int n;
	int directionn;
	String[] directionTab1;
	String[] directionTab2;
	
	public PublicTransport(int _directionn, String[] _directionTab1, String[] _directionTab2)
	{
		n = 0;
		directionn = _directionn;
		directionTab1 = _directionTab1;
		directionTab2 = _directionTab2;
	}
	
	public void changeDirection()
	{
		String next;
		if (directionn == 1)
		{
			next = directionTab1[n];
			direction = directionTab1[directionTab1.length - 1];
		}
		else
		{
			next = directionTab2[n];
			direction = directionTab2[directionTab2.length - 1];
		}
		
		if (next.equals("forward"))
		{
			this.moveForward();
		}
		else if (next.equals("right"))
		{
			this.curveRight();
		}
		else
		{
			this.curveLeft();
		}
		System.out.println("pubTrans: " + next);
		n++;
	}
	
	public String getDirection()
	{
		return direction;
	}
}
