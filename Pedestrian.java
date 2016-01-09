import java.awt.Color;
import java.awt.Graphics;

public class Pedestrian
{
	protected boolean curvingRight;
	protected boolean curvingLeft;
	protected boolean movingForward;
	public Person controller;
	protected Lane[] street; 
	protected Cell currentCell;
	protected Cell nextCell;
	protected Cell tmpCell;
	protected String color;
	public boolean isWaiting;
	
	public Pedestrian(Person _controller)
	{
		controller = _controller;
	}
	
	public void curveRight()
	{
		curvingRight = true;
		curvingLeft = false;
		movingForward = false;
	}
	
	public void curveLeft()
	{
		curvingRight = false;
		curvingLeft = true;
		movingForward = false;
	}
	
	public void moveForward()
	{
		curvingRight = false;
		curvingLeft = false;
		movingForward = true;
	}
	
	public void setStreet(Lane[] _street)
	{
		street = _street;
	}
	
	public void setCurrentCell(Cell _cell)
	{
		currentCell = _cell;
	}
	
	public void setNextCell(Cell _cell)
	{
		nextCell = _cell;
	}
	
	public void setTmpCell(Cell _cell)
	{
		tmpCell = _cell;
	}
	
	public boolean isCurvingRight() { return curvingRight; }
	public boolean isCurvingLeft() { return curvingLeft; }
	public boolean isMovingForward() { return movingForward; }
	public Lane[] getStreet() { return street; }
	public Cell getCurrentCell() { return currentCell; }
	public Cell getNextCell() { return nextCell; }
	public Cell getTmpCell() { return tmpCell; }
	public String getColor() { return color; }
	
	public void paint(Graphics g, String color)
	{	
		g.setColor(Color.ORANGE);
		g.fillOval(this.getCurrentCell().getX()-5, 
				   this.getCurrentCell().getY()-5, 
				   10, 10);
	}
}