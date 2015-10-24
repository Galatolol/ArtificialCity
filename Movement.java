import java.util.List;

public class Movement 
{
	private static Cell tmpCell;
	
	public static void move(List<Vehicle> vehicleList)
	{
		for (int i = 0; i < vehicleList.size(); i++)
		{
			Vehicle car = vehicleList.get(i);
			Cell cell = car.getCurrentCell();
			CellList[] cellListTab = car.getCellListTab();
			int listNr = car.getCurrentListNr();
			int cellNr = cell.getNr();

			if (cellNr < cellListTab[listNr].length - 1 && !cellListTab[listNr].cellList[cellNr + 1].isOccupied())
			{
				car.modifySpeed(1);
			}
			
			car.setTmpCell(cell);
			for (int j = 0; j < car.getCurrentSpeed(); j++)
			{
				cell = car.getTmpCell();
				cellNr = cell.getNr();
				if (cellNr >= cellListTab[listNr].length - 1)
				{
					System.out.println("   Jest już na skrzyzowaniu, co teraz?!");
				}
				else
				{
					determineNextCell(car, cell, cellListTab, listNr, cellNr);
				}
			}
			car.setNextCell(car.getTmpCell());
			car.getNextCell().setOccupied(true);
		}
		for (int i = 0; i < vehicleList.size(); i++)
		{
			moveToNextCell(vehicleList.get(i));
		}
	}
	
	public static void determineNextCell(Vehicle car, Cell cell, CellList[] cellListTab, int listNr, int cellNr)
	{
		try
		{
			if (car.isCurvingRight() && cell.getHowManyCellsToCrossroad() == cellListTab[listNr].getHowManyToRight() + 3
				&& !cellListTab[listNr + 1].cellList[cellNr + 1].isForbidden())
			{
				car.setSpeed(1);
				car.setCurrentListNr(listNr + 1);
				tmpCell = cellListTab[listNr + 1].cellList[cellNr + 1];
			}
			else if (car.isCurvingLeft() && cell.getHowManyCellsToCrossroad() == cellListTab[listNr].getHowManyToLeft() + 3
				&& !cellListTab[listNr - 1].cellList[cellNr + 1].isForbidden())
			{
				car.setSpeed(1);
				car.setCurrentListNr(listNr - 1);
				tmpCell = cellListTab[listNr - 1].cellList[cellNr + 1];
			}
		}
		catch(Exception e)
		{
			System.out.println("wyjatek");
		}
		tmpCell = cellListTab[listNr].cellList[cellNr + 1];
		if (tmpCell.isOccupied())
		{
			car.setSpeed(0);
			
		}
		else
		{
			car.setTmpCell(tmpCell);
		}
	}
	
	public static void moveToNextCell(Vehicle car)
	{
		car.getCurrentCell().setOccupied(false);
		car.setCurrentCell(car.getNextCell());
		car.getCurrentCell().setOccupied(true);
	}
}
