import java.util.ArrayList;
import java.util.List;

public class Main 
{
	private static List<Person> personList = new ArrayList<Person>();
	private static List<Vehicle> vehicleList = new ArrayList<Vehicle>();
 	
	public static void main(String[] args)
	{	
		createLists();		
		Test.displayInfo(personList);
		Charts.createCharts(personList); 
	}
	
	public static void createLists()
	{
		Generator.generate(1000, personList);
		for (int i = 0; i < personList.size(); i++)
		{
			if (personList.get(i).getIsDriving())
			{
				//vehicleList.add(new Car(personList.get(i)));
			}
		}
	}
}
