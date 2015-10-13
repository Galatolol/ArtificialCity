import java.util.ArrayList;
import java.util.List;

public class Main 
{
	public static void main(String[] args)
	{
		
		List<Person> list = new ArrayList<Person>();
		Generator.generate(20, list);
		Test.displayInfo(list);
	}
}
