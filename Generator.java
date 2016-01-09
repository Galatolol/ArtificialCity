import java.util.List;
import java.util.Random;

public class Generator 
{
	private static Graph myGraph;
	private static PedestriansGraph pGraph;
	private static Random rand = new Random();
	
	public static void generate(int n, List<Person> list, boolean willBeDriving)
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
			if (!willBeDriving)
			{
				driving = generateDriving(age, residence);
			}
			else
			{
				driving = true;
			}
			
			Person person = new Person(age, residence, destination, from, goingOut, goingBack, firstName, lastName, driving);
			if (person.getIsDriving())
			{
				generateVertices(person);
			}
			list.add(person);
			
			
		}
	}
	
	private static void generateVertices(Person person)
	{
		int r, prev, curr, dest;
		dest = 0;
		
		if (person.getResidence().equals("A"))
		{
			r = rand.nextInt(4);
			if (r == 0)
			{
				prev = 1;
				curr = 25;
				dest = 84;
			}
			else if (r == 1)
			{
				prev = 3;
				curr = 14;
			}
			else if (r == 2)
			{
				prev = 4;
				curr = 14;
			}
			else
			{
				prev = 6;
				curr = 8;
			}
		}
		else if (person.getResidence().equals("B"))
		{
			r = rand.nextInt(2);
			if (r == 0)
			{
				prev = 49;
				curr = 48;
			}
			else
			{
				prev = 46;
				curr = 45;
			}
		}
		else if (person.getResidence().equals("C"))
		{
			r = rand.nextInt(3);
			if (r == 0)
			{
				prev = 67;
				curr = 66;
				r = rand.nextInt(2);
				if (r == 0)
				{
					dest = 94;
				}
				else
				{
					dest = 96;
				}
			}
			else
			{
				prev = 81;
				curr = 80;
			}
		}
		else
		{
			r = rand.nextInt(3);
			if (r == 0)
			{
				prev = 78;
				curr = 79;
			}
			else if (r == 1)
			{
				prev = 23;
				curr = 22;
			}
			else
			{
				prev = 24;
				curr = 25;
				dest = 84;
			}
		}
		if (dest == 0)
		{
			while (true)
			{
				if (person.getDestination().equals("A"))
				{
					r = rand.nextInt(3);
					if (r == 0)
					{
						dest = 84;
					}
					else if (r == 1)
					{
						dest = 85;
					}
					else
					{
						dest = 87;
					}
				}
				else if (person.getDestination().equals("B"))
				{
					r = rand.nextInt(3);
					if (r == 0)
					{
						dest = 90;
					}
					else if (r == 1)
					{
						dest = 91;
					}
					else
					{
						dest = 92;
					}
				}
				else if (person.getDestination().equals("C"))
				{
					r = rand.nextInt(4);
					if (r == 0)
					{
						dest = 94;
					}
					else if (r == 1)
					{
						dest = 96;
					}
					else //2x wieksza szansa
					{
						dest = 98;
					}
				}
				else
				{
					r = rand.nextInt(2);
					if (r == 0)
					{
						dest = 100;
					}
					else
					{
						dest = 101;
					}
				}
				if (dest != prev && dest != curr)
				{
					break;
				}
			}
		}
		person.setAllVertices(myGraph.vertices.get(prev), myGraph.vertices.get(curr), myGraph.vertices.get(dest));
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
			result[0] = rand.nextInt(6) + 20; //20 - 25
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
					return "S";	
				case 1:
					return "A";
				case 2:
					return "B";
				case 3:
					return "C";
				case 4:
					return "D";
				//case 6:
					//return "AGH";
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
			{
					goingOut = Util.substractTime("11:00", rand.nextInt(40));
			}
			else
			{
				time = new Integer(rand.nextInt(23) + 1);
				goingOut = Util.substractTime(time.toString() + ":00", rand.nextInt(59));
			}
		}
		/*int hrs = Integer.parseInt(goingOut.split(":")[0]);
		int min = Integer.parseInt(goingOut.split(":")[1]);
		String hrs1 = String.format("%02d", hrs);
		String min1 = String.format("%02d", min);*/
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
		goingBack = Util.addTime(goingBack, rand.nextInt(15));
		/*int hrs = Integer.parseInt(goingBack.split(":")[0]);
		int min = Integer.parseInt(goingBack.split(":")[1]);
		String hrs1 = String.format("%02d", hrs);
		String min1 = String.format("%02d", min);*/
		return goingBack;
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
	
	public static PublicTransport generatePubTran(int nr, int destination)
	{
		String[] linia8_1 = {"forward", "forward", "forward", "forward", "forward", "forward", "forward", "right", "forward", "C", "3", "14"};
		String[] linia8_2 = {"left", "forward", "forward", "forward", "forward", "forward", "forward", "forward", "forward", "A", "56", "55"};	
		
		String[] linia4_1 = {"forward", "forward", "forward", "forward", "forward", "forward", "forward", "right", "forward", "forward", "B", "3", "14"};
		String[] linia4_2 = {"forward", "forward", "right", "forward", "forward", "forward", "forward", "forward", "forward", "forward", "A", "52", "60"};
		
		String[] linia18_1 = {"forward", "forward", "forward", "forward", "B", "56", "55"};
		String[] linia18_2 = {"forward", "forward", "forward", "forward", "C", "52", "60"};
		
		String[] linia179_1 = {"forward", "forward", "forward", "forward", "forward", "forward", "forward", "forward", "C", "4", "14"};
		String[] linia179_2 = {"forward", "forward", "forward", "forward", "forward", "forward", "forward", "forward", "A", "81", "80"};
		
		String[] linia173_1 = {"forward", "right", "forward", "forward", "forward", "forward", "forward", "C", "23", "22"};
		String[] linia173_2 = {"forward", "forward", "left",  "forward", "forward", "forward", "forward", "D", "81", "80"};
		
		String[] linia159_1 = {"forward", "forward", "right", "forward", "forward", "forward", "forward", "forward", "D", "4", "14"};
		String[] linia159_2 = {"forward", "forward", "forward", "left", "forward", "forward", "forward", "forward", "A", "23", "22"};
		
		PublicTransport veh = null;
		
		if (nr == 8)
		{
			veh = new Tram(destination, linia8_1, linia8_2);

		}
		else if (nr == 18)
		{
			veh = new Tram(destination, linia18_1, linia18_2);
		}
		else if (nr == 4)
		{
			veh = new Tram(destination, linia4_1, linia4_2);
		}
		else if (nr == 179)
		{
			veh = new Bus(destination, linia179_1, linia179_2);
		}
		else if (nr == 159)
		{
			veh = new Bus(destination, linia159_1, linia159_2);
		}
		else if (nr == 173)
		{
			veh = new Bus(destination, linia173_1, linia173_2);
		}
		
		if (destination == 1)
		{
			veh.changeDestination1();
		}
		else
		{
			veh.changeDestination2();
		}
		return veh;
	}
	
	public static Vehicle generateCar(Person driver)
	{
		Vehicle car = new Car(driver);
		int prevVertex = Integer.parseInt(driver.getPrevVertex().toString());
		int currentVertex = Integer.parseInt(driver.getCurrentVertex().toString());
		car.setStreet(Util.getStreet(prevVertex, currentVertex));
		car.setLaneNr(0);
		car.setCurrentCell(car.getStreet()[0].cellList[0]);
		car.setSpeed(1);
		myGraph.calcWeightedShortestPath((Car)car);
		return car;
	}
	
	public static Pedestrian generatePed(Person controller)
	{
		int[] a = {3, 43, 61};
		int[] b = {21, 22, 29, 26};
		int[] c = {24, 37, 23, 60, 40};
		int[] d = {40, 39, 38, 19, 11, 9, 1};
		int begin, end, dest;
		Pedestrian ped = new Pedestrian(controller);
		if (controller.getResidence().equals("A"))
		{
			begin = a[rand.nextInt(a.length)];
		}
		else if (controller.getResidence().equals("B"))
		{
			begin = b[rand.nextInt(b.length)];
		}
		else if (controller.getResidence().equals("C"))
		{
			begin = c[rand.nextInt(c.length)];
		}
		else if (controller.getResidence().equals("D"))
		{
			begin = d[rand.nextInt(d.length)];
		}
		else
		{
			begin = rand.nextInt(63) + 1;
		}
		
		while (true)
		{
			end = rand.nextInt(63) + 1;
			while (begin == end)
			{
				end = rand.nextInt(63) + 1;
			}
			
			try
			{
				ped.setStreet(Util.getPedStreet(begin, end));
			}
			catch (Exception e) {}
			if (ped.getStreet() == null)
			{
				continue;
			}
			else
			{
				break;
			}
		}
		
		while(true)
		{
			if (controller.getDestination().equals("A"))
			{
				dest = a[rand.nextInt(a.length)];
			}
			else if (controller.getDestination().equals("B"))
			{
				dest = b[rand.nextInt(b.length)];
			}
			else if (controller.getDestination().equals("C"))
			{
				dest = c[rand.nextInt(c.length)];
			}
			else if (controller.getDestination().equals("D"))
			{
				dest = d[rand.nextInt(d.length)];
			}
			else
			{
				dest = rand.nextInt(63) + 1;
			}
			if (dest != begin && dest != end)
			{
				break;
			}
		}
		
		controller.setAllVertices(pGraph.vertices.get(begin), pGraph.vertices.get(end), pGraph.vertices.get(dest));
		int dd;
		if (ped.getStreet()[0].clDir)
		{
			dd = 0;
		}
		else
		{
			dd = ped.getStreet()[0].cellList.length - 1;
		}
		ped.setCurrentCell(ped.getStreet()[0].cellList[dd]);
		pGraph.calcWeightedShortestPath(ped);
		return ped;
	}
}

