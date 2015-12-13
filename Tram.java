public class Tram extends PublicTransport
{	
	public Tram(int _direction, String[] _directionTab1, String[] _directionTab2)
	{
		super(_direction, _directionTab1, _directionTab2);
		maxSpeed = 2;
		color = "cyan";
	}
}
