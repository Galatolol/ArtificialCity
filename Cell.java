
public class Cell {
	private int x;
	private int y;
	private int howManyCellsToCrossroad;
	private Vehicle veh;
	private boolean occupied;
	private boolean forbidden; //gdy nie dla aut osobowych
	private int nr;
	
	public Cell(double _x, double _y, int _howManyCellsToCrossroad, int _nr) {
		this.x = (int) _x;
		this.y = (int) _y;
		howManyCellsToCrossroad = _howManyCellsToCrossroad;
		nr = _nr;
		occupied = false;
		forbidden = false;
	}
	
	public void setOccupied(boolean _occupied)
	{
		occupied = _occupied;
	}
	
	public void setForbidden(boolean _forbidden)
	{
		forbidden = _forbidden;
	}
	
	public void setX(int _x) {
		this.x = _x;
	}
	
	public void setY(int _y) {
		this.y = _y;
	}
	
	public String toString() { return " (" + this.x + "," + this.y + ")  "; }
	public int getX() { return x; }
	public int getY() { return y; }
	public int getHowManyCellsToCrossroad() { return howManyCellsToCrossroad; }
	public boolean isOccupied() { return occupied; }
	public boolean isForbidden() { return forbidden; }
	public int getNr(){ return nr; }
}
