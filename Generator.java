import java.sql.Time;
import java.util.List;
import java.util.Random;

public class Generator 
{
	private static Random rand = new Random();
	
	public static void generate(int n, List<Person> list)
	{
		String destination, residence, from, goingOut, goingBack, firstName, lastName;
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
			Person p = new Person(age, residence, destination, from, goingOut, goingBack, firstName, lastName);
			list.add(p);
		}

	}
	
	private static int[] generateAgeAndProfession()
	{
		int age, profession, r;
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
			result[0] = rand.nextInt(26) + 65; //66 - 90
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
		int r1, r2;
		Integer time1;
		String time;
		if (profession == 0)
		{
			r1 = rand.nextInt(6);
			if (r1 < 1)
			{
				time = Util.substractTime("09:30", rand.nextInt(59));
			}
			else if (r1 < 3)
			{
				time = Util.substractTime("08:45", rand.nextInt(59));
			}
			else
			{
				time = Util.substractTime("08:00", rand.nextInt(59));
			}
		}
		else if (profession == 1)
		{
			r1 = rand.nextInt(19);
			time1 = new Integer(rand.nextInt(19) + 8);
			time = Util.substractTime(time1.toString() + ":00", rand.nextInt(40));
		}
		else
		{
			r1 = rand.nextInt(22);
			if (r1 < 2)
			{
				time = Util.substractTime("06:00", rand.nextInt(30));
			}
			else if (r1 < 5)
			{
				time = Util.substractTime("07:00", rand.nextInt(30));
			}
			else if (r1 < 10)
			{
				time = Util.substractTime("08:00", rand.nextInt(59));
			}
			else if (r1 < 15)
			{
				time = Util.substractTime("09:00", rand.nextInt(59));
			}
			else if (r1 < 18)
			{
				time = Util.substractTime("10:00", rand.nextInt(59));
			}
			else if (r1 < 20)
			{
				time = Util.substractTime("11:00", rand.nextInt(40));
			}
			else
			{
				time1 = new Integer(rand.nextInt(23) + 1);
				time = Util.substractTime(time1.toString() + ":00", rand.nextInt(59));
			}
		}
		return time;
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

	
}
