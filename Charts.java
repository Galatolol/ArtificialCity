import java.util.List;

public class Charts 
{
	public static void createCharts(List<Person> personList)
	{
		//------------WYKRESY-------------------
		//------------wiek----------------------
		Integer tmp;
		String[] tab = new String[personList.size()];
		for (int i = 0; i < personList.size(); i ++)
		{
			tmp = personList.get(i).getAge();
			tab[i] = tmp.toString();
		}
	    Chart chart = new Chart("Wykres wieku", "Rozłożenie wieku w populacji", "Wiek", "Wystąpienia", tab);
	    chart.pack();
	    chart.setVisible(true);
	    
	    //----------miejsceZamieszkania---------
		for (int i = 0; i < personList.size(); i ++)
		{
			tab[i] = personList.get(i).getResidence();
		}
	    Chart chart1 = new Chart("Wykres zamieszkania", "Rozłożenie miejsca zamieszkania w populacji", "Miejsce zamieszkania", "Wystąpienia", tab);
	    chart1.pack();
	    chart1.setVisible(true);
	    
	    //----------miejsceDocelowe---------
		for (int i = 0; i < personList.size(); i ++)
		{
			tab[i] = personList.get(i).getDestination();
		}
	    Chart chart2 = new Chart("Wykres celu", "Rozłożenie miejsca docelowego w populacji", "Miejsce docelowe", "Wystąpienia", tab);
	    chart2.pack();
	    chart2.setVisible(true);
	    
	    //--------godzinaWyjsciaZDomu-------------
		for (int i = 0; i < personList.size(); i ++)
		{
			tab[i] = personList.get(i).getGoingOut();
		}
	    Chart chart3 = new Chart("Wykres wyjścia", "Rozłożenie godziny wyjścia z domu w czasie", "Godzina", "Wystąpienia", tab);
	    chart3.pack();
	    chart3.setVisible(true);
	    
	    //--------godzinaWyjsciaZPracy-------------
		for (int i = 0; i < personList.size(); i ++)
		{
			tab[i] = personList.get(i).getGoingBack();
		}
	    Chart chart4 = new Chart("Wykres powrotu", "Rozłożenie godziny wyjścia z pracy w czasie", "Godzina", "Wystąpienia", tab);
	    chart4.pack();
	    chart4.setVisible(true);
	}
}
