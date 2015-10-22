public class Car extends Vehicle 
{
	public Person driver;
	
	public Car(Person _driver)
	{
		maxSpeed = 4;
		driver = _driver;
	}
	
	public String getDestination()
	{
		return driver.getDestination();
	}
}
