import java.util.ArrayList;
import java.util.List;

public class Main 
{
	private static List<Person> personList = new ArrayList<Person>();
 	
	public static void main(String[] args)
	{	
		createLists();		
		Test.displayInfo(personList);
		Charts.createCharts(personList); 
	}
	
	public static void createLists()
	{
		Generator.generate(1000, personList, false);
	}
}
