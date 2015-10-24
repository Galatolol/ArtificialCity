
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
	}
	
	public void setOccupied(boolean _occupied){
		occupied = _occupied;
	}

	public String toString() { return " (" + this.x + "," + this.y + ")  "; }
	public int getX() { return x; }
	public int getY() { return y; }
	public int getHowManyCellsToCrossroad() { return howManyCellsToCrossroad; }
	public boolean isOccupied() { return occupied; }
	public boolean isForbidden() { return forbidden; }
	public int getNr(){ return nr; }
}
