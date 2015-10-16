
public class Cell {
	private int x;
	private int y;
	private boolean state;
	
	Cell(double _x, double _y) {
		this.x = (int) _x;
		this.y = (int) _y;
	}

	public String toString() {
		return " ( " + this.x + "," + this.y + ")  "; 
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
