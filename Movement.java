import java.util.List;
import java.util.Random;

public class Movement 
{
	private static Random rand = new Random();
	private static Cell tmpCell;
	
	public static void move(List<Vehicle> vehicleList)
	{
		for (Vehicle car : vehicleList)
		{
			Cell cell = car.getCurrentCell();
			Lane[] Road = car.getRoad();
			int listNr = car.getLaneNr();
			int cellNr = cell.getNr();
			car.modifySpeed(1);
			if (rand.nextInt(3) == 0)
			{
				car.modifySpeed(-1);
			}
			
			car.setTmpCell(cell);
			for (int i = 0; i < car.getCurrentSpeed(); i++)
			{
				cell = car.getTmpCell();
				cellNr = cell.getNr();
				if (cellNr >= Road[listNr].length - 1)
				{
					changeRoad(car, cell.getHowManyCellsToCrossroad());
					break;
					//System.out.println("   Jest już na skrzyzowaniu, co teraz?!");
				}
				else
				{
					determineNextCell(car, cell, Road, listNr, cellNr);
				}
			}
			car.setNextCell(car.getTmpCell());
			car.getNextCell().setOccupied(true);
		}
		for (Vehicle vehicle : vehicleList)
		{
			moveToNextCell(vehicle);
		}
	}
	
	public static void determineNextCell(Vehicle car, Cell cell, Lane[] Road, int listNr, int cellNr)
	{
		try
		{
			if (car.isCurvingRight() && cell.getHowManyCellsToCrossroad() == Road[listNr].getHowManyToRight() + 1
				&& !Road[listNr + 1].cellList[cellNr + 1].isForbidden())
			{
				car.setSpeed(1);
				car.setLaneNr(listNr + 1);
				tmpCell = Road[listNr + 1].cellList[cellNr + 1];
			}
			else if (car.isCurvingLeft() && cell.getHowManyCellsToCrossroad() == Road[listNr].getHowManyToLeft() + 1
				&& !Road[listNr - 1].cellList[cellNr + 1].isForbidden())
			{
				car.setSpeed(1);
				car.setLaneNr(listNr - 1);
				tmpCell = Road[listNr - 1].cellList[cellNr + 1];
			}
			else
			{
				tmpCell = Road[listNr].cellList[cellNr + 1];
			}
		}
		catch(Exception e)
		{
			System.out.println("wyjatek");
		}
		if (tmpCell.isOccupied())
		{
			car.setSpeed(0);
		}
		else
		{
			car.setTmpCell(tmpCell);
		}
	}
	
	public static void changeRoad(Vehicle car, int howManyCellsToCrossroad)
	{
		
		Lane[] road = car.getRoad();
		if (car.isMovingForward()) 
		{
			try{car.setRoad(road[0].forward);}catch (Exception e) {System.out.println("---1");}
			try{car.setLaneNr(getNextLaneNr(car));}catch (Exception e) {System.out.println("-----2");}
			try{car.setTmpCell(car.getRoad()[car.getLaneNr()].cellList[car.getCurrentSpeed() - howManyCellsToCrossroad]);}catch (Exception e) {System.out.println("-----3");}
		}
		else if (car.isCurvingRight())
		{
			try{car.setRoad(road[0].right);}catch (Exception e) {System.out.println("---1");}
			try{car.setLaneNr(0);}catch (Exception e) {System.out.println("-----2");}
			try{car.setTmpCell(car.getRoad()[car.getLaneNr()].cellList[1]);}catch (Exception e) {System.out.println("-----3");}
		}
		else
		{
			try{car.setRoad(road[0].left);}catch (Exception e) {System.out.println("---1");}
			try{car.setLaneNr(0);}catch (Exception e) {System.out.println("-----2");}
			try{car.setTmpCell(car.getRoad()[car.getLaneNr()].cellList[1]);}catch (Exception e) {System.out.println("-----3");}
		}
	}
	
	public static int getNextLaneNr(Vehicle car)
	{
		if (car.getLaneNr() <= car.getRoad().length)
		{
			return car.getLaneNr();
		}
		else
		{
			return 0;
		}
	}
	
	public static void moveToNextCell(Vehicle car)
	{
		car.getCurrentCell().setOccupied(false);
		car.setCurrentCell(car.getNextCell());
		car.getCurrentCell().setOccupied(true);
	}
}
