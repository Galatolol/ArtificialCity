
public class Person 
{
	private int age;
	private String residence;
	private String destination;
	private V destinationVertex;
	private V currentVertex;
	private V prevVertex;
	private String from; // po to, by wracali do siebie na weekend, moze miec wartosc ""
	
	private String goingOut;
	private String goingBack;
	
	private String firstName;
	private String lastName;
	
	public boolean driving;
	
	Person(int _age, String _residence, String _destination, V _prevVertex, V _currentVertex, V _destinationVertex, String _from, String _goingOut, String _goingBack, 
			String _firstName, String _lastName, boolean _driving)
	{
		age = _age;
		residence = _residence;
		destination = _destination;
		prevVertex = _prevVertex;
		currentVertex = _currentVertex;
		destinationVertex = _destinationVertex;
		from = _from;
		goingOut = _goingOut;
		goingBack = _goingBack;
		firstName = _firstName;
		lastName = _lastName;
		driving = _driving;
	}
	
	//------------------------------------
	
	public int getAge() { return age; }
	public String getGoingOut() { return goingOut; }
	public String getGoingBack() { return goingBack; }
	public String getResidence() { return residence; }
	public String getDestination() { return destination; }
	public String getFrom() { return from; }
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public boolean getIsDriving() { return driving; }
	
	public V getDestinationVertex() { return destinationVertex; }
	public V getCurrentVertex() { return currentVertex; }
	public V getPrevVertex() { return prevVertex; }
	
	public void setCurrentVertex(V v) {
		this.currentVertex = v;
	}
	
	public void setPrevVertex(V v) {
		this.prevVertex = v;
	}
	
	public void setDestinationVertex(V v) {
		this.destinationVertex = v;
	}
}
