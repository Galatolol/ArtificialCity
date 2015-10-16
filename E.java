import java.util.LinkedList;

// Edge class

public class E {
	private V begin;
	private V end;
	private int length;
	private int speedLimit;
	private int lanesNum;
	public CellList[] cellTab;
	private int cellsNum;
	
	public static final int CELL_SIZE = 5;
	
	E (V _begin, V _end, int _length, int _speedLimit, int _lanesNum) {
		this.begin = _begin;
		this.end = _end;
		this.length = (int) Math.sqrt((end.getX()-begin.getX()) * (end.getX()-begin.getX()) +
				            (end.getY()-begin.getY()) * (end.getY()-begin.getY()));
		
		System.out.println(begin.toString() + " - " + end.toString() + " : " + length);
		this.speedLimit = _speedLimit;
		this.lanesNum = _lanesNum;
		
		cellsNum =  length / CELL_SIZE;
		cellTab = new CellList[lanesNum];
		
		for(int i = 0; i < lanesNum; i++) {
			cellTab[i] = new CellList(cellsNum, this.begin, this.end);
		}
		
	}
	
	public int getLength() {
		return this.length;
	}
	
	public String toString() {
		return this.begin.toString() + " - " + this.end.toString();
	}
	
	public int getBeginX() {
		return begin.getX();
	}
	
	public int getBeginY() {
		return begin.getY();
	}
	
	public int getEndX() {
		return end.getX();
	}
	
	public int getEndY() {
		return end.getY();
	}
	
	public int getLanesNum() {
		return lanesNum;
	}
	
	public String cellsToString() {
		String output = "";
		for(int i = 0; i < lanesNum; i++) {
			for(int j = 0; j < cellsNum; j++) {
				output += cellTab[i].cellList[j].toString();
			}
		output += "\n------------------------------\n";
		}
		
		return output;
	}
	
}
