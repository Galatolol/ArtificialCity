import java.util.Collection;
import java.util.LinkedList;


public class Util
{
	public static String getHour(String time)
	{
		String[] parts = time.split(":");
		return parts[0];
	}
	
	public static String getMinutes(String time)
	{
		String[] parts = time.split(":");
		return parts[1];
	}
	
	public static String setHour(String time, String hour)
	{
		String minutes = getMinutes(time);
		return hour + ":" + minutes;
	}
	
	public static String setMinutes(String time, String minutes)
	{
		String hour = getHour(time);
		return hour + ":" + minutes;
	}
	
	public static String substractTime(String time, int minutesToSubstr)
	{
		String[] parts = time.split(":");
		Integer hours = Integer.parseInt(parts[0]);
		Integer minutes = Integer.parseInt(parts[1]);
		hours = hours - minutesToSubstr / 60;
		minutesToSubstr = minutesToSubstr % 60;
		if (minutesToSubstr > minutes)
		{
			hours = hours - 1;
			minutesToSubstr = minutesToSubstr - minutes;
			minutes = 60 - minutesToSubstr;
		}
		else
		{
			minutes = minutes - minutesToSubstr;
		}
		return hours.toString() + ":" + minutes.toString();
	}
	
	public static String addTime(String time, int minutesToAdd)
	{
		String[] parts = time.split(":");
		Integer hours = Integer.parseInt(parts[0]);
		Integer minutes = Integer.parseInt(parts[1]);
		hours = hours + minutesToAdd / 60;
		minutesToAdd = minutesToAdd % 60;
		if (minutesToAdd > 60 - minutes)
		{
			hours = hours + 1;;
			minutesToAdd = (minutes + minutesToAdd) % 60;
			minutes = minutesToAdd;
		}
		else
		{
			minutes = minutes + minutesToAdd;
		}
		hours = hours % 24;
		return hours.toString() + ":" + minutes.toString();
	}
	
	//-------------------------------------------------------
	
