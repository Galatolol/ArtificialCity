import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PedestriansMovement 
{
	private static PedestriansGraph pedestriansGraph;
	private static Cell tmpCell;
	private static List<Pedestrian> pedestriansList = new ArrayList<Pedestrian>();
	private static List<Pedestrian> pedestriansList1 = new ArrayList<Pedestrian>();
	
	public static void move(List<Pedestrian> pedicleList)
	{
		ListIterator<Pedestrian> iter = pedestriansList.listIterator();
		while (iter.hasNext())
		{
			boolean pedRemoved = false;
			Pedestrian ped = iter.next();
			Cell cell = ped.getCurrentCell();
			Lane[] Street = ped.getStreet();
			int cellNr = cell.getNr();
			ped.setTmpCell(cell);
			
			cell = ped.getTmpCell();
			cellNr = cell.getNr();
			if (cellNr >= Street[0].length - 1)
			{
				if (!changeStreet(ped, cell.getHowManyCellsToCrossroad()))
				{
					pedestriansList1.add(ped);
					iter.remove();
					pedRemoved = true;
				}
				break;
			}
			else
			{
				determineNextCell(ped, cell, Street, cellNr);
			}
					
			if (pedRemoved)
			{
				pedRemoved = false;
				continue;
			}
			ped.setNextCell(ped.getTmpCell());
		}
		for (Pedestrian ped : pedestriansList)
		{
			moveToNextCell(ped);
		}
	}
	
	public static void determineNextCell(Pedestrian ped, Cell cell, Lane[] Street, int cellNr)
	{
		try
		{
			tmpCell = Street[0].cellList[cellNr + 1];
			ped.setTmpCell(tmpCell);
		}
		catch(Exception e)
		{
			System.out.println("PedestriansMovement.determineNextCell(): wyjatek");
		}
	}
	
	public static boolean changeStreet(Pedestrian ped, int howManyCellsToCrossroad)
	{
		Lane[] street = ped.getStreet();
		if (ped.isMovingForward()) 
		{
			if (ped.getStreet()[0].forward[0].getSpeedLimit() == -1)
			{
				return false;
			}
			try{ped.setStreet(street[0].forward);}catch (Exception e) {System.out.println("-f-1");}
			try{ped.setTmpCell(ped.getStreet()[0].cellList[0]);}catch (Exception e) {System.out.println("-f-3");}
		}
		else if (ped.isCurvingRight())
		{
			if (ped.getStreet()[0].right[0].getSpeedLimit() == -1)
			{
				return false;
			}
			try{ped.setStreet(street[0].right);}catch (Exception e) {System.out.println("---1");}
			try{ped.setTmpCell(ped.getStreet()[0].cellList[0]);}catch (Exception e) {System.out.println("-----3");}
		}
		else
		{
			if (ped.getStreet()[0].left[0].getSpeedLimit() == -1)
			{
				return false;
			}
			try{ped.setStreet(street[0].left);}catch (Exception e) {System.out.println("---1");}
			try{ped.setTmpCell(ped.getStreet()[0].cellList[0]);}catch (Exception e) {System.out.println("-----3");}
		}
		
		try
		{
			ped.controller.setPrevVertex(ped.getStreet()[0].begin);
			ped.controller.setCurrentVertex(ped.getStreet()[0].end);
			System.out.print("");
			//pedestriansGraph.calcWeightedShortestPath(ped);
		}
		catch (IndexOutOfBoundsException e)
		{
			return false;
		}
		return true;
	}
	
	public static void moveToNextCell(Pedestrian ped)
	{
		ped.getCurrentCell().setOccupied(false);
		ped.setCurrentCell(ped.getNextCell());
		ped.getCurrentCell().setOccupied(true);
	}
}