import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PedestriansMovement 
{
	private static PedestriansGraph pGraph;
	private static Cell tmpCell;
	private static List<Pedestrian> pList1 = new ArrayList<Pedestrian>();
	
	public static void move(List<Pedestrian> pList)
	{
		ListIterator<Pedestrian> iter = pList.listIterator();
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
			
			if ((Street[0].clDir && cellNr >= Street[0].cellList.length - 1) || (!Street[0].clDir && cellNr <= 0))
			{
				if (!changeStreet(ped, cell.getHowManyCellsToCrossroad()))
				{
					pList1.add(ped);
					iter.remove();
					pedRemoved = true;
				}
				continue;
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
		for (Pedestrian ped : pList)
		{
			moveToNextCell(ped);
		}
	}
	
	public static void determineNextCell(Pedestrian ped, Cell cell, Lane[] street, int cellNr)
	{
		try
		{
			if (street[0].clDir)
			{
				tmpCell = street[0].cellList[cellNr + 1];
			}
			else
			{
				tmpCell = street[0].cellList[cellNr - 1];
			}
			ped.setTmpCell(tmpCell);
		}
		catch(Exception e)
		{
			System.out.println("PedestriansMovement.determineNextCell(): wyjatek");
		}
	}
	
	public static boolean changeStreet(Pedestrian ped, int howManyCellsToCrossroad)
	{
		System.out.println("Ped.changeStreet()");
		Lane[] street = ped.getStreet();
		if (ped.isMovingForward()) 
		{
			if (ped.getStreet()[0].forward[0].getSpeedLimit() == -1)
			{
				return false;
			}
			try{ped.setStreet(street[0].forward);}catch (Exception e) {System.out.println("-f-1");}
		}
		else if (ped.isCurvingRight())
		{
			if (ped.getStreet()[0].right[0].getSpeedLimit() == -1)
			{
				return false;
			}
			try{ped.setStreet(street[0].right);}catch (Exception e) {System.out.println("---1");}
		}
		else
		{
			if (ped.getStreet()[0].left[0].getSpeedLimit() == -1)
			{
				return false;
			}
			try{ped.setStreet(street[0].left);}catch (Exception e) {System.out.println("---1");}
		}
		
		if (ped.getStreet()[0].clDir)
		{
			ped.setNextCell(ped.getStreet()[0].cellList[0]);
		}
		else
		{
			ped.setNextCell(ped.getStreet()[0].cellList[ped.getStreet()[0].cellList.length - 1]);
			System.out.println("ped" + ped.getTmpCell().getNr());
		}
		
		try
		{
			ped.controller.setPrevVertex(ped.getStreet()[0].getBegin());
			ped.controller.setCurrentVertex(ped.getStreet()[0].getEnd());
			System.out.print("");
			pGraph.calcWeightedShortestPath(ped);
		}
		catch (IndexOutOfBoundsException e)
		{
			return false;
		}
		return true;
	}
	
	public static void moveToNextCell(Pedestrian ped)
	{
		ped.setCurrentCell(ped.getNextCell());
	}
}