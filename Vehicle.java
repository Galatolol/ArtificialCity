import java.awt.Color;
import java.awt.Graphics;

public abstract class Vehicle 
{
	protected boolean curvingRight;
	protected boolean curvingLeft;
	protected boolean movingForward;
	protected int currentSpeed;
	protected int maxSpeed;
	protected CellList[] cellListTab; 
	protected int currentListNr;
	protected Cell currentCell;
	protected Cell nextCell;
	protected Cell tmpCell;
	
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
		curvingRight = true;
		curvingLeft = false;
		movingForward = true;
	}
	
	public void setSpeed(int speed)
	{
		currentSpeed = speed;
		if (speed > maxSpeed)
		{
			currentSpeed = maxSpeed;
		}
		else if (speed < 0)
		{
			currentSpeed = 0;
		}
	}
	
	public void modifySpeed(int speed)
	{
		setSpeed(currentSpeed + speed);
	}
	
	public void setCellListTab(CellList[] _cellListTab)
	{
		cellListTab = _cellListTab;
	}
	
	public void setCurrentListNr(int _nr)
	{
		currentListNr = _nr;
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
	
	public int getCurrentSpeed() { return currentSpeed; }
	public boolean isCurvingRight() { return curvingRight; }
	public boolean isCurvingLeft() { return curvingLeft; }
	public boolean isMovingForward() { return movingForward; }
	public CellList[] getCellListTab() { return cellListTab; }
	public int getCurrentListNr() { return currentListNr; }
	public Cell getCurrentCell() { return currentCell; }
	public Cell getNextCell() { return nextCell; }
	public Cell getTmpCell() { return tmpCell; }
	
	public void paintVehicle(Graphics g)
	{	
		g.setColor(Color.BLUE);
		g.fillOval(this.getCurrentCell().getX()-5, 
				   this.getCurrentCell().getY()-5, 
				   10, 10);
	}
}
