
// Edge class

public class E {
	private V begin;
	private V end;
	private int length;
	private int speedLimit;
	private int weight;
	private int cellListsNum;
	public Lane[] street;
	private int cellsNum;
	private boolean oneWay;
	
	public static final int CELL_SIZE = 5;
	
	E (V _begin, V _end, int _length, int _speedLimit, int _cellListsNum, boolean _oneWay) {
		this.begin = _begin;
		this.end = _end;
		this.weight = _cellListsNum;
		this.length = (int) Math.sqrt((end.getX()-begin.getX()) * (end.getX()-begin.getX()) +
				            (end.getY()-begin.getY()) * (end.getY()-begin.getY()));
		
		//System.out.println(begin.toString() + " - " + end.toString() + " : " + length);
		this.speedLimit = _speedLimit;
		this.cellListsNum = _cellListsNum;
		this.oneWay = _oneWay;
		
		cellsNum =  length / CELL_SIZE;
		street = new Lane[cellListsNum];
		
		
		// jeden pas ruchu
		if(cellListsNum == 1) {
			street[0] = new Lane(cellsNum, this.begin, this.end, 0, 0, 0);
			if(!oneWay) {
				
				if(this.end.getY() < this.getBeginY()) {
					for(int j = 0; j < street[0].cellList.length; j++) {
						street[0].cellList[j].setX(street[0].cellList[j].getX() + 3);
						//street[0].cellList[j].setY(street[0].cellList[j].getY() + 3);
					}
				} else {
					for(int j = 0; j < street[0].cellList.length; j++) {
						street[0].cellList[j].setX(street[0].cellList[j].getX() - 3);
						//street[0].cellList[j].setY(street[0].cellList[j].getY() - 3);
					}
				}															
			}
		// dwa pasy ruchu
		} else if(cellListsNum == 2) {
			street[0] = new Lane(cellsNum, this.begin, this.end, 0, 1, 0);
			street[1] = new Lane(cellsNum, this.begin, this.end, 1, 0, 1);
			
			if(this.end.getY() < this.begin.getY()) {
				for(int j = 0; j < street[0].cellList.length; j++) {
					street[0].cellList[j].setX(street[0].cellList[j].getX() + 5);
					//street[0].cellList[j].setY(street[0].cellList[j].getY() + 5);
					street[1].cellList[j].setX(street[1].cellList[j].getX() + 10);
					//street[1].cellList[j].setY(street[1].cellList[j].getY() + 10);
				}
			} else {
				for(int j = 0; j < street[1].cellList.length; j++) {
					street[0].cellList[j].setX(street[0].cellList[j].getX() - 5);
					//street[0].cellList[j].setY(street[0].cellList[j].getY() - 5);
					street[1].cellList[j].setX(street[1].cellList[j].getX() - 10);
					//street[1].cellList[j].setY(street[1].cellList[j].getY() - 10);
				}
			}															

		
		// trzy pasy ruchu
		} else if(cellListsNum == 3) {
			street[0] = new Lane(cellsNum, this.begin, this.end, 0, 2, 0);
			street[1] = new Lane(cellsNum, this.begin, this.end, 1, 1, 1);
			street[2] = new Lane(cellsNum, this.begin, this.end, 2, 0, 2);
			if(this.end.getY() < this.begin.getY()) {
				for(int j = 0; j < street[1].cellList.length; j++) {
					street[0].cellList[j].setX(street[0].cellList[j].getX() - 4);
					street[0].cellList[j].setY(street[0].cellList[j].getY() - 4);
					street[2].cellList[j].setX(street[2].cellList[j].getX() + 4);
					street[2].cellList[j].setY(street[2].cellList[j].getY() + 4);
				}
			} else {
				for(int j = 0; j < street[1].cellList.length; j++) {
					street[0].cellList[j].setX(street[0].cellList[j].getX() + 4);
					street[0].cellList[j].setY(street[0].cellList[j].getY() + 4);
					street[2].cellList[j].setX(street[2].cellList[j].getX() - 4);
					street[2].cellList[j].setY(street[2].cellList[j].getY() - 4);
				}
			}
		}
	}
	
