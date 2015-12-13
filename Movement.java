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
			Vehicle veh = iter.next();
			Cell cell = veh.getCurrentCell();
			Lane[] Street = veh.getStreet();
			int listNr = veh.getLaneNr();
			int cellNr = cell.getNr();
			if (veh.getCurrentSpeed() < veh.getStreet()[0].speedLimit)
			{
				veh.modifySpeed(1);
			}
			if (rand.nextInt(3) == 0)
			{
				veh.modifySpeed(-1);
			}
			
			veh.setTmpCell(cell);
			for (int i = 0; i < veh.getCurrentSpeed(); i++)
			{
				cell = veh.getTmpCell();
				cellNr = cell.getNr();
				if (cellNr >= Street[listNr].length - 1)
				{
					if (!changeStreet(veh, cell.getHowManyCellsToCrossroad()))
					{
						vehicleList1.add(veh);
						iter.remove();
						continue;
					}
					break;
				}
				else
				{
					determineNextCell(veh, cell, Street, listNr, cellNr);
				}
			}
			veh.setNextCell(veh.getTmpCell());
			veh.getNextCell().setOccupied(true);
		}
		for (Vehicle vehicle : vehicleList)
		{
			moveToNextCell(vehicle);
		}
	}
	
	public static void determineNextCell(Vehicle veh, Cell cell, Lane[] Street, int listNr, int cellNr)
	{
		try
		{
			if (((veh instanceof Bus && Util.isBusStop(Street[0], cellNr)) || (veh instanceof Tram && Util.isTramStop(Street[0], cellNr))) && veh.waiting < 5)
			{
				veh.isWaiting = true;
				veh.waiting++;
				return;
			}
			else if ((veh instanceof Bus || veh instanceof Tram) && veh.waiting >= 5)
			{
				veh.waiting = 0;
				veh.isWaiting = false;
			}
			if (veh.isCurvingRight() && cell.getHowManyCellsToCrossroad() == Street[listNr].getHowManyToRight() + 1
				&& !Street[listNr + 1].cellList[cellNr + 1].isForbidden())
			{
				veh.setSpeed(1);
				veh.setLaneNr(listNr + 1);
				tmpCell = Street[listNr + 1].cellList[cellNr + 1];
			}
			else if (veh.isCurvingLeft() && cell.getHowManyCellsToCrossroad() == Street[listNr].getHowManyToLeft() + 1
				&& !Street[listNr - 1].cellList[cellNr + 1].isForbidden())
			{
				veh.setSpeed(1);
				veh.setLaneNr(listNr - 1);
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
			veh.setSpeed(0);
		}
		else
		{
			veh.setTmpCell(tmpCell);
		}
	}
	
	public static boolean changeStreet(Vehicle veh, int howManyCellsToCrossroad)
	{
		
		Lane[] street = veh.getStreet();
		if (veh.isMovingForward()) 
		{
			try{veh.setStreet(street[0].forward);}catch (Exception e) {System.out.println("-f-1");}
			if (veh.getStreet()[0].speedLimit == -1)
			{
				return false;
			}
			try{veh.setLaneNr(getNextLaneNr(veh));}catch (Exception e) {System.out.println("-f-2");}
			try{veh.setTmpCell(veh.getStreet()[veh.getLaneNr()].cellList[veh.getCurrentSpeed() - howManyCellsToCrossroad]);}catch (Exception e) {System.out.println("-f-3");}
		}
		else if (veh.isCurvingRight())
		{
			try{veh.setStreet(street[0].right);}catch (Exception e) {System.out.println("---1");}
			if (veh.getStreet()[0].speedLimit == -1)
			{
				return false;
			}
			try{veh.setLaneNr(0);}catch (Exception e) {System.out.println("-----2");}
			try{veh.setTmpCell(veh.getStreet()[veh.getLaneNr()].cellList[2]);}catch (Exception e) {System.out.println("-----3");}
		}
		else
		{
			try{veh.setStreet(street[0].left);}catch (Exception e) {System.out.println("---1");}
			if (veh.getStreet()[0].speedLimit == -1)
			{
				return false;
			}
			try{veh.setLaneNr(0);}catch (Exception e) {System.out.println("-----2");}
			try{veh.setTmpCell(veh.getStreet()[veh.getLaneNr()].cellList[1]);}catch (Exception e) {System.out.println("-----3");}
		}
		
		if (veh instanceof Car)
		{
			try
			{
				myGraph.calcWeightedShortestPath((Car)veh);
			}
			catch (IndexOutOfBoundsException e)
			{
				return false;
			}
		}
		if (veh instanceof PublicTransport)
		{
			((PublicTransport) veh).changeDirection();
		}

		return true;
	}
	
	public static int getNextLaneNr(Vehicle veh)
	{ 
		if (veh.getLaneNr() <= veh.getStreet().length)
		{
			return veh.getLaneNr();
		}
		else
		{
			return 0;
		}
	}
	
	public static void moveToNextCell(Vehicle veh)
	{
		veh.getCurrentCell().setOccupied(false);
		veh.setCurrentCell(veh.getNextCell());
		veh.getCurrentCell().setOccupied(true);
	}
}
