public class PublicTransport extends Vehicle 
{
	String direction;
	int n;
	int destination;
	String[] directionTab1;
	String[] directionTab2;
	
	public PublicTransport(int _destination, String[] _directionTab1, String[] _directionTab2)
	{
		n = 0;
		destination = _destination;
		directionTab1 = _directionTab1;
		directionTab2 = _directionTab2;
	}
	
	public void changeDestination1()
	{
		destination = 1;
		int v1 = Integer.parseInt(directionTab1[directionTab1.length - 2]);
		int v2 = Integer.parseInt(directionTab1[directionTab1.length - 1]);
		changeDestination3(v1, v2);
	}
	
	public void changeDestination2()
	{
		destination = 2;
		int v1 = Integer.parseInt(directionTab2[directionTab2.length - 2]);
		int v2 = Integer.parseInt(directionTab2[directionTab2.length - 1]);
		changeDestination3(v1, v2);
	}
	
	public void changeDestination3(int v1, int v2)
	{
		this.setStreet(Util.getStreet(v1, v2));
		this.setLaneNr(0);
		if (this instanceof Bus)
		{
			this.setLaneNr(street.length - 1); 
		}
		this.setCurrentCell(street[laneNr].cellList[0]);
		this.setSpeed(1);
		this.changeDirection();
	}
	
	public void changeDirection()
	{
		String next;
		if (destination == 1)
		{
			next = directionTab1[n];
			direction = directionTab1[directionTab1.length - 3];
		}
		else
		{
			next = directionTab2[n];
			direction = directionTab2[directionTab2.length - 3];
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
		n++; 
	}
	
	public String getDirection()
	{
		return direction;
	}
	
	
}
