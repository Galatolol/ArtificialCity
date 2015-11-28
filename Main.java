import java.util.ArrayList;
import java.util.List;

public class Main 
{
	private static List<Person> personList = new ArrayList<Person>();
	private static List<Vehicle> vehicleList = new ArrayList<Vehicle>();
 	
	public static void main(String[] args)
	{	
		//createLists();		
		//Test.displayInfo(personList);
		//Charts.createCharts(personList); 
		
		Lane[] cellListTab = new Lane[3];
		cellListTab[0] = new Lane(15, new V(2, 90, 965), new V(1, 55, 965),0, 2, 0);
		cellListTab[1] = new Lane(15, new V(2, 90, 965), new V(1, 55, 965),0, 1, 1);
		cellListTab[2] = new Lane(15, new V(2, 90, 965), new V(1, 55, 965),0, 0, 2);
		
		Vehicle auto = new Car(null);
		auto.setStreet(cellListTab);
		auto.setLaneNr(1);
		auto.setCurrentCell(cellListTab[0].cellList[0]);
		auto.setSpeed(1);
		auto.curveLeft();
		vehicleList.add(auto);
		
		for (int i = 0; i < 15; i++)
		{
			System.out.println("Jednostka czasu: " + i);
			Test.displayInfoAboutCar(auto);
			Movement.move(vehicleList);
		}
	}
	
	public static void createLists()
	{
		Generator.generate(20, personList);
		for (int i = 0; i < personList.size(); i++)
		{
			if (personList.get(i).getIsDriving())
			{
				vehicleList.add(new Car(personList.get(i)));
			}
		}
	}
}
