
public class Lane {
	public Cell[] cellList;
	public int howManyToLeft; //ile pasow do lewa
	public int howManyToRight;
	public int length;
	private int speedLimit;
	public Lane[] left;
	public Lane[] right;
	public Lane[] forward;
	public V begin;
	public V end;
	
	public boolean clDir; // zwrot listy komórek na potrzeby ruchu pieszych, gdzie krawedzie są dwukierunkowe
	
	public Lane(int n, V _begin, V _end, int laneNr, int _howManyToRight, int _howManyToLeft) {
		cellList = new Cell[n];
		length = n;
		howManyToRight = _howManyToRight;
		howManyToLeft = _howManyToLeft;
		for (int i = 0; i < n; i++) {
			cellList[i] = createCell(i, n, _begin, _end, laneNr, n - i);
		}
		speedLimit = 3;
		begin = _begin;
		end = _end;
	}
	
	public Lane(Lane l)
	{
		cellList = new Cell[l.cellList.length];
		int n = l.cellList.length;
		howManyToRight = l.howManyToRight;
		howManyToLeft = l.howManyToLeft;
		for (int i = 0; i < n; i++) {
			cellList[i] = createCell(i, n, l.begin, l.end, 0, n - i);
		}
		speedLimit = 3;
		begin = l.begin;
		end = l.end;
	}
	
	private Cell createCell(int i, int n, V begin, V end, int laneNr, int howManyCellsToCrossroad) {
		double x;
		double y;
		
		switch (laneNr) {
			case 0: x = Math.abs(end.getX() - begin.getX());
					y = Math.abs(end.getY() - begin.getY());
					break;
			
			case 1: x = Math.abs(end.getX() - begin.getX());
					y = Math.abs(end.getY() - begin.getY());
					break;	
					
			case 2: x = Math.abs(end.getX() - begin.getX());
					y = Math.abs(end.getY() - begin.getY());
					break;	
			
			default: x = Math.abs(end.getX() - begin.getX());
					 y = Math.abs(end.getY() - begin.getY());
					 break;		
		}
		
		x = (1f / n) * x;
		y = (1f / n) * y;		
		
		if(end.getX() >= begin.getX() && (end.getY() >= begin.getY())) 
			return new Cell((begin.getX()+ x * i), (begin.getY() + y * i), howManyCellsToCrossroad, i);
		
		else if(end.getX() >= begin.getX() && (end.getY() < begin.getY()))
			return new Cell((begin.getX()+ x * i), (begin.getY() - y * i), howManyCellsToCrossroad, i);
	 	
		else if(end.getX() < begin.getX() && (end.getY() < begin.getY()))
			return new Cell((begin.getX()- x * i), (begin.getY() - y * i), howManyCellsToCrossroad, i);
		
		else 
			return new Cell((begin.getX()- x * i), (begin.getY() + y * i), howManyCellsToCrossroad, i);
	}
	
	public String toString() {
		String output = "";

		for(int j = 0; j < length; j++) {
			output += cellList[j].toString();
		}
		output += "\n------------------------------\n";
		
		
		return output;
	}
	
	public void setSpeedLimit(int _speedLimit) { speedLimit = _speedLimit; }
	public int getSpeedLimit() { return speedLimit; }
	public int getHowManyToRight() { return howManyToRight; }
	public int getHowManyToLeft() { return howManyToLeft; }

}