	public static Lane[] createStreets(Graph myGraph)
	{
	    E edge = myGraph.getEdge(16, 13);
	    
	    for(int i = 0; i < 3; i++)
	    {
	    	edge.street[i].speedLimit = 3;
	    }
		for(int k = 0; k < edge.street[2].cellList.length - 1; k++)
		{
			edge.street[2].cellList[k].setForbidden(true); //buspas
		}
 
		Lane[] street16_13 = new Lane[3];
		for (int i = 0; i < 3; i++)
		{
			street16_13[i] = edge.street[i];
		}
		
	    //----------------------
		
	    edge = myGraph.getEdge(13, 5);
	    
	    for(int i = 0; i < 3; i++)
	    {
	    	edge.street[i].speedLimit = 4;
	    }
		for(int k = 0; k < edge.street[2].cellList.length - 1; k++)
		{
			edge.street[2].cellList[k].setForbidden(true); //buspas
		}
 
		Lane[] street13_5 = new Lane[3];
		for (int i = 0; i < 3; i++)
		{
			street13_5[i] = edge.street[i];
		}
		
		//-------------------
		
	    edge = myGraph.getEdge(13, 12);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street13_12 = new Lane[1];
		street13_12[0] = edge.street[0];
		
		//---------------------
	   
	    edge = myGraph.getEdge(12, 32);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street12_32 = new Lane[1];
		street12_32[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(12, 11);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street12_11 = new Lane[1];
		street12_11[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(11, 10);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street11_10 = new Lane[1];
		street11_10[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(10, 9);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street10_9 = new Lane[1];
		street10_9[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(9, 36);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street9_36 = new Lane[1];
		street9_36[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(36, 35);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street36_35 = new Lane[1];
		street36_35[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(36, 37);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street36_37 = new Lane[1];
		street36_37[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(35, 38);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street35_38 = new Lane[1];
		street35_38[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(33, 32);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street33_32 = new Lane[1];
		street33_32[0] = edge.street[0];
		   
		//----------------------------------------
		
	    edge = myGraph.getEdge(33, 34);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street33_34 = new Lane[1];
		street33_34[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(35, 33);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street35_33 = new Lane[1];
		street35_33[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(9, 8);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street9_8 = new Lane[1];
		street9_8[0] = edge.street[0];
		
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(8, 7);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street8_7 = new Lane[1];
		street8_7[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(8, 39);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street8_39 = new Lane[1];
		street8_39[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(6, 8);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street6_8 = new Lane[1];
		street6_8[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(7, 8);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street7_8= new Lane[1];
		street7_8[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(8, 9);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street8_9 = new Lane[1];
		street8_9[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(9, 10);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street9_10 = new Lane[1];
		street9_10[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(10, 11);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street10_11 = new Lane[1];
		street10_11[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(11, 12);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street11_12 = new Lane[1];
		street11_12[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(37, 36);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street37_36 = new Lane[1];
		street37_36[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(32, 30);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street32_30 = new Lane[1];
		street32_30[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(30, 29);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street30_29 = new Lane[1];
		street30_29[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(32, 40);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street32_40 = new Lane[1];
		street32_40[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(30, 31);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street30_31 = new Lane[1];
		street30_31[0] = edge.street[0];
		
		//-----------------------------------------
		   
	    edge = myGraph.getEdge(31, 30);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street31_30 = new Lane[1];
		street31_30[0] = edge.street[0];
		
		//----------------------------------------
		
	    edge = myGraph.getEdge(29, 28);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street29_28 = new Lane[1];
		street29_28[0] = edge.street[0];
		
		//----------------------------------------
		
	    edge = myGraph.getEdge(29, 41);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street29_41 = new Lane[1];
		street29_41[0] = edge.street[0];
		
		//----------------------------------------
		
	    edge = myGraph.getEdge(41, 40);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street41_40 = new Lane[1];
		street41_40[0] = edge.street[0];
		
		//----------------------------------------
		
	    edge = myGraph.getEdge(41, 42);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street41_42 = new Lane[1];
		street41_42[0] = edge.street[0];
		
		//----------------------------------------
		
	    edge = myGraph.getEdge(40, 38);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street40_38 = new Lane[1];
		street40_38[0] = edge.street[0];
		
		//----------------------------------------
		
	    edge = myGraph.getEdge(40, 32);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street40_32 = new Lane[1];
		street40_32[0] = edge.street[0];
		
		//----------------------------------------
		
	    edge = myGraph.getEdge(38, 39);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street38_39 = new Lane[1];
		street38_39[0] = edge.street[0];
		
		//----------------------------------------
		
	    edge = myGraph.getEdge(38, 35);
	    
	    for(int i = 0; i < 1; i++)
	    {
	    	edge.street[i].speedLimit = 2;
	    }
 
		Lane[] street38_35 = new Lane[1];
		street38_35[0] = edge.street[0];
		
		//----------------------------------------
		
		//-----------------------------------------------
		
		for (int i = 0; i < 3; i++)
		{
			street16_13[i].forward = street13_5;
			street16_13[i].right = street13_12;
		}
		
		street13_12[0].forward = street12_32;
		street13_12[0].left = street12_11;
		
		street12_11[0].forward = street11_10;
		
		street11_10[0].forward = street10_9;
		
		street10_9[0].forward = street9_36;
		street10_9[0].left = street9_8;
		
		street9_36[0].forward = street36_35;
		street9_36[0].right = street36_37;
		
		street36_35[0].forward = street35_38;
		street36_35[0].right = street35_33;
		
		street35_33[0].forward = street33_32;
		street35_33[0].right = street33_34;

		street6_8[0].forward = street8_39;
		street6_8[0].left = street8_7;
		
		street9_8[0].right = street8_39;
		street9_8[0].forward = street8_7;
		
		street7_8[0].forward = street8_9;
	    street7_8[0].left = street8_39;
	    
	    street8_9[0].right = street9_10;
	    street8_9[0].left = street9_36;
	    
	    street9_10[0].forward = street10_11;
	    
	    street10_11[0].forward = street11_12;
	    
	    street11_12[0].left = street12_32;
	    
	    street37_36[0].right = street36_35;
	    
	    street12_32[0].right = street32_30;
	    street12_32[0].forward = street32_40;
	    
	    street32_30[0].forward = street30_29;
	    street32_30[0].right = street30_31;
	    
	    street31_30[0].right = street30_29;
	    
	    street30_29[0].right = street29_28;
	    street30_29[0].left = street29_41;
	    
	    street29_41[0].left = street41_40;
	    street29_41[0].right = street41_42;
	    
	    street41_40[0].forward = street40_38;
	    street41_40[0].left = street40_32;
	    
	    street40_38[0].forward = street38_39;
	    street40_38[0].left = street38_35;
	    
		
		
		return street16_13;
	}
}
