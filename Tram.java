public class Tram extends Vehicle 
{
	int n;
	int direction;
	String[] directionTab1;
	String[] directionTab2;
	
	
	public Tram(int _direction, String[] _directionTab1, String[] _directionTab2)
	{
		n = 0;
		direction = _direction;
		directionTab1 = _directionTab1;
		directionTab2 = _directionTab2;
		maxSpeed = 2;
		color = "cyan";
	}
	
	public void changeDirection()
	{
		String next;
		if (direction == 1)
		{
			next = directionTab1[n];
		}
		else
		{
			next = directionTab2[n];
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
		System.out.println(next);
		n++;
	}
}