	E (V _begin, V _end, int _length, int _speedLimit, int _cellListsNum, boolean _oneWay, int cellSize) {
		this.begin = _begin;
		this.end = _end;
		this.weight = _length;
		this.length = (int) Math.sqrt((end.getX()-begin.getX()) * (end.getX()-begin.getX()) +
				            (end.getY()-begin.getY()) * (end.getY()-begin.getY()));
		
		//System.out.println(begin.toString() + " - " + end.toString() + " : " + length);
		this.speedLimit = _speedLimit;
		this.cellListsNum = _cellListsNum;
		this.oneWay = _oneWay;
		
		cellsNum =  length / cellSize;
		street = new Lane[cellListsNum];
		
		
		// jeden pas ruchu
		if(cellListsNum == 1) {
			street[0] = new Lane(cellsNum, this.begin, this.end, 0, 0, 0);
			if(!oneWay) {
				
				if(this.end.getY() < this.getBeginY()) {
					for(int j = 0; j < street[0].cellList.length; j++) {
						street[0].cellList[j].setX(street[0].cellList[j].getX() + 3);
						//street[0].cellList[j].setY(street[0].cellList[j].getY() + 3);
					}
				} else {
					for(int j = 0; j < street[0].cellList.length; j++) {
						street[0].cellList[j].setX(street[0].cellList[j].getX() - 3);
						//street[0].cellList[j].setY(street[0].cellList[j].getY() - 3);
					}
				}															
			}
		// dwa pasy ruchu
		} else if(cellListsNum == 2) {
			street[0] = new Lane(cellsNum, this.begin, this.end, 0, 1, 0);
			street[1] = new Lane(cellsNum, this.begin, this.end, 1, 0, 1);
			
			if(this.end.getY() < this.begin.getY()) {
				for(int j = 0; j < street[0].cellList.length; j++) {
					street[0].cellList[j].setX(street[0].cellList[j].getX() + 5);
					//street[0].cellList[j].setY(street[0].cellList[j].getY() + 5);
					street[1].cellList[j].setX(street[1].cellList[j].getX() + 10);
					//street[1].cellList[j].setY(street[1].cellList[j].getY() + 10);
				}
			} else {
				for(int j = 0; j < street[1].cellList.length; j++) {
					street[0].cellList[j].setX(street[0].cellList[j].getX() - 5);
					//street[0].cellList[j].setY(street[0].cellList[j].getY() - 5);
					street[1].cellList[j].setX(street[1].cellList[j].getX() - 10);
					//street[1].cellList[j].setY(street[1].cellList[j].getY() - 10);
				}
			}															

		
		// trzy pasy ruchu
		} else if(cellListsNum == 3) {
			street[0] = new Lane(cellsNum, this.begin, this.end, 0, 2, 0);
			street[1] = new Lane(cellsNum, this.begin, this.end, 1, 1, 1);
			street[2] = new Lane(cellsNum, this.begin, this.end, 2, 0, 2);
			if(this.end.getY() < this.begin.getY()) {
				for(int j = 0; j < street[1].cellList.length; j++) {
					street[0].cellList[j].setX(street[0].cellList[j].getX() - 4);
					street[0].cellList[j].setY(street[0].cellList[j].getY() - 4);
					street[2].cellList[j].setX(street[2].cellList[j].getX() + 4);
					street[2].cellList[j].setY(street[2].cellList[j].getY() + 4);
				}
			} else {
				for(int j = 0; j < street[1].cellList.length; j++) {
					street[0].cellList[j].setX(street[0].cellList[j].getX() + 4);
					street[0].cellList[j].setY(street[0].cellList[j].getY() + 4);
					street[2].cellList[j].setX(street[2].cellList[j].getX() - 4);
					street[2].cellList[j].setY(street[2].cellList[j].getY() - 4);
				}
			}
		}
	}
	
	public int getLength() {
		return this.length;
	}
	
	public int getWeight() {
		return this.weight;
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
		return cellListsNum;
	}
	
	public V getBegin() {
		return begin;
	}
	
	public V getEnd() {
		return end;
	}
	
	public String cellsToString() {
		String output = "";
		for(int i = 0; i < cellListsNum; i++) {
			for(int j = 0; j < cellsNum; j++) {
				output += street[i].cellList[j].toString();
			}
		output += "\n------------------------------\n";
		}
		
		return output;
	}
	
}
