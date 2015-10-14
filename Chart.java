import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Chart extends JFrame 
{
   private static final long serialVersionUID = 1L;
   String[] tab;
   final String[] tabZones = {"A", "B", "C", "D", "S"};

   public Chart(String applicationTitle, String chartTitle, String xLabel, String yLabel, String[] _tab) 
   {
	   super(applicationTitle);
	   tab = _tab;

       JFreeChart pieChart = ChartFactory.createBarChart(chartTitle, xLabel, yLabel, createDataset(), 
    		   PlotOrientation.VERTICAL, true, true, false);
       ChartPanel chartPanel = new ChartPanel(pieChart);
       chartPanel.setPreferredSize(new java.awt.Dimension(1000, 500));
       setContentPane(chartPanel);
	}
  
    private CategoryDataset createDataset() 
	{
	    final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    if (tab[0].length() == 1) // czyli napis jednoliterowy, czyli residence/destination
	    {
	    	for (int i = 0; i < 5; i++)
	    	{
		    	dataset.addValue(howMany(tabZones[i]), "jakWywalićToPole?", tabZones[i]);
	    	}
	    }
	    else if (tab[0].contains(":"))
	    {
	    	for (Integer i = 0; i < 24; i++)
	    	{
		    	dataset.addValue(howMany(i.toString()), "jakWywalićToPole?", i.toString());
	    	}
	    }
	    else
	    {
		    for (Integer i = 13; i < 91; i++)
		    {
		    	dataset.addValue(howMany(i.toString()), "jakWywalićToPole?", i.toString());
		    } 
	    }
	    return dataset;
	}
    
    public int howMany(String pattern)
    {
    	int k = 0;
    	for (int i = 0; i < tab.length; i++)
    	{
    		try
    		{
	    		if (Util.getMinutes(tab[i]) != null)
	    		{
	    			tab[i] = Util.getHour(tab[i]);
	    		}
    		}
    		catch (Exception e) { }
    		if (tab[i].equals(pattern))
    		{
    			k++;
    		}
    	}
    	return k;
    }
}