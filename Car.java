import java.awt.Color;
import java.awt.Graphics;

public class Car extends Vehicle 
{
	public Person driver;
	
	public Car(Person _driver)
	{
		maxSpeed = 4;
		driver = _driver;
		color = "red";
	}
	
	public String getDestination()
	{
		return driver.getDestination();
	}
	
	public void setDestinationVertex(V v) {
		driver.setDestinationVertex(v);
	}
	
	public void setCurrentVertex(V v) {
		driver.setCurrentVertex(v);
	}
	
	public void setPrevVertex(V v) {
		driver.setPrevVertex(v);
	}
	public void paintVehicle(Graphics g)
	{	
		g.setColor(Color.getColor(color));
		g.fillOval(this.getCurrentCell().getX()-5, 
				   this.getCurrentCell().getY()-5, 
				   10, 10);
	}
}
