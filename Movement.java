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
	private static List<Vehicle> vehicleList1 = new ArrayList<Vehicle>();
	private static long timer, timer1, timerr;
	private static boolean signaling1;
	
	public static void move(List<Vehicle> vehicleList)
	{
		ListIterator<Vehicle> iter = vehicleList.listIterator();
		timer = System.currentTimeMillis();
		setSignaling(false);
		while (iter.hasNext())
		{
			boolean vehRemoved = false;
			Vehicle veh = iter.next();
			Cell cell = veh.getCurrentCell();
			Lane[] Street = veh.getStreet();
			int listNr = veh.getLaneNr();
			int cellNr = cell.getNr();
			if (veh.getCurrentSpeed() < veh.getStreet()[0].getSpeedLimit())
			{
				veh.modifySpeed(1);
			}
			if (rand.nextInt(3) == 0 && veh.getCurrentSpeed() > 1)
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
						veh.getCurrentCell().setOccupied(false);
						veh.getNextCell().setOccupied(false);
						vehicleList1.add(veh);
						iter.remove();
						vehRemoved = true;
					}
					break;
				}
				else
				{
					determineNextCell(veh, cell, Street, listNr, cellNr);
				}
			}
			if (vehRemoved)
			{
				vehRemoved = false;
				continue;
			}
			veh.setNextCell(veh.getTmpCell());
			veh.getNextCell().setOccupied(true);
			timer1 = System.currentTimeMillis();
			System.out.println(timerr);
			if (timerr >= 10 && signaling1 == false)
			{
				timerr = 0;
				signaling1 = true;
				setSignaling(signaling1);
				System.out.println("wlaczam");
			}
			else if (timerr >= 30 && signaling1 == true)
			{
				timerr = 0;
				signaling1 = false;
				setSignaling(signaling1);
				System.out.println("wyłączam");
			}
		}
		for (Vehicle vehicle : vehicleList)
		{
			moveToNextCell(vehicle);
		}
		timerr++;
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
			System.out.println("Movement.determineNextCell(): wyjatek");
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
			if (veh.getStreet()[0].forward[0].getSpeedLimit() == -1)
			{
				return false;
			}
			try{veh.setStreet(street[0].forward);}catch (Exception e) {System.out.println("-f-1");}
			int laneNr = getNextLaneNr(veh);
			if (veh instanceof Bus)
			{
				laneNr = street[0].forward.length - 1;
			}
			try{veh.setLaneNr(laneNr);}catch (Exception e) {System.out.println("-f-2");} 
			try{veh.setTmpCell(veh.getStreet()[veh.getLaneNr()].cellList[veh.getCurrentSpeed() - howManyCellsToCrossroad]);}catch (Exception e) {System.out.println("-f-3");}
		}
		else if (veh.isCurvingRight())
		{
			if (veh.getStreet()[0].right[0].getSpeedLimit() == -1)
			{
				return false;
			}
			try{veh.setStreet(street[0].right);}catch (Exception e) {System.out.println("---1");}
			int laneNr = 0;
			if (veh instanceof Bus)
			{
				laneNr = street[0].right.length - 1;
			}
			try{veh.setLaneNr(laneNr);}catch (Exception e) {System.out.println("-----2");}
			try{veh.setTmpCell(veh.getStreet()[veh.getLaneNr()].cellList[2]);}catch (Exception e) {System.out.println("-----3");}
		}
		else
		{
			if (veh.getStreet()[0].left[0].getSpeedLimit() == -1)
			{
				return false;
			}
			try{veh.setStreet(street[0].left);}catch (Exception e) {System.out.println("---1");}
			int laneNr = 0;
			if (veh instanceof Bus)
			{
				laneNr = street[0].left.length - 1;
			}
			try{veh.setLaneNr(laneNr);}catch (Exception e) {System.out.println("-----2");}
			try{veh.setTmpCell(veh.getStreet()[veh.getLaneNr()].cellList[1]);}catch (Exception e) {System.out.println("-----3");}
		}
		
		if (veh instanceof Car)
		{
			try
			{
				((Car) veh).driver.setPrevVertex(veh.getStreet()[0].begin);
				((Car) veh).driver.setCurrentVertex(veh.getStreet()[0].end);
				System.out.print("");
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
	
	public static void setSignaling(boolean signaling)
	{
		Lane[] street = Util.getStreet(20, 76);
		Lane[] street1 = Util.getStreet(81, 80);
		Lane[] street2 = Util.getStreet(78, 79);

		int length = street[0].cellList.length;
		for (int i = 0; i < street.length; i++)
		{
			street[i].cellList[length - 1].setOccupied(signaling);
			street[i].cellList[length - 2].setOccupied(signaling);
		}
		length = street1[0].cellList.length;
		for (int i = 0; i < street1.length; i++)
		{
			street1[i].cellList[length - 1].setOccupied(signaling);
			street1[i].cellList[length - 2].setOccupied(signaling);
		}
		length = street2[0].cellList.length;
		for (int i = 0; i < street2.length; i++)
		{
			street2[i].cellList[length - 1].setOccupied(!signaling);
			//street2[i].cellList[length - 2].setOccupied(!signaling);
		}
	}
	
	public static void moveToNextCell(Vehicle veh)
	{
		veh.getCurrentCell().setOccupied(false);
		veh.setCurrentCell(veh.getNextCell());
		veh.getCurrentCell().setOccupied(true);
	}
}
