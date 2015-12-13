import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Movement 
{
	private static Graph myGraph;
	private static Random rand = new Random();
	private static Cell tmpCell;
	private static List<Vehicle> vehicleList1 = new ArrayList<Vehicle>();
	
	public static void move(List<Vehicle> vehicleList)
	{
		Iterator<Vehicle> iter = vehicleList.iterator();
		while (iter.hasNext())
		{
			Vehicle car = iter.next();
			Cell cell = car.getCurrentCell();
			Lane[] Street = car.getStreet();
			int listNr = car.getLaneNr();
			int cellNr = cell.getNr();
			if (car.getCurrentSpeed() < car.getStreet()[0].speedLimit)
			{
				car.modifySpeed(1);
			}
			if (rand.nextInt(3) == 0)
			{
				car.modifySpeed(-1);
			}
			
			car.setTmpCell(cell);
			for (int i = 0; i < car.getCurrentSpeed(); i++)
			{
				cell = car.getTmpCell();
				cellNr = cell.getNr();
				if (cellNr >= Street[listNr].length - 1)
				{
					if (!changeStreet(car, cell.getHowManyCellsToCrossroad()))
					{
						iter.remove();
						vehicleList1.add(car);
						continue;
					}
					break;
					//System.out.println("   Jest już na skrzyzowaniu, co teraz?!");
				}
				else
				{
					determineNextCell(car, cell, Street, listNr, cellNr);
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
	
	public static void determineNextCell(Vehicle car, Cell cell, Lane[] Street, int listNr, int cellNr)
	{
		try
		{
			if (((car instanceof Bus && Util.isBusStop(Street[0], cellNr)) || (car instanceof Tram && Util.isTramStop(Street[0], cellNr))) && car.waiting < 5)
			{
				car.isWaiting = true;
				car.waiting++;
				return;
			}
			else if ((car instanceof Bus || car instanceof Tram) && car.waiting >= 5)
			{
				car.waiting = 0;
				car.isWaiting = false;
				System.out.println("dupa");
			}
			if (car.isCurvingRight() && cell.getHowManyCellsToCrossroad() == Street[listNr].getHowManyToRight() + 1
				&& !Street[listNr + 1].cellList[cellNr + 1].isForbidden())
			{
				car.setSpeed(1);
				car.setLaneNr(listNr + 1);
				tmpCell = Street[listNr + 1].cellList[cellNr + 1];
			}
			else if (car.isCurvingLeft() && cell.getHowManyCellsToCrossroad() == Street[listNr].getHowManyToLeft() + 1
				&& !Street[listNr - 1].cellList[cellNr + 1].isForbidden())
			{
				car.setSpeed(1);
				car.setLaneNr(listNr - 1);
				tmpCell = Street[listNr - 1].cellList[cellNr + 1];
			}
			else
			{
				tmpCell = Street[listNr].cellList[cellNr + 1];
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
	
	public static boolean changeStreet(Vehicle car, int howManyCellsToCrossroad)
	{
		
		Lane[] street = car.getStreet();
		if (car.isMovingForward()) 
		{
			try{car.setStreet(street[0].forward);}catch (Exception e) {System.out.println("-f-1");}
			if (car.getStreet()[0].speedLimit == -1)
			{
				return false;
			}
			try{car.setLaneNr(getNextLaneNr(car));}catch (Exception e) {System.out.println("-f-2");}
			try{car.setTmpCell(car.getStreet()[car.getLaneNr()].cellList[car.getCurrentSpeed() - howManyCellsToCrossroad]);}catch (Exception e) {System.out.println("-f-3");}
		}
		else if (car.isCurvingRight())
		{
			try{car.setStreet(street[0].right);}catch (Exception e) {System.out.println("---1");}
			if (car.getStreet()[0].speedLimit == -1)
			{
				return false;
			}
			try{car.setLaneNr(0);}catch (Exception e) {System.out.println("-----2");}
			try{car.setTmpCell(car.getStreet()[car.getLaneNr()].cellList[2]);}catch (Exception e) {System.out.println("-----3");}
		}
		else
		{
			try{car.setStreet(street[0].left);}catch (Exception e) {System.out.println("---1");}
			if (car.getStreet()[0].speedLimit == -1)
			{
				return false;
			}
			try{car.setLaneNr(0);}catch (Exception e) {System.out.println("-----2");}
			try{car.setTmpCell(car.getStreet()[car.getLaneNr()].cellList[1]);}catch (Exception e) {System.out.println("-----3");}
		}
		
		if (car instanceof Car)
		{
			try
			{
				myGraph.calcWeightedShortestPath((Car)car);
			}
			catch (IndexOutOfBoundsException e)
			{
				return false;
			}
		}
		if (car instanceof Tram)
		{
			((Tram) car).changeDirection();
		}

		return true;
	}
	
	public static int getNextLaneNr(Vehicle car)
	{ System.out.println(car.getLaneNr() + car.getStreet().length);
		if (car.getLaneNr() <= car.getStreet().length)
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
