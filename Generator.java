import java.util.List;
import java.util.Random;

public class Generator 
{
	private static Random rand = new Random();
	
	public static void generate(int n, List<Person> list)
	{
		String destination, residence, from, goingOut, goingBack, firstName, lastName;
		boolean driving;
		int[] ageAndProfession;
		int age, profession;
		for (int i = 0; i < n; i++)
		{
			ageAndProfession = generateAgeAndProfession();
			age = ageAndProfession[0];
			profession = ageAndProfession[1];
			residence = generateResidence();
			while(true)
			{
				destination = generateDestination(profession); 
				if (!destination.equals(residence))
				{
					break;
				}
			}
			while(true)
			{
				from = generateFrom(age);
				if (from.equals("") || !from.equals("residence"))
				{
					break;
				}
			}
			goingOut = generateGoingOut(profession);
			goingBack = generateGoingBack(goingOut, profession);
			firstName = generateFirstName();
			lastName = generateLastName();
			if (age > 65)
			{
				destination = "";
				from = "";
				goingOut = "";
				goingBack = "";
			}
			driving = generateDriving(age, residence);
			
			
			//trzeba dodac w konsrtuktorze : wierzcholki destionation i current
			//Person p = new Person(age, residence, destination, goingOut, goingBack, firstName, lastName, driving);
			//list.add(p);
		}

	}
	
	private static int[] generateAgeAndProfession()
	{
		int r;
		int[] result = new int[2]; // wiek, zajecie
		r = rand.nextInt(10);
		if (r < 1)
		{
			result[0] = rand.nextInt(7) + 13; //13 - 19
			result[1] = 0;
		}
		else if (r < 4)
		{
			result[0] = rand.nextInt(10) + 20; //20 - 29
			result[1] = 1;
		}
		else if (r < 8)
		{
			result[0] = rand.nextInt(41) + 25; //25 - 65
			result[1] = 2;
		}
		else
		{
			result[0] = rand.nextInt(26) + 66; //66 - 90
			result[1] = 3;
		}
		return result;
	}
	
	private static String generateResidence()
	{
		int r = rand.nextInt(13);
		if (r < 1)
		{
			return "S";
		}
		else if (r < 4)
		{
			return "A";
		}
		else if (r < 7)
		{
			return "B";
		}
		else if (r < 10)
		{
			return "C";
		}
		else
		{
			return "D";
		}
	}
	
	private static String generateDestination(int profession)
	{
		if (profession == 1)
		{
			int r = rand.nextInt(6);
			switch(r)
			{
				case 0:
					return "AGH";
				case 1:
					return "A";
				case 2:
					return "B";
				case 3:
					return "C";
				case 4:
					return "D";
				case 5: 
					return "S";	
			}
		}
		return generateResidence();
	}
	
	private static String generateFrom(int age)
	{
		int r = rand.nextInt(12);
		switch(r)
		{
			case 0:
				return "A";
			case 1:
				return "B";
			case 2:
				return "C";
			case 3:
				return "D";
			default:
				return "";
		}
	}
	
	private static String generateGoingOut(int profession)
	{
		int r;
		Integer time;
		String goingOut;
		if (profession == 0)
		{
			r = rand.nextInt(6);
			if (r < 1)
			{
				goingOut = Util.substractTime("09:30", rand.nextInt(59));
			}
			else if (r < 3)
			{
				goingOut = Util.substractTime("08:45", rand.nextInt(59));
			}
			else
			{
				goingOut = Util.substractTime("08:00", rand.nextInt(59));
			}
		}
		else if (profession == 1)
		{
			r = rand.nextInt(19);
			time = new Integer(rand.nextInt(11) + 8);
			goingOut = Util.substractTime(time.toString() + ":00", rand.nextInt(40));
		}
		else
		{
			r = rand.nextInt(23);
			if (r < 2)
			{
				goingOut = Util.substractTime("06:00", rand.nextInt(30));
			}
			else if (r < 5)
			{
				goingOut = Util.substractTime("07:00", rand.nextInt(30));
			}
			else if (r < 10)
			{
				goingOut= Util.substractTime("08:00", rand.nextInt(59));
			}
			else if (r < 16)
			{
				goingOut = Util.substractTime("09:00", rand.nextInt(59));
			}
			else if (r < 19)
			{
				goingOut = Util.substractTime("10:00", rand.nextInt(59));
			}
			else if (r < 21)
			{		System.out.println(Util.addTime("02:56", 400));
					goingOut = Util.substractTime("11:00", rand.nextInt(40));
			}
			else
			{
				time = new Integer(rand.nextInt(23) + 1);
				goingOut = Util.substractTime(time.toString() + ":00", rand.nextInt(59));
			}
		}
		return goingOut;
	}
	
