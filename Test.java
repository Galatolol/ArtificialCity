import java.util.List;


public class Test 
{
	public static void displayInfo(List<Person> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			Person elem = list.get(i);
			System.out.println("\n \n");
			System.out.println(elem.getFirstName() + " " + elem.getLastName());
			System.out.println("Wiek: " + elem.getAge());
			System.out.println("Mieszka w " + elem.getResidence());
			System.out.println("Codziennie jeździ do " + elem.getDestination());
			System.out.println("Pochodzi z " + elem.getFrom());
			System.out.println("Z domu wychodzi o " + elem.getGoingOut());
			System.out.println("Z pracy/szkoły wraca o " + elem.getGoingBack());
		}
	}
	
	public static void displayInfoAboutCar(Vehicle car)
	{
		Integer listNr = car.getCurrentListNr();
		Integer cellNr = car.getCurrentCell().getNr();
		Integer speed = car.getCurrentSpeed();
		Integer howMany = car.getCurrentCell().getHowManyCellsToCrossroad();
		Integer howManyToRight = car.getCellListTab()[listNr].getHowManyToRight();
		System.out.println("Pas: " + listNr + "  komorka: " + cellNr + "  predkosc: " + speed
				+ "  ile do konca: " + howMany + "  ile do prawa : " + howManyToRight);
	}
	
}
