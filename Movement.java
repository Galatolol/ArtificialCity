import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Movement 
{
	private static Graph myGraph;
	private static Random rand = new Random();
	private static Cell tmpCell;
	
	public static void move(List<Vehicle> vehicleList)
	{
		ListIterator<Vehicle> iter = vehicleList.listIterator();
		while (iter.hasNext())
		{
			boolean vehRemoved = false;
			Vehicle veh = iter.next();
			Cell cell = veh.getCurrentCell();
			Lane[] Street = veh.getStreet();
			int listNr = veh.getLaneNr();
			int cellNr = cell.getNr();
			if (veh.getStreet() != null && veh.getCurrentSpeed() < veh.getStreet()[0].getSpeedLimit())
			{
				veh.modifySpeed(1);
			}
			if (rand.nextInt(3) == 0 && veh.getCurrentSpeed() > 1)
			{
				veh.modifySpeed(-1);
			}
			
			veh.setTmpCell(cell);
			try
			{
				for (int i = 0; i < veh.getCurrentSpeed(); i++)
				{
					cell = veh.getTmpCell();
					cellNr = cell.getNr();
					if (cellNr >= Street[listNr].length - 1)
					{
						if (!changeStreet(veh, cell.getHowManyCellsToCrossroad()))
						{
							veh.getCurrentCell().setOccupied(false);
							veh.getNextCell().setOccupied(false);
							iter.remove();
							vehRemoved = true;
						}
						break;
					}
					else
					{
						if(veh.isWaiting && veh.waiting < 10)
						{
							veh.waiting++;
						}
						else
						{
							determineNextCell(veh, cell, Street, listNr, cellNr);
						}
					}
				}
			}
			catch (Exception e)
			{
				veh.getCurrentCell().setOccupied(false);
				veh.getNextCell().setOccupied(false);
				iter.remove();
				vehRemoved = true;
			}
			if (vehRemoved)
			{
				vehRemoved = false;
				continue;
			}
			veh.setNextCell(veh.getTmpCell());
			veh.getNextCell().setOccupied(true);
		}
		for (Vehicle veh : vehicleList)
		{
			moveToNextCell(veh);
		}
	}
	
	public static void determineNextCell(Vehicle veh, Cell cell, Lane[] Street, int listNr, int cellNr)
	{
		try
		{
			if (((veh instanceof Bus && Util.isBusStop(Street[0], cellNr, (PublicTransport) veh)) || (veh instanceof Tram && Util.isTramStop(Street[0], cellNr, (PublicTransport) veh))) && veh.waiting < 5)
			{
				veh.isWaiting = true;
				veh.setSpeed(0);
				tmpCell = Street[listNr].cellList[cellNr];
			}
			else if ((veh instanceof Bus || veh instanceof Tram) && veh.waiting >= 10)
			{
				veh.waiting = 0;
				veh.isWaiting = false;
				veh.setSpeed(1);
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
		catch(Exception e) { }
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
			if (veh.getStreet()[0].forward != null && veh.getStreet()[0].forward[0].getSpeedLimit() == -1)
			{
				return false;
			}
			try{veh.setStreet(street[0].forward);}catch (Exception e) { }
			int laneNr = getNextLaneNr(veh);
			if (veh instanceof Bus)
			{
				laneNr = street[0].forward.length - 1;
			}
			try{veh.setLaneNr(laneNr);}catch (Exception e) { } 
			try{veh.setTmpCell(veh.getStreet()[veh.getLaneNr()].cellList[veh.getCurrentSpeed() - howManyCellsToCrossroad]);}catch (Exception e) { }
		}
		else if (veh.isCurvingRight())
		{
			if (veh.getStreet()[0].right != null && veh.getStreet()[0].right[0].getSpeedLimit() == -1)
			{
				return false;
			}
			try{veh.setStreet(street[0].right);}catch (Exception e) { }
			int laneNr = 0;
			if (veh instanceof Bus)
			{
				laneNr = street[0].right.length - 1;
			}
			try{veh.setLaneNr(laneNr);}catch (Exception e) { }
			try{veh.setTmpCell(veh.getStreet()[veh.getLaneNr()].cellList[2]);}catch (Exception e) { }
		}
		else
		{
			if (veh.getStreet()[0].left != null && veh.getStreet()[0].left[0].getSpeedLimit() == -1)
			{
				return false;
			}
			try{veh.setStreet(street[0].left);}catch (Exception e) { }
			int laneNr = 0;
			if (veh instanceof Bus)
			{
				laneNr = street[0].left.length - 1;
			}
			try{veh.setLaneNr(laneNr);}catch (Exception e) { }
			try{veh.setTmpCell(veh.getStreet()[veh.getLaneNr()].cellList[1]);}catch (Exception e) { }
		}
		
		if (veh instanceof Car)
		{
			try
			{
				((Car) veh).driver.setPrevVertex(veh.getStreet()[0].begin);
				((Car) veh).driver.setCurrentVertex(veh.getStreet()[0].end);
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
		if (veh.getLaneNr() < veh.getStreet().length)
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