	private static String generateGoingBack(String goingOut, int profession)
	{
		String goingBack;
		int r;
		if (profession == 0)
		{
			r = rand.nextInt(6);
			if (r < 2)
			{
				goingBack = "13:50";
			}
			else if (r < 5)
			{
				goingBack = "14:35";
			}
			else
			{
				goingBack = "15:20";
			}
		}
		else if (profession == 1)
		{
			goingBack = Util.addTime(goingOut, (rand.nextInt(11) + 3) * 30);
			if (Integer.parseInt(Util.getHour(goingBack)) >= 22)
			{
				Util.setHour(goingBack, "21");
			}
		}
		else
		{
			goingBack = Util.addTime(goingOut, (rand.nextInt(5) + 6) * 60);
		}
		return Util.addTime(goingBack, rand.nextInt(15));
	}
	
	private static String generateFirstName()
	{
		String[] firstNames = {"Ryszard", "Jan", "Kamil", "Michał", "Janusz", "Jarosław", "Beata", 
				"Paulina", "Anna", "Ewa", "Krystyna", "Adam", "Andrzej", "Eryk", "Robert", "Joanna", "Jakub"};
		return firstNames[rand.nextInt(firstNames.length)];
	}
	
	private static String generateLastName()
	{
		String[] lastNames = {"Nowak", "Kłos", "Klich", "Jurek", "Majtyka", "Nawałka", "Wąs", "Ćmiel", "Błachut", 
				"Oramus", "Mazur", "Wojtylak", "Banach", "Kopernik", "Bąk", "Filipiak", "Kubala"};
		return lastNames[rand.nextInt(lastNames.length)];
	}

	private static boolean generateDriving(int age, String residence)
	{
		if (age > 19 && age < 25)
		{
			if (rand.nextInt(9) < 1)
			{
				return true;
			}
		}
		else if (age >= 25 && age < 75)
		{
			int r = rand.nextInt(10);
			if (residence.length() == 1)
			{
				if (r < 8)
				{
					return true;
				}
			}
			else
			{
				if (r < 2)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static Tram generateTram(int nr, int directionn)
	{
		String[] linia8_1 = {"forward", "forward", "forward", "forward", "forward", "forward", "forward", "right", "C"};
		String[] linia8_2 = {"left", "forward", "forward", "forward", "forward", "forward", "forward", "forward", "A"};	
		
		String[] linia4_1 = {"forward", "forward", "forward", "forward", "forward", "forward", "forward", "right", "forward", "forward", "B"};
		String[] linia4_2 = {"forward", "forward", "right", "forward", "forward", "forward", "forward", "forward", "forward", "forward", "A"};
		
		String[] linia18_1 = {"forward", "forward", "forward", "forward", "B"};
		String[] linia18_2 = {"forward", "forward", "forward", "forward", "C"};
		
		Tram tram;
		Lane[] street = null;
		
		if (nr == 8)
		{
			tram = new Tram(directionn, linia8_1, linia8_2);
			if (directionn == 1)
			{
				street = Util.getPubTranStreet(3, 14);
			}
			else
			{
				street = Util.getPubTranStreet(56, 55);
			}
		}
		else if (nr == 18)
		{
			tram = new Tram(directionn, linia18_1, linia18_2);
		}
		else
		{
			tram = new Tram(directionn, linia4_1, linia4_2);
		}
		
		tram.setStreet(street);
		tram.setLaneNr(0);
		tram.setCurrentCell(street[0].cellList[0]);
		tram.setSpeed(1);
		((Tram)tram).changeDirection();
		
		return tram;
	}
}
