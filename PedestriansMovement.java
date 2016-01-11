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
			if (ped.isWaiting)
			{
				if (ped.isWaiting && ped.getStreet() != null && ContentPanel.stopArray[ped.getStreet()[0].begin.id].equals(ped.controller.getDestination()))
				{
					iter.remove();
					pedRemoved = true;
				}
				continue;
			}
			
			try
			{
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
						iter.remove();
						pedRemoved = true;
					}
					continue;
				}
				else
				{
					determineNextCell(ped, cell, Street, cellNr);
				}
			}
			catch (Exception e)
			{
				iter.remove();
				pedRemoved = true;
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
		} catch(Exception e) { }
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
			try{ped.setStreet(street[0].forward);}catch (Exception e) {}
		}
		else if (ped.isCurvingRight())
		{
			try{ped.setStreet(street[0].right);}catch (Exception e) {}
		}
		else
		{
			try{ped.setStreet(street[0].left);}catch (Exception e) {}
		}
		
		if (ped.getStreet() == null)
		{
			return false;
		}
		
		if (ped.getStreet()[0].clDir)
		{
			ped.setNextCell(ped.getStreet()[0].cellList[0]);
		}
		else
		{
			ped.setNextCell(ped.getStreet()[0].cellList[ped.getStreet()[0].cellList.length - 1]);
		}
		
		try
		{
			ped.controller.setPrevVertex(ped.getStreet()[0].getBegin());
			ped.controller.setCurrentVertex(ped.getStreet()[0].getEnd());
			pGraph.calcWeightedShortestPath(ped);
		}
		catch (IndexOutOfBoundsException e)
		{
			return false;
		}
		if ((ped.controller.getDestination().equals("A") || ped.controller.getDestination().equals("C") || 
				ped.controller.getDestination().equals("D")) && Util.isStop(ped.getStreet()[0].getBegin(), ped.controller.getDestination()))
		{
			ped.isWaiting = true;
		}
		return true;
	}
	
	public static void moveToNextCell(Pedestrian ped)
	{
		ped.setCurrentCell(ped.getNextCell());
	}
}