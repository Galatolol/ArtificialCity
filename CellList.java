
public class CellList {
	public Cell[] cellList;
	
	CellList(int n, V begin, V end) {
		cellList = new Cell[n];
		for(int i = 0; i < n; i++) {
			cellList[i] = createCell(i, n, begin, end);
		}
	}
	
	private Cell createCell(int i, int n, V begin, V end) {
		double x;
		double y;
		
		//if(end.getX() > begin.getX()) {
			x = Math.abs(end.getX() - begin.getX());
		//} else {
		//	x = begin.getX() - end.getX() ;
		//}
		//
		//if(end.getY() > begin.getY()) {
			y = Math.abs(end.getY() - begin.getY());
		//} else {
		//	y = begin.getY() - end.getY() ;
		//}
		
		x = (1f / n) * x;
		y = (1f / n) * y;		
		
		if(end.getX() >= begin.getX() && (end.getY() >= begin.getY())) 
			return new Cell((begin.getX()+ x * i), (begin.getY() + y * i));
		
		else if(end.getX() >= begin.getX() && (end.getY() < begin.getY()))
			return new Cell((begin.getX()+ x * i), (begin.getY() - y * i));
	 	
		else if(end.getX() < begin.getX() && (end.getY() < begin.getY()))
			return new Cell((begin.getX()- x * i), (begin.getY() - y * i));
		
		else 
			return new Cell((begin.getX()- x * i), (begin.getY() + y * i));
	}

}
