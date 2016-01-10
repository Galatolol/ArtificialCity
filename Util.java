import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Util {
	private static ArrayList<Lane[]> streetList = new ArrayList<Lane[]>();
	private static ArrayList<Lane[]> pedestriansStreetList = new ArrayList<Lane[]>();
	public static PedestriansGraph pGraph;

	public static String getHour(String time) {
		String[] parts = time.split(":");
		return parts[0];
	}

	public static String getMinutes(String time) {
		String[] parts = time.split(":");
		return parts[1];
	}

	public static String setHour(String time, String hour) {
		String minutes = getMinutes(time);
		return hour + ":" + minutes;
	}

	public static String setMinutes(String time, String minutes) {
		String hour = getHour(time);
		return hour + ":" + minutes;
	}

	public static String substractTime(String time, int minutesToSubstr) {
		String[] parts = time.split(":");
		Integer hours = Integer.parseInt(parts[0]);
		Integer minutes = Integer.parseInt(parts[1]);
		hours = hours - minutesToSubstr / 60;
		minutesToSubstr = minutesToSubstr % 60;
		if (minutesToSubstr > minutes) {
			hours = hours - 1;
			minutesToSubstr = minutesToSubstr - minutes;
			minutes = 60 - minutesToSubstr;
		} else {
			minutes = minutes - minutesToSubstr;
		}
		return hours.toString() + ":" + minutes.toString();
	}

	public static String addTime(String time, int minutesToAdd) {
		String[] parts = time.split(":");
		Integer hours = Integer.parseInt(parts[0]);
		Integer minutes = Integer.parseInt(parts[1]);
		hours = hours + minutesToAdd / 60;
		minutesToAdd = minutesToAdd % 60;
		if (minutesToAdd > 60 - minutes) {
			hours = hours + 1;
			;
			minutesToAdd = (minutes + minutesToAdd) % 60;
			minutes = minutesToAdd;
		} else {
			minutes = minutes + minutesToAdd;
		}
		hours = hours % 24;
		return hours.toString() + ":" + minutes.toString();
	}

	// ------------------------------------------------------

	public static boolean isBusStop(Lane lane, int cellNr, PublicTransport veh) {
		if (lane.begin.toString().equals("13")
				&& lane.end.toString().equals("5") && cellNr == 6) {
			ContentPanel.stopArray[61] = veh.getDirection();
			return true;
		} else if (lane.begin.toString().equals("14")
				&& lane.end.toString().equals("15") && cellNr == 6) {
			ContentPanel.stopArray[5] = veh.getDirection();
			return true;
		} else if (lane.begin.toString().equals("22")
				&& lane.end.toString().equals("21") && cellNr == 17) {
			ContentPanel.stopArray[39] = veh.getDirection();
			return true;
		}
		return false;
	}

	// ------------------------------------------------------

	public static boolean isTramStop(Lane lane, int cellNr, PublicTransport veh) {
		if (lane.begin.toString().equals("3")
				&& lane.end.toString().equals("14") && cellNr == 17) {
			ContentPanel.stopArray[4] = veh.getDirection();
			return true;
		} else if (lane.begin.toString().equals("12")
				&& lane.end.toString().equals("13") && cellNr == 16) {
			ContentPanel.stopArray[49] = veh.getDirection();
			return true;
		} else if (lane.begin.toString().equals("32")
				&& lane.end.toString().equals("40") && cellNr == 14) {
			ContentPanel.stopArray[51] = veh.getDirection();
			return true;
		} else if (lane.begin.toString().equals("54")
				&& lane.end.toString().equals("55") && cellNr == 36) {
			ContentPanel.stopArray[56] = veh.getDirection();
			return true;
		} else if (lane.begin.toString().equals("56")
				&& lane.end.toString().equals("55") && cellNr == 30) {
			ContentPanel.stopArray[54] = veh.getDirection();
			return true;
		} else if (lane.begin.toString().equals("58")
				&& lane.end.toString().equals("55") && cellNr == 20) {
			ContentPanel.stopArray[58] = veh.getDirection();
			return true;
		} else if (lane.begin.toString().equals("53")
				&& lane.end.toString().equals("40") && cellNr == 50) {
			ContentPanel.stopArray[50] = veh.getDirection();
			return true;
		}
		return false;
	}
	
	//-------------------------------------------------------------
	
	public static PublicTransport spawnPubTran(String time)
	{
		String[] l8_1 = {"05:12", "05:43", "12:03", "8", "1"};
		
		for (int i = 0; i < l8_1.length - 2; i++)
		{
			if (time.equals(l8_1[i]))
			{
				System.out.println("jest");
				return Generator.generatePubTran(Integer.parseInt(l8_1[l8_1.length - 2]), Integer.parseInt(l8_1[l8_1.length - 1]));
			}
		}
		return null;
	}
	
	//--------------------------------------------------------------
	
	public static String convertTimerValueToTime(double timerValue)
	{
		int timVa = (int) (timerValue / 5);
		int min = (timVa * 2) / 60;
		int sec = (timVa * 2) % 60;
		int hrs = min/60;
		min = min % 60;
		hrs = (12 + hrs) % 24;
		
		String hrs1 = String.format("%02d", hrs);
		String min1 = String.format("%02d", min);
		String sec1 = String.format("%02d", sec);
		return hrs1 + ":" + min1 + ":" + sec1;
	}
	
	//----------------------------------------------------------------
	
	public static boolean isStop(V vertex, String dest)
	{
		if (dest.equals("A") && (vertex.id == 39 || vertex.id == 61 || vertex.id == 49 || vertex.id == 58 || vertex.id == 50 || vertex.id == 54))
		{
			return true;
		}
		else if (dest.equals("B") && (vertex.id == 4 || vertex.id == 51 || vertex.id == 56 || vertex.id == 54))
		{
			return true;
		}
		else if (dest.equals("C") && (vertex.id == 4 || vertex.id == 51 || vertex.id == 56 || vertex.id == 58 || vertex.id == 5 || vertex.id == 39))
		{
			return true;
		}
		else if (dest.equals("D") && vertex.id == 5)
		{
			return true;
		}
		return false;
	}
	
	//--------------------------------------------------------
	
	public static void createPedestriansStreets(PedestriansGraph pGraph)
	{
		E edge = pGraph.getEdge(6, 7);  
		Lane[] street6_7 = new Lane[1]; 
		street6_7[0] = edge.street[0];
		street6_7[0].clDir = false;
		pedestriansStreetList.add(street6_7); 
		
		Lane[] street7_6 = new Lane[1]; 
		Lane l = new Lane(edge.street[0]);
		street7_6[0] = l;
		street7_6[0].clDir = true;
		pedestriansStreetList.add(street7_6); 

		// -------------------------------------------

		edge = pGraph.getEdge(7, 10);
		Lane[] street7_10 = new Lane[1];
		street7_10[0] = edge.street[0];
		street7_10[0].clDir = true;
		pedestriansStreetList.add(street7_10);
		
		edge = pGraph.getEdge(10, 7);  
		Lane[] street10_7 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street10_7[0] = l;
		street10_7[0].clDir = false;
		pedestriansStreetList.add(street10_7); 
		
		// -------------------------------------------

		edge = pGraph.getEdge(7, 8);
		Lane[] street7_8 = new Lane[1];
		street7_8[0] = edge.street[0];
		street7_8[0].clDir = true;
		pedestriansStreetList.add(street7_8);
		
		edge = pGraph.getEdge(8, 7);  
		Lane[] street8_7 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street8_7[0] = l;
		street8_7[0].clDir = false;
		pedestriansStreetList.add(street8_7); 
		
		// -------------------------------------------

		edge = pGraph.getEdge(7, 2);
		Lane[] street7_2 = new Lane[1];
		street7_2[0] = edge.street[0];
		pedestriansStreetList.add(street7_2);
		
		edge = pGraph.getEdge(2, 7);  
		Lane[] street2_7 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street2_7[0] = l;
		street2_7[0].clDir = true;
		pedestriansStreetList.add(street2_7); 
		
		// -------------------------------------------

		edge = pGraph.getEdge(10, 11);
		Lane[] street10_11 = new Lane[1];
		street10_11[0] = edge.street[0];
		street10_11[0].clDir = true;
		pedestriansStreetList.add(street10_11);
		
		edge = pGraph.getEdge(11, 10);  
		Lane[] street11_10 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street11_10[0] = l;
		street11_10[0].clDir = false;
		pedestriansStreetList.add(street11_10); 
		
		// -------------------------------------------

		edge = pGraph.getEdge(10, 8);
		Lane[] street10_8 = new Lane[1];
		street10_8[0] = edge.street[0];
		pedestriansStreetList.add(street10_8);
		
		edge = pGraph.getEdge(8, 10);  
		Lane[] street8_10 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street8_10[0] = l;
		street8_10[0].clDir = true;
		pedestriansStreetList.add(street8_10); 
		
		// -------------------------------------------

		edge = pGraph.getEdge(10, 13);
		Lane[] street10_13 = new Lane[1];
		street10_13[0] = edge.street[0];
		street10_13[0].clDir = true;
		pedestriansStreetList.add(street10_13);
		
		edge = pGraph.getEdge(13, 10);  
		Lane[] street13_10 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street13_10[0] = l;
		street13_10[0].clDir = false;
		pedestriansStreetList.add(street13_10); 
		
		// -------------------------------------------

		edge = pGraph.getEdge(8, 9);
		Lane[] street8_9 = new Lane[1];
		street8_9[0] = edge.street[0];
		street8_9[0].clDir = true;
		pedestriansStreetList.add(street8_9);
		
		edge = pGraph.getEdge(9, 8);  
		Lane[] street9_8 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street9_8[0] = l;
		street9_8[0].clDir = false;
		pedestriansStreetList.add(street9_8); 
		
		// -------------------------------------------

		edge = pGraph.getEdge(6, 5);
		Lane[] street6_5 = new Lane[1];
		street6_5[0] = edge.street[0];
		street6_5[0].clDir = true;
		pedestriansStreetList.add(street6_5);
		
		Lane[] street5_6 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street5_6[0] = l;
		street5_6[0].clDir = false;
		pedestriansStreetList.add(street5_6); 
		
		// -------------------------------------------

		edge = pGraph.getEdge(14, 6);
		Lane[] street14_6 = new Lane[1];
		street14_6[0] = edge.street[0];
		pedestriansStreetList.add(street14_6);
		
		edge = pGraph.getEdge(6, 14);  
		Lane[] street6_14 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street6_14[0] = l;
		street6_14[0].clDir = true;
		pedestriansStreetList.add(street6_14); 
		
		// -------------------------------------------

		edge = pGraph.getEdge(8, 1);
		Lane[] street8_1 = new Lane[1];
		street8_1[0] = edge.street[0];
		pedestriansStreetList.add(street8_1);
		
		edge = pGraph.getEdge(1, 8);  
		Lane[] street1_8 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street1_8[0] = l;
		street1_8[0].clDir = true;
		pedestriansStreetList.add(street1_8); 
		
		// -------------------------------------------

		edge = pGraph.getEdge(5, 41);
		Lane[] street5_41 = new Lane[1];
		street5_41[0] = edge.street[0];
		street5_41[0].clDir = false;
		pedestriansStreetList.add(street5_41);
		 
		Lane[] street41_5 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street41_5[0] = l;
		street41_5[0].clDir = true;
		pedestriansStreetList.add(street41_5); 
		
		// -------------------------------------------

		edge = pGraph.getEdge(41, 48);
		Lane[] street41_48 = new Lane[1];
		street41_48[0] = edge.street[0];
		street41_48[0].clDir = false;
		pedestriansStreetList.add(street41_48);
		 
		Lane[] street48_41 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street48_41[0] = l;
		street48_41[0].clDir = true;
		pedestriansStreetList.add(street48_41); 
		
		// -------------------------------------------

		edge = pGraph.getEdge(41, 4);
		Lane[] street41_4 = new Lane[1];
		street41_4[0] = edge.street[0];
		street41_4[0].clDir = false;
		pedestriansStreetList.add(street41_4);
		 
		Lane[] street4_41 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street4_41[0] = l;
		street4_41[0].clDir = true;
		pedestriansStreetList.add(street4_41); 
		
		// -------------------------------------------

		edge = pGraph.getEdge(4, 42);
		Lane[] street4_42 = new Lane[1];
		street4_42[0] = edge.street[0];
		street4_42[0].clDir = false;
		pedestriansStreetList.add(street4_42);
		 
		Lane[] street42_4 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street42_4[0] = l;
		street42_4[0].clDir = true;
		pedestriansStreetList.add(street42_4); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(42, 3);
		Lane[] street42_3 = new Lane[1];
		street42_3[0] = edge.street[0];
		street42_3[0].clDir = false;
		pedestriansStreetList.add(street42_3);
		 
		Lane[] street3_42 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street3_42[0] = l;
		street3_42[0].clDir = true;
		pedestriansStreetList.add(street3_42); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(3, 2);
		Lane[] street3_2 = new Lane[1];
		street3_2[0] = edge.street[0];
		street3_2[0].clDir = false;
		pedestriansStreetList.add(street3_2);
		 
		Lane[] street2_3 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street2_3[0] = l;
		street2_3[0].clDir = true;
		pedestriansStreetList.add(street2_3); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(42, 43);
		Lane[] street42_43 = new Lane[1];
		street42_43[0] = edge.street[0];
		street42_43[0].clDir = true;
		pedestriansStreetList.add(street42_43);
		 
		Lane[] street43_42 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street43_42[0] = l;
		street43_42[0].clDir = false;
		pedestriansStreetList.add(street43_42); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(4, 44);
		Lane[] street4_44 = new Lane[1];
		street4_44[0] = edge.street[0];
		street4_44[0].clDir = true;
		pedestriansStreetList.add(street4_44);
		 
		Lane[] street44_4 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street44_4[0] = l;
		street44_4[0].clDir = false;
		pedestriansStreetList.add(street44_4); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(4, 2);
		Lane[] street4_2 = new Lane[1];
		street4_2[0] = edge.street[0];
		street4_2[0].clDir = false;
		pedestriansStreetList.add(street4_2);
		 
		Lane[] street2_4 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street2_4[0] = l;
		street2_4[0].clDir = true;
		pedestriansStreetList.add(street2_4); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(2, 1);
		Lane[] street2_1 = new Lane[1];
		street2_1[0] = edge.street[0];
		street2_1[0].clDir = false;
		pedestriansStreetList.add(street2_1);
		 
		Lane[] street1_2 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street1_2[0] = l;
		street1_2[0].clDir = true;
		pedestriansStreetList.add(street1_2); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(11, 12);
		Lane[] street11_12 = new Lane[1];
		street11_12[0] = edge.street[0];
		street11_12[0].clDir = true;
		pedestriansStreetList.add(street11_12);
		 
		Lane[] street12_11 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street12_11[0] = l;
		street12_11[0].clDir = false;
		pedestriansStreetList.add(street12_11); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(13, 15);
		Lane[] street13_15 = new Lane[1];
		street13_15[0] = edge.street[0];
		street13_15[0].clDir = false;
		pedestriansStreetList.add(street13_15);
		 
		Lane[] street15_13 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street15_13[0] = l;
		street15_13[0].clDir = true;
		pedestriansStreetList.add(street15_13); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(13, 14);
		Lane[] street13_14 = new Lane[1];
		street13_14[0] = edge.street[0];
		street13_14[0].clDir = true;
		pedestriansStreetList.add(street13_14);
		 
		Lane[] street14_13 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street14_13[0] = l;
		street14_13[0].clDir = false;
		pedestriansStreetList.add(street14_13); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(12, 18);
		Lane[] street12_18 = new Lane[1];
		street12_18[0] = edge.street[0];
		street12_18[0].clDir = false;
		pedestriansStreetList.add(street12_18);
		 
		Lane[] street18_12 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street18_12[0] = l;
		street18_12[0].clDir = true;
		pedestriansStreetList.add(street18_12); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(12, 13);
		Lane[] street12_13 = new Lane[1];
		street12_13[0] = edge.street[0];
		street12_13[0].clDir = false;
		pedestriansStreetList.add(street12_13);
		 
		Lane[] street13_12 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street13_12[0] = l;
		street13_12[0].clDir = true;
		pedestriansStreetList.add(street13_12); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(14, 15);
		Lane[] street14_15 = new Lane[1];
		street14_15[0] = edge.street[0];
		street14_15[0].clDir = true;
		pedestriansStreetList.add(street14_15);
		 
		Lane[] street15_14 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street15_14[0] = l;
		street15_14[0].clDir = false;
		pedestriansStreetList.add(street15_14); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(38, 39);
		Lane[] street38_39 = new Lane[1];
		street38_39[0] = edge.street[0];
		street38_39[0].clDir = true;
		pedestriansStreetList.add(street38_39);
		 
		Lane[] street39_38 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street39_38[0] = l;
		street39_38[0].clDir = false;
		pedestriansStreetList.add(street39_38); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(18, 20);
		Lane[] street18_20 = new Lane[1];
		street18_20[0] = edge.street[0];
		street18_20[0].clDir = true;
		pedestriansStreetList.add(street18_20);
		 
		Lane[] street20_18 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street20_18[0] = l;
		street20_18[0].clDir = false;
		pedestriansStreetList.add(street20_18); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(18, 19);
		Lane[] street18_19 = new Lane[1];
		street18_19[0] = edge.street[0];
		street18_19[0].clDir = true;
		pedestriansStreetList.add(street18_19);
		 
		Lane[] street19_18 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street19_18[0] = l;
		street19_18[0].clDir = false;
		pedestriansStreetList.add(street19_18);
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(18, 17);
		Lane[] street18_17 = new Lane[1];
		street18_17[0] = edge.street[0];
		street18_17[0].clDir = false;
		pedestriansStreetList.add(street18_17);
		 
		Lane[] street17_18 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street17_18[0] = l;
		street17_18[0].clDir = true;
		pedestriansStreetList.add(street17_18); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(20, 38);
		Lane[] street20_38 = new Lane[1];
		street20_38[0] = edge.street[0];
		street20_38[0].clDir = false;
		pedestriansStreetList.add(street20_38);
		 
		Lane[] street38_20 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street38_20[0] = l;
		street38_20[0].clDir = true;
		pedestriansStreetList.add(street38_20); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(20, 16);
		Lane[] street20_16 = new Lane[1];
		street20_16[0] = edge.street[0];
		street20_16[0].clDir = false;
		pedestriansStreetList.add(street20_16);
		 
		Lane[] street16_20 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street16_20[0] = l;
		street16_20[0].clDir = true;
		pedestriansStreetList.add(street16_20); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(38, 19);
		Lane[] street38_19 = new Lane[1];
		street38_19[0] = edge.street[0];
		street38_19[0].clDir = false;
		pedestriansStreetList.add(street38_19);
		 
		Lane[] street19_38 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street19_38[0] = l;
		street19_38[0].clDir = true;
		pedestriansStreetList.add(street19_38); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(16, 15);
		Lane[] street16_15 = new Lane[1];
		street16_15[0] = edge.street[0];
		street16_15[0].clDir = false;
		pedestriansStreetList.add(street16_15);
		 
		Lane[] street15_16 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street15_16[0] = l;
		street15_16[0].clDir = true;
		pedestriansStreetList.add(street15_16); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(17, 16);
		Lane[] street17_16 = new Lane[1];
		street17_16[0] = edge.street[0];
		street17_16[0].clDir = false;
		pedestriansStreetList.add(street17_16);
		 
		Lane[] street16_17 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street16_17[0] = l;
		street16_17[0].clDir = true;
		pedestriansStreetList.add(street16_17); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(17, 15);
		Lane[] street17_15 = new Lane[1];
		street17_15[0] = edge.street[0];
		street17_15[0].clDir = false;
		pedestriansStreetList.add(street17_15);
		 
		Lane[] street15_17 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street15_17[0] = l;
		street15_17[0].clDir = true;
		pedestriansStreetList.add(street15_17);
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(16, 40);
		Lane[] street16_40 = new Lane[1];
		street16_40[0] = edge.street[0];
		street16_40[0].clDir = true;
		pedestriansStreetList.add(street16_40);
		 
		Lane[] street40_16 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street40_16[0] = l;
		street40_16[0].clDir = false;
		pedestriansStreetList.add(street40_16); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(39, 40);
		Lane[] street39_40 = new Lane[1];
		street39_40[0] = edge.street[0];
		street39_40[0].clDir = true;
		pedestriansStreetList.add(street39_40);
		 
		Lane[] street40_39 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street40_39[0] = l;
		street40_39[0].clDir = false;
		pedestriansStreetList.add(street40_39); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(43, 44);
		Lane[] street43_44 = new Lane[1];
		street43_44[0] = edge.street[0];
		street43_44[0].clDir = true;
		pedestriansStreetList.add(street43_44);
		 
		Lane[] street44_43 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street44_43[0] = l;
		street44_43[0].clDir = false;
		pedestriansStreetList.add(street44_43); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(44, 45);
		Lane[] street44_45 = new Lane[1];
		street44_45[0] = edge.street[0];
		street44_45[0].clDir = true;
		pedestriansStreetList.add(street44_45);
		 
		Lane[] street45_44 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street45_44[0] = l;
		street45_44[0].clDir = false;
		pedestriansStreetList.add(street45_44); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(45, 46);
		Lane[] street45_46 = new Lane[1];
		street45_46[0] = edge.street[0];
		street45_46[0].clDir = true;
		pedestriansStreetList.add(street45_46);
		 
		Lane[] street46_45 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street46_45[0] = l;
		street46_45[0].clDir = false;
		pedestriansStreetList.add(street46_45); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(46, 49);
		Lane[] street46_49 = new Lane[1];
		street46_49[0] = edge.street[0];
		street46_49[0].clDir = true;
		pedestriansStreetList.add(street46_49);
		 
		Lane[] street49_46 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street49_46[0] = l;
		street49_46[0].clDir = false;
		pedestriansStreetList.add(street49_46); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(46, 47);
		Lane[] street46_47 = new Lane[1];
		street46_47[0] = edge.street[0];
		street46_47[0].clDir = true;
		pedestriansStreetList.add(street46_47);
		 
		Lane[] street47_46 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street47_46[0] = l;
		street47_46[0].clDir = false;
		pedestriansStreetList.add(street47_46); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(47, 48);
		Lane[] street47_48 = new Lane[1];
		street47_48[0] = edge.street[0];
		street47_48[0].clDir = true;
		pedestriansStreetList.add(street47_48);
		 
		Lane[] street48_47 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street48_47[0] = l;
		street48_47[0].clDir = false;
		pedestriansStreetList.add(street48_47); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(47, 51);
		Lane[] street47_51 = new Lane[1];
		street47_51[0] = edge.street[0];
		street47_51[0].clDir = false;
		pedestriansStreetList.add(street47_51);
		 
		Lane[] street51_47 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street51_47[0] = l;
		street51_47[0].clDir = true;
		pedestriansStreetList.add(street51_47); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(51, 53);
		Lane[] street51_53 = new Lane[1];
		street51_53[0] = edge.street[0];
		street51_53[0].clDir = false;
		pedestriansStreetList.add(street51_53);
		 
		Lane[] street53_51 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street53_51[0] = l;
		street53_51[0].clDir = true;
		pedestriansStreetList.add(street53_51); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(51, 52);
		Lane[] street51_52 = new Lane[1];
		street51_52[0] = edge.street[0];
		street51_52[0].clDir = true;
		pedestriansStreetList.add(street51_52);
		 
		Lane[] street52_51 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street52_51[0] = l;
		street52_51[0].clDir = false;
		pedestriansStreetList.add(street52_51); 
		
		//------------------------------------------------
		
	 	edge = pGraph.getEdge(46, 61);
		Lane[] street46_61 = new Lane[1];
		street46_61[0] = edge.street[0];
		street46_61[0].clDir = true;
		pedestriansStreetList.add(street46_61);
		 
		Lane[] street61_46 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street61_46[0] = l;
		street61_46[0].clDir = false;
		pedestriansStreetList.add(street61_46); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(50, 52);
		Lane[] street50_52 = new Lane[1];
		street50_52[0] = edge.street[0];
		street50_52[0].clDir = false;
		pedestriansStreetList.add(street50_52);
		 
		Lane[] street52_50 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street52_50[0] = l;
		street52_50[0].clDir = true;
		pedestriansStreetList.add(street52_50); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(49, 52);
		Lane[] street49_52 = new Lane[1];
		street49_52[0] = edge.street[0];
		street49_52[0].clDir = true;
		pedestriansStreetList.add(street49_52);
		 
		Lane[] street52_49 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street52_49[0] = l;
		street52_49[0].clDir = false;
		pedestriansStreetList.add(street52_49); 
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(62, 53);
		Lane[] street62_53 = new Lane[1];
		street62_53[0] = edge.street[0];
		street62_53[0].clDir = false;
		pedestriansStreetList.add(street62_53);
		 
		Lane[] street53_62 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street53_62[0] = l;
		street53_62[0].clDir = true;
		pedestriansStreetList.add(street53_62);  
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(56, 55);
		Lane[] street56_55 = new Lane[1];
		street56_55[0] = edge.street[0];
		street56_55[0].clDir = true;
		pedestriansStreetList.add(street56_55);
		 
		Lane[] street55_56 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street55_56[0] = l;
		street55_56[0].clDir = false;
		pedestriansStreetList.add(street55_56); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(53, 50);
		Lane[] street53_50 = new Lane[1];
		street53_50[0] = edge.street[0];
		street53_50[0].clDir = false;
		pedestriansStreetList.add(street53_50);
		 
		Lane[] street50_53 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street50_53[0] = l;
		street50_53[0].clDir = true;
		pedestriansStreetList.add(street50_53); 
		
		//------------------------------------------------
		
		edge = pGraph.getEdge(56, 57);
		Lane[] street56_57 = new Lane[1];
		street56_57[0] = edge.street[0];
		street56_57[0].clDir = false;
		pedestriansStreetList.add(street56_57);
		 
		Lane[] street57_56 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street57_56[0] = l;
		street57_56[0].clDir = true;
		pedestriansStreetList.add(street57_56);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(50, 63);
		Lane[] street63_50 = new Lane[1];
		street63_50[0] = edge.street[0];
		street63_50[0].clDir = false;
		pedestriansStreetList.add(street63_50);
		 
		Lane[] street50_63 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street50_63[0] = l;
		street50_63[0].clDir = true;
		pedestriansStreetList.add(street50_63);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(57, 58);
		Lane[] street57_58 = new Lane[1];
		street57_58[0] = edge.street[0];
		street57_58[0].clDir = false;
		pedestriansStreetList.add(street57_58);
		 
		Lane[] street58_57 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street58_57[0] = l;
		street58_57[0].clDir = true;
		pedestriansStreetList.add(street58_57);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(62, 56);
		Lane[] street62_56 = new Lane[1];
		street62_56[0] = edge.street[0];
		street62_56[0].clDir = true;
		pedestriansStreetList.add(street62_56);
		 
		Lane[] street56_62 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street56_62[0] = l;
		street56_62[0].clDir = false;
		pedestriansStreetList.add(street56_62);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(63, 57);
		Lane[] street63_57 = new Lane[1];
		street63_57[0] = edge.street[0];
		street63_57[0].clDir = true;
		pedestriansStreetList.add(street63_57);
		 
		Lane[] street57_63 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street57_63[0] = l;
		street57_63[0].clDir = false;
		pedestriansStreetList.add(street57_63);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(55, 54);
		Lane[] street55_54 = new Lane[1];
		street55_54[0] = edge.street[0];
		street55_54[0].clDir = true;
		pedestriansStreetList.add(street55_54);
		 
		Lane[] street54_55 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street54_55[0] = l;
		street54_55[0].clDir = false;
		pedestriansStreetList.add(street54_55);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(60, 55);
		Lane[] street60_55 = new Lane[1];
		street60_55[0] = edge.street[0];
		street60_55[0].clDir = false;
		pedestriansStreetList.add(street60_55);
		 
		Lane[] street55_60 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street55_60[0] = l;
		street55_60[0].clDir = true;
		pedestriansStreetList.add(street55_60);  

		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(54, 23);
		Lane[] street23_54 = new Lane[1];
		street23_54[0] = edge.street[0];
		street23_54[0].clDir = false;
		pedestriansStreetList.add(street23_54);
		 
		Lane[] street54_23 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street54_23[0] = l;
		street54_23[0].clDir = true;
		pedestriansStreetList.add(street54_23); 
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(23, 37);
		Lane[] street23_37 = new Lane[1];
		street23_37[0] = edge.street[0];
		street23_37[0].clDir = true;
		pedestriansStreetList.add(street23_37);
		 
		Lane[] street37_23 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street37_23[0] = l;
		street37_23[0].clDir = false;
		pedestriansStreetList.add(street37_23);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(54, 28);
		Lane[] street28_54 = new Lane[1];
		street28_54[0] = edge.street[0];
		street28_54[0].clDir = true;
		pedestriansStreetList.add(street28_54);
		 
		Lane[] street54_28 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street54_28[0] = l;
		street54_28[0].clDir = false;
		pedestriansStreetList.add(street54_28); 
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(37, 36);
		Lane[] street37_36 = new Lane[1];
		street37_36[0] = edge.street[0];
		street37_36[0].clDir = false;
		pedestriansStreetList.add(street37_36);
		 
		Lane[] street36_37 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street36_37[0] = l;
		street36_37[0].clDir = true;
		pedestriansStreetList.add(street36_37);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(36, 35);
		Lane[] street36_35 = new Lane[1];
		street36_35[0] = edge.street[0];
		street36_35[0].clDir = false;
		pedestriansStreetList.add(street36_35);
		 
		Lane[] street35_36 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street35_36[0] = l;
		street35_36[0].clDir = true;
		pedestriansStreetList.add(street35_36); 
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(36, 28);
		Lane[] street36_28 = new Lane[1];
		street36_28[0] = edge.street[0];
		street36_28[0].clDir = false;
		pedestriansStreetList.add(street36_28);
		 
		Lane[] street28_36 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street28_36[0] = l;
		street28_36[0].clDir = true;
		pedestriansStreetList.add(street28_36);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(25, 59);
		Lane[] street25_59 = new Lane[1];
		street25_59[0] = edge.street[0];
		street25_59[0].clDir = true;
		pedestriansStreetList.add(street25_59);
		 
		Lane[] street59_25 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street59_25[0] = l;
		street59_25[0].clDir = false;
		pedestriansStreetList.add(street59_25); 
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(59, 58);
		Lane[] street59_58 = new Lane[1];
		street59_58[0] = edge.street[0];
		street59_58[0].clDir = true;
		pedestriansStreetList.add(street59_58);
		 
		Lane[] street58_59 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street58_59[0] = l;
		street58_59[0].clDir = false;
		pedestriansStreetList.add(street58_59);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(28, 59);
		Lane[] street28_59 = new Lane[1];
		street28_59[0] = edge.street[0];
		street28_59[0].clDir = false;
		pedestriansStreetList.add(street28_59);
		 
		Lane[] street59_28 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street59_28[0] = l;
		street59_28[0].clDir = true;
		pedestriansStreetList.add(street59_28); 
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(35, 34);
		Lane[] street35_34 = new Lane[1];
		street35_34[0] = edge.street[0];
		street35_34[0].clDir = false;
		pedestriansStreetList.add(street35_34);
		 
		Lane[] street34_35 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street34_35[0] = l;
		street34_35[0].clDir = true;
		pedestriansStreetList.add(street34_35);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(35, 24);
		Lane[] street35_24 = new Lane[1];
		street35_24[0] = edge.street[0];
		street35_24[0].clDir = true;
		pedestriansStreetList.add(street35_24);
		 
		Lane[] street24_35 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street24_35[0] = l;
		street24_35[0].clDir = false;
		pedestriansStreetList.add(street24_35); 
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(21, 22);
		Lane[] street21_22 = new Lane[1];
		street21_22[0] = edge.street[0];
		street21_22[0].clDir = true;
		pedestriansStreetList.add(street21_22);
		 
		Lane[] street22_21 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street22_21[0] = l;
		street22_21[0].clDir = false;
		pedestriansStreetList.add(street22_21);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(25, 27);
		Lane[] street25_27 = new Lane[1];
		street25_27[0] = edge.street[0];
		street25_27[0].clDir = false;
		pedestriansStreetList.add(street25_27);
		 
		Lane[] street27_25 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street27_25[0] = l;
		street27_25[0].clDir = true;
		pedestriansStreetList.add(street27_25); 
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(30, 22);
		Lane[] street30_22 = new Lane[1];
		street30_22[0] = edge.street[0];
		street30_22[0].clDir = false;
		pedestriansStreetList.add(street30_22);
		 
		Lane[] street22_30 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street22_30[0] = l;
		street22_30[0].clDir = true;
		pedestriansStreetList.add(street22_30);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(30, 33);
		Lane[] street30_33 = new Lane[1];
		street30_33[0] = edge.street[0];
		street30_33[0].clDir = false;
		pedestriansStreetList.add(street30_33);
		 
		Lane[] street33_30 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street33_30[0] = l;
		street33_30[0].clDir = true;
		pedestriansStreetList.add(street33_30); 
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(34, 33);
		Lane[] street34_33 = new Lane[1];
		street34_33[0] = edge.street[0];
		street34_33[0].clDir = true;
		pedestriansStreetList.add(street34_33);
		 
		Lane[] street33_34 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street33_34[0] = l;
		street33_34[0].clDir = false;
		pedestriansStreetList.add(street33_34);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(34, 32);
		Lane[] street34_32 = new Lane[1];
		street34_32[0] = edge.street[0];
		street34_32[0].clDir = true;
		pedestriansStreetList.add(street34_32);
		 
		Lane[] street32_34 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street32_34[0] = l;
		street32_34[0].clDir = false;
		pedestriansStreetList.add(street32_34); 
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(31, 32);
		Lane[] street31_32 = new Lane[1];
		street31_32[0] = edge.street[0];
		street31_32[0].clDir = true;
		pedestriansStreetList.add(street31_32);
		 
		Lane[] street32_31 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street32_31[0] = l;
		street32_31[0].clDir = false;
		pedestriansStreetList.add(street32_31);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(31, 29);
		Lane[] street31_29 = new Lane[1];
		street31_29[0] = edge.street[0];
		street31_29[0].clDir = true;
		pedestriansStreetList.add(street31_29);
		 
		Lane[] street29_31 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street29_31[0] = l;
		street29_31[0].clDir = false;
		pedestriansStreetList.add(street29_31); 
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(27, 30);
		Lane[] street30_27 = new Lane[1];
		street30_27[0] = edge.street[0];
		street30_27[0].clDir = false;
		pedestriansStreetList.add(street30_27);
		 
		Lane[] street27_30 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street27_30[0] = l;
		street27_30[0].clDir = true;
		pedestriansStreetList.add(street27_30);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(21, 27);
		Lane[] street21_27 = new Lane[1];
		street21_27[0] = edge.street[0];
		street21_27[0].clDir = true;
		pedestriansStreetList.add(street21_27);
		 
		Lane[] street27_21 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street27_21[0] = l;
		street27_21[0].clDir = false;
		pedestriansStreetList.add(street27_21); 
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(25, 33);
		Lane[] street33_25 = new Lane[1];
		street33_25[0] = edge.street[0];
		street33_25[0].clDir = false;
		pedestriansStreetList.add(street33_25);
		 
		Lane[] street25_33 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street25_33[0] = l;
		street25_33[0].clDir = true;
		pedestriansStreetList.add(street25_33);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(26, 32);
		Lane[] street26_32 = new Lane[1];
		street26_32[0] = edge.street[0];
		street26_32[0].clDir = false;
		pedestriansStreetList.add(street26_32);
		 
		Lane[] street32_26 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street32_26[0] = l;
		street32_26[0].clDir = true;
		pedestriansStreetList.add(street32_26); 
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(33, 32);
		Lane[] street33_32 = new Lane[1];
		street33_32[0] = edge.street[0];
		street33_32[0].clDir = true;
		pedestriansStreetList.add(street33_32);
		 
		Lane[] street32_33 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street32_33[0] = l;
		street32_33[0].clDir = false;
		pedestriansStreetList.add(street32_33);  
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(31, 30);
		Lane[] street31_30 = new Lane[1];
		street31_30[0] = edge.street[0];
		street31_30[0].clDir = false;
		pedestriansStreetList.add(street31_30);
		 
		Lane[] street30_31 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street30_31[0] = l;
		street30_31[0].clDir = true;
		pedestriansStreetList.add(street30_31); 
		
		//------------------------------------------------
		
	  	edge = pGraph.getEdge(34, 24);
		Lane[] street34_24 = new Lane[1];
		street34_24[0] = edge.street[0];
		street34_24[0].clDir = true;
		pedestriansStreetList.add(street34_24);
		 
		Lane[] street24_34 = new Lane[1]; 
		l = new Lane(edge.street[0]);
		street24_34[0] = l;
		street24_34[0].clDir = false;
		pedestriansStreetList.add(street24_34);  
		
		//------------------------------------------------

		
		
		

		
		
		Lane[] streetToNowhere = new Lane[1]; 
		l = new Lane(edge.street[0]);
		streetToNowhere[0] = l;
		streetToNowhere[0].setSpeedLimit(-1);
		
		//-----------------------------------------
		
		street6_7[0].forward = street7_8;
		street6_7[0].right = street7_2;
		street6_7[0].left = street7_10;
		street7_6[0].forward = street6_5;
		street7_6[0].left = street6_14;
		
		street7_10[0].forward = street10_11;
		street7_10[0].right = street10_8;
		street7_10[0].left = street10_13;
		street10_7[0].forward = street7_2;
		street10_7[0].right = street7_6;
		street10_7[0].left = street7_8;
		
		street7_8[0].forward = street8_9;
		street7_8[0].right = street8_1;
		street7_8[0].left = street8_10;
	    street8_7[0].forward = street7_6;
	    street8_7[0].left = street7_2;
		
		street8_9[0].forward = streetToNowhere;
		street9_8[0].forward = street8_7;
		street9_8[0].right = street8_10;
	    street9_8[0].left = street8_1;
	    
	    street7_6[0].forward = street6_5;
	    street7_6[0].right = street6_14;
	    street6_7[0].forward = street7_8;
	    street6_7[0].right = street7_2;
	    street6_7[0].left = street7_10;
	    
	    street6_5[0].forward = street5_41;
	    street5_6[0].forward = street6_7;
	    street5_6[0].left = street6_14;
	    
	    street5_41[0].forward = street41_4;
	    street5_41[0].right = street41_48;
	    street41_5[0].forward = street5_6;
	    
	    street41_4[0].forward = street4_42;
	    street41_4[0].right = street4_44;
	    street41_4[0].left = street4_2;
	    street4_41[0].forward = street41_5;
	    street4_41[0].left = street41_48;
	    
	    street4_42[0].forward = street42_3;
	    street42_4[0].left = street4_44;
	    street42_4[0].right = street4_2;
	    street42_4[0].forward = street4_41;
	    
	    street42_3[0].forward = street3_2;
	    street3_42[0].forward = street42_4;
	    street3_42[0].left = street42_43;
	    
	    street3_2[0].forward = street2_7;
	    street2_3[0].forward = street3_42;
	    
	    street2_7[0].forward = street7_10;
	    street2_7[0].right = street7_8;
	    street2_7[0].left = street7_6;
	    street7_2[0].forward = street2_3;
	    street7_2[0].right = street2_4;
	    street7_2[0].left = street2_1;
	    
	    street2_1[0].forward = street1_8;
	    street1_2[0].forward = street2_4;
	    street1_2[0].left = street2_3;
	    street1_2[0].right = street2_7;
	    
	    street1_8[0].forward = street8_10;
	    street1_8[0].right = street8_9;
	    street1_8[0].left = street8_7;
	    street8_1[0].forward = street1_2;
	    
	    street8_10[0].forward = street10_13;
	    street8_10[0].right = street10_11;
	    street8_10[0].left = street10_7;
	    street10_8[0].forward = street8_1;
	    street10_8[0].right = street8_7;
	    street10_8[0].left = street8_9;
	    
	    street10_13[0].forward = street13_15;
	    street10_13[0].right = street13_12;
	    street10_13[0].left = street13_14;
	    street13_10[0].forward = street10_8;
	    street13_10[0].right = street10_7;
	    street13_10[0].left = street10_11;
	    
	    street10_11[0].forward = street11_12;
	    street11_10[0].forward = street10_7;
	    street11_10[0].right = street10_13;
	    street11_10[0].left = street10_8;
	    
	    street11_12[0].forward = street12_18;
	    street11_12[0].left = street12_13;
	    street12_11[0].forward = street11_10;
	    
	    street6_14[0].forward = street14_13;
	    street6_14[0].left = street14_15;
	    street14_6[0].forward = street6_5;
	    street14_6[0].left = street6_7;
	    
	    street14_13[0].forward = street13_12;
	    street14_13[0].right = street13_10;
	    street14_13[0].left = street13_15;
	    street13_14[0].forward = street14_6;
	    street13_14[0].right = street14_15;
	    
	    street12_18[0].forward = street18_20;
	    street12_18[0].right = street18_19;
	    street12_18[0].left = street18_17;
	    street18_12[0].forward = street12_13;
	    street18_12[0].left = street12_11;
	    
	    street18_20[0].forward = street20_38;
	    street18_20[0].left = street20_16;
	    street20_18[0].forward = street18_20;
	    street20_18[0].right = street18_17;
	    street20_18[0].left = street18_19;
	    
	    street20_38[0].forward = street38_39;
	    street20_38[0].right = street38_19;	 
	    street38_20[0].forward = street20_16;
	    street38_20[0].left = street20_18;
	    
	    street38_19[0].forward = street19_18;
	    street19_38[0].forward = street38_39;
	    street19_38[0].forward = street38_20;
	    
	    street19_18[0].forward = street18_17;
	    street19_18[0].right = street18_20;
	    street19_18[0].left = street18_12;
	    street18_19[0].forward = street19_38;
	    
	    street20_16[0].forward = street16_15;
	    street20_16[0].right = street16_40;
	    street20_16[0].left = street16_17;
	    street16_20[0].forward = street20_38;
	    street16_20[0].right = street20_18;
	    
	    street16_17[0].forward = street17_15;
	    street16_17[0].left = street17_18;
	    street17_16[0].forward = street16_40;
	    street17_16[0].right = street16_20;
	    street17_16[0].left = street16_15;
	    
	    street16_15[0].forward = street15_13;
	    street16_15[0].right = street15_14;
	    street16_15[0].left = street15_17;
	    street15_16[0].forward = street16_20;
	    street15_16[0].right = street16_17;
	    street15_16[0].left = street16_40;
	    
	    street17_18[0].forward = street18_19;
	    street17_18[0].right = street18_12;
	    street17_18[0].left = street18_20;
	    street18_17[0].forward = street17_15;
	    street18_17[0].right = street17_16;
	    
	    street17_15[0].forward = street15_14;
	    street17_15[0].right = street15_16;
	    street17_15[0].left = street15_13;
	    street15_17[0].forward = street17_18;
	    street15_17[0].left = street17_16;
	    
	    street15_14[0].forward = street14_6;
	    street15_14[0].left = street15_13;
	    street14_15[0].forward = street15_17;
	    street14_15[0].right = street15_13;
	    street14_15[0].left = street15_16;
	    
	    street38_39[0].forward = street39_40;
	    street39_38[0].forward = street38_19;
	    street39_38[0].right = street38_20;
	    
	    street39_40[0].forward = street40_16;
	    street40_39[0].forward = street39_38;
	    
	    street40_16[0].forward = street16_17;
	    street40_16[0].right = street16_15;
	    street40_16[0].left = street16_20;
	    street16_40[0].forward = street40_39;
	    
	    street42_43[0].forward = street43_44;
	    street43_42[0].forward = street42_3;
	    street43_42[0].left = street42_4;
	    
	    street43_44[0].forward = street44_45;
	    street43_44[0].right = street44_4;
	    street44_43[0].forward = street43_42;
	    
	    street44_45[0].forward = street45_46;
	    street45_44[0].forward = street44_43;
	    street45_44[0].left = street44_4;
	    
	    street45_46[0].forward = street46_49;
	    street45_46[0].right = street46_47;
	    street45_46[0].left = street46_61;
	    street46_45[0].forward = street45_44;
	    
	    street46_47[0].forward = street47_48;
	    street46_47[0].left = street47_51;
	    street47_46[0].left = street46_45;
	    street47_46[0].right = street46_49;
	    street47_46[0].forward = street46_61;
	    
	    street47_48[0].forward = street48_41;
	    street48_47[0].forward = street47_51;
	    street48_47[0].left = street47_46;
	    
	    street48_41[0].forward = street41_4;
	    street48_41[0].left = street41_5;
	    street41_48[0].forward = street48_47;
	    
	    street4_44[0].forward = street44_45;
	    street4_44[0].left = street44_43;
	    street44_4[0].forward = street4_2;
	    street44_4[0].right = street4_42;
	    street44_4[0].left = street4_41;
	    
	    street47_51[0].forward = street51_53;
	    street47_51[0].left = street51_52;
	    street51_47[0].forward = street47_48;
	    street51_47[0].right = street47_46;
	    
	    street13_12[0].forward = street12_18;
	    street13_12[0].right = street12_11;
	    street12_13[0].forward = street13_14;
	    street12_13[0].right = street13_15;
	    street12_13[0].left = street13_10;
	    
	    street46_49[0].forward = street49_52;
	    street49_46[0].forward = street46_45;
	    street49_46[0].left = street46_47;
	    street49_46[0].right = street46_61;
	    
	    street49_52[0].forward = street52_50;
	    street49_52[0].right = street52_51;
	    street52_49[0].forward = street49_46;
	    
	    street46_61[0].forward = streetToNowhere;
	    street61_46[0].forward = street46_47;
	    street61_46[0].right = street46_45;
	    street61_46[0].left = street46_49;
	    
	    street52_50[0].forward = street50_63;
	    street52_50[0].right = street50_53;
	    street50_52[0].forward = street52_49;
	    street50_52[0].left = street52_51;
	    
	    street51_53[0].forward = street53_62;
	    street51_53[0].left = street53_50;
	    street53_51[0].forward = street51_47;
	    street53_51[0].right = street51_52;
	    
	    street62_56[0].forward = street56_55;
	    street62_56[0].left = street56_57;
	    street56_62[0].forward = street62_53;
	    
	    street53_62[0].forward = street62_56;
	    street62_53[0].forward = street53_51;
	    street62_53[0].right = street53_50;
	    
	    street51_52[0].forward = street52_49;
	    street51_52[0].right = street52_50;
	    street52_51[0].forward = street51_47;
	    street52_51[0].left = street51_53;
	    
	    street57_63[0].forward = street63_50;
	    street63_57[0].forward = street57_58;
	    street63_57[0].right = street57_56;
	    
	    street57_56[0].forward = street56_62;
	    street57_56[0].left = street56_55;
	    street56_57[0].forward = street57_63;
	    street56_57[0].right = street57_58;
	    
	    street53_50[0].forward = street50_52;
	    street53_50[0].right = street50_63;
	    street50_53[0].forward = street53_51;
	    street50_53[0].left = street53_62;
	    
	    street50_63[0].forward = street63_57;
	    street63_50[0].forward = street50_52;
	    street63_50[0].left = street50_53;
	    
	    street56_55[0].forward = street55_54;
	    street56_55[0].right = street55_60;
	    street55_56[0].forward = street56_62;
	    street55_56[0].right = street56_57;
	    
	    street55_60[0].forward = streetToNowhere;
	    street60_55[0].forward = street55_56;
	    street60_55[0].right = street55_54;
	    
	    street55_54[0].forward = street54_28;
	    street55_54[0].left = street54_23;
	    street54_55[0].forward = street55_56;
	    street54_55[0].left = street55_60;
	    
	    street54_23[0].forward = street23_37;
	    street23_54[0].forward = street54_28;
	    street23_54[0].right = street54_55;
	    
	    street23_37[0].forward = street37_36;
	    street37_23[0].forward = street23_54;
	    
	    street37_36[0].forward = street36_35;
	    street37_36[0].left = street36_28;
	    street36_37[0].forward = street37_23;
	    
	    street36_28[0].forward = street28_54;
	    street36_28[0].right = street28_59;
	    street28_36[0].forward = street36_35;
	    street28_36[0].right = street36_37;
	    
	    street54_28[0].forward = street28_59;
	    street54_28[0].left = street28_36;
	    street28_54[0].forward = street54_23;
	    street28_54[0].right = street54_55;
	    
	    street28_59[0].forward = street59_25;
	    street28_59[0].left = street59_58;
	    street59_28[0].forward = street28_54;
	    street59_28[0].left = street28_36;
	    
	    street59_58[0].forward = street58_57;
	    street58_59[0].forward = street59_28;
	    street58_59[0].left = street59_25;
	    
	    street58_57[0].forward = street57_63;
	    street58_57[0].left = street57_56;
	    street57_58[0].forward = street58_59;
	    
	    street36_35[0].forward = street35_24;
	    street36_35[0].left = street35_34;
	    street35_36[0].forward = street36_28;
	    street35_36[0].left = street36_37;
	    
	    street35_24[0].forward = street24_34;
	    street24_35[0].forward = street35_36;
	    street24_35[0].right = street35_34;
	    
	    street24_34[0].forward = street34_33;
	    street24_34[0].right = street34_32;
	    street24_34[0].left = street34_35;
	    street34_24[0].forward = street24_35;
	    
	    street35_34[0].forward = street34_32;
	    street35_34[0].right = street34_24;
	    street35_34[0].left = street34_33;  
	    street34_35[0].forward = street35_36;
	    street34_35[0].left = street35_24;
	    
	    street34_33[0].forward = street33_30;
	    street34_33[0].right = street33_32;
	    street34_33[0].left = street33_25;
	    street33_34[0].forward = street34_24;
	    street33_34[0].right = street34_35;
	    street33_34[0].left = street34_32;
	    
	    street34_32[0].forward = street32_31;
	    street34_32[0].right = street32_26;
	    street34_32[0].left = street32_33;
	    street32_34[0].forward = street34_35;
	    street32_34[0].right = street34_33;
	    street32_34[0].left = street34_24;
	    
	    street32_26[0].forward = streetToNowhere;
	    street26_32[0].forward = street32_33;
	    street26_32[0].right = street32_31;
	    street26_32[0].left = street32_34;
	    
	    street32_33[0].forward = street33_25;
	    street32_33[0].right =  street33_30;
	    street32_33[0].left = street33_34;
	    street33_32[0].forward = street32_26;
	    street33_32[0].right = street32_34;
	    street33_32[0].left = street32_31;
	    
	    street33_25[0].forward = street25_59;
	    street33_25[0].right = street25_27;
	    street25_33[0].forward = street33_32;
	    street25_33[0].right = street33_34;
	    street25_33[0].left = street33_30;
	    
	    street59_25[0].forward = street25_27;
	    street59_25[0].right = street25_33;
	    street25_59[0].forward = street59_28;
	    street25_59[0].right = street59_58;
	    
	    street25_27[0].forward = street27_21;
	    street25_27[0].right = street27_30;
	    street27_25[0].forward = street25_59;
	    street27_25[0].left = street25_33;
	    
	    street27_21[0].forward = street21_22;
	    street21_27[0].forward = street27_25;
	    street21_27[0].left = street27_30;
	    
	    street21_22[0].forward = street22_30;
	    street22_21[0].forward = street21_27;
	    
	    street22_30[0].forward = street30_33;
	    street22_30[0].right = street30_27;
	    street22_30[0].left = street30_31;
	    street30_22[0].forward = street22_21;
	    
	    street30_27[0].forward = street27_25;
	    street30_27[0].right = street27_21;
	    street27_30[0].forward = street30_31;
	    street27_30[0].right = street30_33;
	    street27_30[0].left = street30_22;
	    
	    street30_31[0].forward = street31_29;
	    street30_31[0].right = street31_32;
	    street31_30[0].forward = street30_27;
	    street31_30[0].right = street30_22;
	    street31_30[0].left = street30_33;
	    
	    street31_29[0].forward = streetToNowhere;
	    street29_31[0].forward = street31_30;
	    street29_31[0].left = street31_32;
	    
	    street30_33[0].forward = street33_34;
	    street30_33[0].right = street33_25;
	    street30_33[0].left = street33_32;
	    street33_30[0].forward = street30_22;
	    street33_30[0].right = street30_31;
	    street33_30[0].left = street30_27;
	    
	    street32_31[0].forward = street31_30;
	    street32_31[0].right = street31_29;
	    street31_32[0].forward = street32_34;
	    street31_32[0].right = street32_33;
	    street31_32[0].left = street32_26;
	    
	    street2_4[0].forward = street4_44;
	    street2_4[0].right = street4_41;
	    street2_4[0].left = street4_42;
	    
	    street4_2[0].forward = street2_1;
	    street4_2[0].right = street2_1;
	    street4_2[0].left = street2_7;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	// -------------------------------------------------------

	public static void createStreets(Graph myGraph) {
		E edge = myGraph.getEdge(16, 13);

		for (int i = 0; i < 3; i++) {
			edge.street[i].setSpeedLimit(3);
		}
		for (int k = 0; k < edge.street[2].cellList.length - 1; k++) {
			edge.street[2].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street16_13 = new Lane[3];
		for (int i = 0; i < 3; i++) {
			street16_13[i] = edge.street[i];
		}
		streetList.add(street16_13);

		// ----------------------

		edge = myGraph.getEdge(13, 5);

		for (int i = 0; i < 3; i++) {
			edge.street[i].setSpeedLimit(4);
		}
		for (int k = 0; k < edge.street[2].cellList.length - 1; k++) {
			edge.street[2].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street13_5 = new Lane[3];
		for (int i = 0; i < 3; i++) {
			street13_5[i] = edge.street[i];
		}

		// ----------------------

		edge = myGraph.getEdge(20, 76);

		for (int i = 0; i < 3; i++) {
			edge.street[i].setSpeedLimit(4);
		}
		for (int k = 0; k < edge.street[2].cellList.length - 1; k++) {
			edge.street[2].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street20_76 = new Lane[3];
		for (int i = 0; i < 3; i++) {
			street20_76[i] = edge.street[i];
		}
		streetList.add(street20_76);

		// ----------------------

		edge = myGraph.getEdge(76, 79);

		for (int i = 0; i < 3; i++) {
			edge.street[i].setSpeedLimit(4);
		}
		for (int k = 0; k < edge.street[2].cellList.length - 1; k++) {
			edge.street[2].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street76_79 = new Lane[3];
		for (int i = 0; i < 3; i++) {
			street76_79[i] = edge.street[i];
		}

		// ----------------------

		edge = myGraph.getEdge(79, 82);

		for (int i = 0; i < 3; i++) {
			edge.street[i].setSpeedLimit(4);
		}
		for (int k = 0; k < edge.street[2].cellList.length; k++) {
			edge.street[2].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street79_82 = new Lane[3];
		for (int i = 0; i < 3; i++) {
			street79_82[i] = edge.street[i];
		}
		
		streetList.add(street79_82);

		// ----------------------

		edge = myGraph.getEdge(81, 80);

		for (int i = 0; i < 3; i++) {
			edge.street[i].setSpeedLimit(4);
		}
		for (int k = 0; k < edge.street[2].cellList.length; k++) {
			edge.street[2].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street81_80 = new Lane[3];
		for (int i = 0; i < 3; i++) {
			street81_80[i] = edge.street[i];
		}
		streetList.add(street81_80);
		streetList.add(street81_80);

		// ----------------------

		edge = myGraph.getEdge(80, 75);

		for (int i = 0; i < 3; i++) {
			edge.street[i].setSpeedLimit(4);
		}
		for (int k = 0; k < edge.street[2].cellList.length - 1; k++) {
			edge.street[2].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street80_75 = new Lane[3];
		for (int i = 0; i < 3; i++) {
			street80_75[i] = edge.street[i];
		}
		
		streetList.add(street80_75);

		// ----------------------

		edge = myGraph.getEdge(75, 19);

		for (int i = 0; i < 3; i++) {
			edge.street[i].setSpeedLimit(4);
		}
		for (int k = 0; k < edge.street[2].cellList.length - 1; k++) {
			edge.street[2].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street75_19 = new Lane[3];
		for (int i = 0; i < 3; i++) {
			street75_19[i] = edge.street[i];
		}

		// ----------------------

		edge = myGraph.getEdge(19, 18);

		for (int i = 0; i < 3; i++) {
			edge.street[i].setSpeedLimit(4);
		}
		for (int k = 0; k < edge.street[2].cellList.length - 1; k++) {
			edge.street[2].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street19_18 = new Lane[3];
		for (int i = 0; i < 3; i++) {
			street19_18[i] = edge.street[i];
		}
		streetList.add(street19_18);

		// -------------------

		edge = myGraph.getEdge(13, 12);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street13_12 = new Lane[1];
		street13_12[0] = edge.street[0];

		// -------------------

		edge = myGraph.getEdge(17, 18);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(3);
		}

		Lane[] street17_18 = new Lane[1];
		street17_18[0] = edge.street[0];

		// ---------------------

		edge = myGraph.getEdge(12, 32);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street12_32 = new Lane[1];
		street12_32[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(12, 11);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street12_11 = new Lane[1];
		street12_11[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(11, 10);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street11_10 = new Lane[1];
		street11_10[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(10, 9);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street10_9 = new Lane[1];
		street10_9[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(9, 36);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street9_36 = new Lane[1];
		street9_36[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(36, 35);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street36_35 = new Lane[1];
		street36_35[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(36, 37);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street36_37 = new Lane[1];
		street36_37[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(35, 38);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street35_38 = new Lane[1];
		street35_38[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(33, 32);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street33_32 = new Lane[1];
		street33_32[0] = edge.street[0];

		// ----------------------------------------

		edge = myGraph.getEdge(33, 34);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street33_34 = new Lane[1];
		street33_34[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(35, 33);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street35_33 = new Lane[1];
		street35_33[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(9, 8);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street9_8 = new Lane[1];
		street9_8[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(86, 4);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(-1);
		}

		Lane[] street86_4 = new Lane[1];
		street86_4[0] = edge.street[0];
		streetList.add(street86_4);

		// -----------------------------------------

		edge = myGraph.getEdge(5, 87);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(-1);
		}

		Lane[] street5_87 = new Lane[1];
		street5_87[0] = edge.street[0];
		streetList.add(street5_87);

		// -----------------------------------------

		edge = myGraph.getEdge(89, 46);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(-1);
		}

		Lane[] street89_46 = new Lane[1];
		street89_46[0] = edge.street[0];
		streetList.add(street89_46);

		// -----------------------------------------

		edge = myGraph.getEdge(88, 6);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(-1);
		}

		Lane[] street88_6 = new Lane[1];
		street88_6[0] = edge.street[0];
		streetList.add(street88_6);

		// -----------------------------------------

		edge = myGraph.getEdge(49, 91);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(-1);
		}

		Lane[] street49_91 = new Lane[1];
		street49_91[0] = edge.street[0];
		streetList.add(street49_91);

		// -----------------------------------------

		edge = myGraph.getEdge(91, 49);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(-1);
		}

		Lane[] street91_49 = new Lane[1];
		street91_49[0] = edge.street[0];
		streetList.add(street91_49);

		// -----------------------------------------

		edge = myGraph.getEdge(52, 92);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(-1);
		}

		Lane[] street52_92 = new Lane[1];
		street52_92[0] = edge.street[0];
		streetList.add(street52_92);

		// -----------------------------------------

		edge = myGraph.getEdge(92, 52);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(-1);
		}

		Lane[] street92_52 = new Lane[1];
		street92_52[0] = edge.street[0];
		streetList.add(street92_52);

		// -----------------------------------------

		edge = myGraph.getEdge(101, 23);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(-1);
		}

		Lane[] street101_23 = new Lane[1];
		street101_23[0] = edge.street[0];
		streetList.add(street101_23);

		// -----------------------------------------

		edge = myGraph.getEdge(23, 101);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(-1);
		}

		Lane[] street23_101 = new Lane[1];
		street23_101[0] = edge.street[0];
		streetList.add(street23_101);

		// -----------------------------------------

		edge = myGraph.getEdge(2, 84);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(-1);
		}

		Lane[] street2_84 = new Lane[1];
		street2_84[0] = edge.street[0];
		streetList.add(street2_84);

		// -----------------------------------------

		edge = myGraph.getEdge(8, 7);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street8_7 = new Lane[1];
		street8_7[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(8, 39);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street8_39 = new Lane[1];
		street8_39[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(6, 8);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street6_8 = new Lane[1];
		street6_8[0] = edge.street[0];
		streetList.add(street6_8);

		// -----------------------------------------

		edge = myGraph.getEdge(7, 8);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street7_8 = new Lane[1];
		street7_8[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(8, 9);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street8_9 = new Lane[1];
		street8_9[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(9, 10);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street9_10 = new Lane[1];
		street9_10[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(10, 11);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street10_11 = new Lane[1];
		street10_11[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(11, 12);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street11_12 = new Lane[1];
		street11_12[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(37, 36);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street37_36 = new Lane[1];
		street37_36[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(32, 30);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street32_30 = new Lane[1];
		street32_30[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(30, 29);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street30_29 = new Lane[1];
		street30_29[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(32, 40);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street32_40 = new Lane[1];
		street32_40[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(30, 31);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street30_31 = new Lane[1];
		street30_31[0] = edge.street[0];

		// -----------------------------------------

		edge = myGraph.getEdge(31, 30);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street31_30 = new Lane[1];
		street31_30[0] = edge.street[0];

		// ----------------------------------------

		edge = myGraph.getEdge(29, 28);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street29_28 = new Lane[1];
		street29_28[0] = edge.street[0];

		// ----------------------------------------

		edge = myGraph.getEdge(29, 41);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street29_41 = new Lane[1];
		street29_41[0] = edge.street[0];

		// ----------------------------------------

		edge = myGraph.getEdge(41, 40);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street41_40 = new Lane[1];
		street41_40[0] = edge.street[0];

		// ----------------------------------------

		edge = myGraph.getEdge(41, 42);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street41_42 = new Lane[1];
		street41_42[0] = edge.street[0];

		// ----------------------------------------

		edge = myGraph.getEdge(40, 38);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street40_38 = new Lane[1];
		street40_38[0] = edge.street[0];

		// ----------------------------------------

		edge = myGraph.getEdge(40, 32);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street40_32 = new Lane[1];
		street40_32[0] = edge.street[0];

		// ----------------------------------------

		edge = myGraph.getEdge(38, 39);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street38_39 = new Lane[1];
		street38_39[0] = edge.street[0];

		// ----------------------------------------

		edge = myGraph.getEdge(38, 35);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(2);
		}

		Lane[] street38_35 = new Lane[1];
		street38_35[0] = edge.street[0];

		// ----------------------------------------

		edge = myGraph.getEdge(15, 16);

		for (int i = 0; i < 1; i++) {
			edge.street[i].setSpeedLimit(3);
		}

		Lane[] street15_16 = new Lane[1];
		street15_16[0] = edge.street[0];

		// ----------------------------------------

		edge = myGraph.getEdge(18, 28);

		for (int i = 0; i < 3; i++) {
			edge.street[i].setSpeedLimit(3);
		}
		for (int k = 0; k < edge.street[2].cellList.length - 1; k++) {
			edge.street[2].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street18_28 = new Lane[3];
		for (int i = 0; i < 3; i++) {
			street18_28[i] = edge.street[i];
		}

		// ----------------------------------------

		edge = myGraph.getEdge(28, 16);

		for (int i = 0; i < 3; i++) {
			edge.street[i].setSpeedLimit(3);
		}
		for (int k = 0; k < edge.street[2].cellList.length - 1; k++) {
			edge.street[2].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street28_16 = new Lane[3];
		for (int i = 0; i < 3; i++) {
			street28_16[i] = edge.street[i];
		}

		// ----------------------------------------

		edge = myGraph.getEdge(4, 14);

		for (int i = 0; i < 3; i++) {
			edge.street[i].setSpeedLimit(3);
		}
		for (int k = 0; k < edge.street[2].cellList.length - 1; k++) {
			edge.street[2].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street4_14 = new Lane[3];
		for (int i = 0; i < 3; i++) 
		{
			street4_14[i] = edge.street[i];
		}
		streetList.add(street4_14);
		streetList.add(street4_14);

		// ----------------------------------------

		edge = myGraph.getEdge(14, 15);

		for (int i = 0; i < 3; i++) {
			edge.street[i].setSpeedLimit(3);
		}
		for (int k = 0; k < edge.street[2].cellList.length - 1; k++) {
			edge.street[2].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street14_15 = new Lane[3];
		for (int i = 0; i < 3; i++) {
			street14_15[i] = edge.street[i];
		}

		// ----------------------------------------

		edge = myGraph.getEdge(15, 17);

		for (int i = 0; i < 3; i++) {
			edge.street[i].setSpeedLimit(3);
		}
		for (int k = 0; k < edge.street[2].cellList.length - 1; k++) {
			edge.street[2].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street15_17 = new Lane[3];
		for (int i = 0; i < 3; i++) {
			street15_17[i] = edge.street[i];
		}
		streetList.add(street15_17);

		// ----------------------------------------

		edge = myGraph.getEdge(17, 20);

		for (int i = 0; i < 3; i++) {
			edge.street[i].setSpeedLimit(3);
		}
		for (int k = 0; k < edge.street[2].cellList.length; k++) {
			edge.street[2].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street17_20 = new Lane[3];
		for (int i = 0; i < 3; i++) {
			street17_20[i] = edge.street[i];
		}

		// -------------------------------------------

		edge = myGraph.getEdge(18, 42);
		edge.street[0].setSpeedLimit(2);
		Lane[] street18_42 = new Lane[1];
		street18_42[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(42, 18);
		edge.street[0].setSpeedLimit(2);
		Lane[] street42_18 = new Lane[1];
		street42_18[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(24, 25);
		edge.street[0].setSpeedLimit(3);
		Lane[] street24_25 = new Lane[1];
		street24_25[0] = edge.street[0];
		streetList.add(street24_25);

		// -------------------------------------------

		edge = myGraph.getEdge(25, 26);
		edge.street[0].setSpeedLimit(3);
		Lane[] street25_26 = new Lane[1];
		street25_26[0] = edge.street[0];
		streetList.add(street25_26);

		// -------------------------------------------

		edge = myGraph.getEdge(26, 27);
		edge.street[0].setSpeedLimit(3);
		Lane[] street26_27 = new Lane[1];
		street26_27[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(27, 2);
		edge.street[0].setSpeedLimit(3);
		Lane[] street27_2 = new Lane[1];
		street27_2[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(1, 25);
		edge.street[0].setSpeedLimit(3);
		Lane[] street1_25 = new Lane[1];
		street1_25[0] = edge.street[0];
		streetList.add(street1_25);

		// -------------------------------------------

		edge = myGraph.getEdge(22, 26);
		edge.street[0].setSpeedLimit(3);
		Lane[] street22_26 = new Lane[1];
		street22_26[0] = edge.street[0];
		streetList.add(street22_26);

		// -------------------------------------------

		edge = myGraph.getEdge(18, 17);
		edge.street[0].setSpeedLimit(3);
		Lane[] street18_17 = new Lane[1];
		street18_17[0] = edge.street[0];
		streetList.add(street18_17);

		// -------------------------------------------

		edge = myGraph.getEdge(19, 17);
		edge.street[0].setSpeedLimit(3);
		Lane[] street19_17 = new Lane[1];
		street19_17[0] = edge.street[0];
		streetList.add(street19_17);

		// -------------------------------------------

		edge = myGraph.getEdge(17, 21);
		edge.street[0].setSpeedLimit(3);
		Lane[] street17_21 = new Lane[1];
		street17_21[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(21, 22);
		edge.street[0].setSpeedLimit(3);
		Lane[] street21_22 = new Lane[1];
		street21_22[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(22, 23);
		edge.street[0].setSpeedLimit(3);
		Lane[] street22_23 = new Lane[1];
		street22_23[0] = edge.street[0];
		streetList.add(street22_23);

		// -------------------------------------------

		edge = myGraph.getEdge(21, 20);
		edge.street[0].setSpeedLimit(3);
		Lane[] street21_20 = new Lane[1];
		street21_20[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(21, 17);
		edge.street[0].setSpeedLimit(3);
		Lane[] street21_17 = new Lane[1];
		street21_17[0] = edge.street[0];
		streetList.add(street21_17);

		// -------------------------------------------

		edge = myGraph.getEdge(75, 76);
		edge.street[0].setSpeedLimit(3);
		Lane[] street75_76 = new Lane[1];
		street75_76[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(76, 77);
		edge.street[0].setSpeedLimit(3);
		Lane[] street76_77 = new Lane[1];
		street76_77[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(78, 79);
		edge.street[0].setSpeedLimit(3);
		Lane[] street78_79 = new Lane[1];
		street78_79[0] = edge.street[0];
		streetList.add(street78_79);

		// -------------------------------------------

		edge = myGraph.getEdge(79, 80);
		edge.street[0].setSpeedLimit(3);
		Lane[] street79_80 = new Lane[1];
		street79_80[0] = edge.street[0];
		streetList.add(street79_80);

		// -------------------------------------------

		edge = myGraph.getEdge(42, 73);
		edge.street[0].setSpeedLimit(2);
		Lane[] street42_73 = new Lane[1];
		street42_73[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(73, 72);
		edge.street[0].setSpeedLimit(2);
		Lane[] street73_72 = new Lane[1];
		street73_72[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(72, 65);
		edge.street[0].setSpeedLimit(2);
		Lane[] street72_65 = new Lane[1];
		street72_65[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(65, 64);
		edge.street[0].setSpeedLimit(2);
		Lane[] street65_64 = new Lane[1];
		street65_64[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(64, 63);
		edge.street[0].setSpeedLimit(2);
		Lane[] street64_63 = new Lane[1];
		street64_63[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(63, 62);
		edge.street[0].setSpeedLimit(2);
		Lane[] street63_62 = new Lane[1];
		street63_62[0] = edge.street[0];
		edge = myGraph.getEdge(23, 22);

		// -------------------------------------------

		edge = myGraph.getEdge(72, 70);
		edge.street[0].setSpeedLimit(2);
		Lane[] street72_70 = new Lane[1];
		street72_70[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(70, 71);
		edge.street[0].setSpeedLimit(2);
		Lane[] street70_71 = new Lane[1];
		street70_71[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(71, 70);
		edge.street[0].setSpeedLimit(2);
		Lane[] street71_70 = new Lane[1];
		street71_70[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(70, 72);
		edge.street[0].setSpeedLimit(2);
		Lane[] street70_72 = new Lane[1];
		street70_72[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(65, 66);
		edge.street[0].setSpeedLimit(2);
		Lane[] street65_66 = new Lane[1];
		street65_66[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(66, 68);
		edge.street[0].setSpeedLimit(2);
		Lane[] street66_68 = new Lane[1];
		street66_68[0] = edge.street[0];
		streetList.add(street66_68);

		// -------------------------------------------

		edge = myGraph.getEdge(68, 69);
		edge.street[0].setSpeedLimit(2);
		Lane[] street68_69 = new Lane[1];
		street68_69[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(69, 68);
		edge.street[0].setSpeedLimit(2);
		Lane[] street69_68 = new Lane[1];
		street69_68[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(68, 70);
		edge.street[0].setSpeedLimit(2);
		Lane[] street68_70 = new Lane[1];
		street68_70[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(68, 66);
		edge.street[0].setSpeedLimit(2);
		Lane[] street68_66 = new Lane[1];
		street68_66[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(67, 66);
		edge.street[0].setSpeedLimit(2);
		Lane[] street67_66 = new Lane[1];
		street67_66[0] = edge.street[0];
		streetList.add(street67_66);

		// -------------------------------------------

		edge = myGraph.getEdge(66, 65);
		edge.street[0].setSpeedLimit(2);
		Lane[] street66_65 = new Lane[1];
		street66_65[0] = edge.street[0];
		streetList.add(street66_65);

		// -------------------------------------------

		edge = myGraph.getEdge(64, 74);
		edge.street[0].setSpeedLimit(2);
		Lane[] street64_74 = new Lane[1];
		street64_74[0] = edge.street[0];
		streetList.add(street64_74);
		
		// -------------------------------------------

		edge = myGraph.getEdge(53, 74);
		edge.street[0].setSpeedLimit(2);
		Lane[] street53_74 = new Lane[1];
		street53_74[0] = edge.street[0];
		streetList.add(street53_74);
		
		// -------------------------------------------

		edge = myGraph.getEdge(74, 73);
		edge.street[0].setSpeedLimit(2);
		Lane[] street74_73 = new Lane[1];
		street74_73[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(73, 42);
		edge.street[0].setSpeedLimit(2);
		Lane[] street73_42 = new Lane[1];
		street73_42[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(3, 85);
		edge.street[0].setSpeedLimit(-1);
		Lane[] street3_85 = new Lane[1];
		street3_85[0] = edge.street[0];
		streetList.add(street3_85);

		// -------------------------------------------

		edge = myGraph.getEdge(85, 3);
		edge.street[0].setSpeedLimit(-1);
		Lane[] street85_3 = new Lane[1];
		street85_3[0] = edge.street[0];
		streetList.add(street85_3);

		// -------------------------------------------

		edge = myGraph.getEdge(82, 98);
		edge.street[0].setSpeedLimit(-1);
		Lane[] street82_98 = new Lane[1];
		street82_98[0] = edge.street[0];
		streetList.add(street82_98);

		// -------------------------------------------

		edge = myGraph.getEdge(97, 81);
		edge.street[0].setSpeedLimit(-1);
		Lane[] street97_81 = new Lane[1];
		street97_81[0] = edge.street[0];
		streetList.add(street97_81);

		// -------------------------------------------

		edge = myGraph.getEdge(39, 44);
		edge.street[0].setSpeedLimit(2);
		Lane[] street39_44 = new Lane[1];
		street39_44[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(56, 93);
		edge.street[0].setSpeedLimit(-1);
		Lane[] street56_93 = new Lane[1];
		street56_93[0] = edge.street[0];
		streetList.add(street56_93);

		// -------------------------------------------

		edge = myGraph.getEdge(93, 56);
		edge.street[0].setSpeedLimit(-1);
		Lane[] street93_56 = new Lane[1];
		street93_56[0] = edge.street[0];
		streetList.add(street93_56);

		// -------------------------------------------

		edge = myGraph.getEdge(44, 45);
		edge.street[0].setSpeedLimit(2);
		Lane[] street44_45 = new Lane[1];
		street44_45[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(45, 44);
		edge.street[0].setSpeedLimit(2);
		Lane[] street45_44 = new Lane[1];
		street45_44[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(46, 45);
		edge.street[0].setSpeedLimit(2);
		Lane[] street46_45 = new Lane[1];
		street46_45[0] = edge.street[0];
		streetList.add(street46_45);

		// -------------------------------------------

		edge = myGraph.getEdge(44, 51);
		edge.street[0].setSpeedLimit(2);
		Lane[] street44_51 = new Lane[1];
		street44_51[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(51, 52);
		edge.street[0].setSpeedLimit(2);
		Lane[] street51_52 = new Lane[1];
		street51_52[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(52, 51);
		edge.street[0].setSpeedLimit(2);
		Lane[] street52_51 = new Lane[1];
		street52_51[0] = edge.street[0];
		streetList.add(street52_51);

		// -------------------------------------------

		edge = myGraph.getEdge(51, 54);
		edge.street[0].setSpeedLimit(2);
		Lane[] street51_54 = new Lane[1];
		street51_54[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(54, 53);
		edge.street[0].setSpeedLimit(2);
		Lane[] street54_53 = new Lane[1];
		street54_53[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(53, 40);
		edge.street[0].setSpeedLimit(2);
		Lane[] street53_40 = new Lane[1];
		street53_40[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(40, 53);
		edge.street[0].setSpeedLimit(2);
		Lane[] street40_53 = new Lane[1];
		street40_53[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(45, 47);
		edge.street[0].setSpeedLimit(2);
		Lane[] street45_47 = new Lane[1];
		street45_47[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(47, 90);
		edge.street[0].setSpeedLimit(-1);
		Lane[] street47_90 = new Lane[1];
		street47_90[0] = edge.street[0];
		streetList.add(street47_90);

		// -------------------------------------------

		edge = myGraph.getEdge(83, 1);
		edge.street[0].setSpeedLimit(-1);
		Lane[] street83_1 = new Lane[1];
		street83_1[0] = edge.street[0];
		streetList.add(street83_1);

		// -------------------------------------------

		edge = myGraph.getEdge(95, 67);
		edge.street[0].setSpeedLimit(-1);
		Lane[] street95_67 = new Lane[1];
		street95_67[0] = edge.street[0];
		streetList.add(street95_67);

		// -------------------------------------------

		edge = myGraph.getEdge(62, 94);
		edge.street[0].setSpeedLimit(-1);
		Lane[] street62_94 = new Lane[1];
		street62_94[0] = edge.street[0];
		streetList.add(street62_94);

		// -------------------------------------------

		edge = myGraph.getEdge(96, 69);
		edge.street[0].setSpeedLimit(-1);
		Lane[] street96_69 = new Lane[1];
		street96_69[0] = edge.street[0];
		streetList.add(street96_69);

		// -------------------------------------------

		edge = myGraph.getEdge(69, 96);
		edge.street[0].setSpeedLimit(-1);
		Lane[] street69_96 = new Lane[1];
		street69_96[0] = edge.street[0];
		streetList.add(street69_96);

		// -------------------------------------------

		edge = myGraph.getEdge(99, 78);
		edge.street[0].setSpeedLimit(-1);
		Lane[] street99_78 = new Lane[1];
		street99_78[0] = edge.street[0];
		streetList.add(street99_78);

		// -------------------------------------------

		edge = myGraph.getEdge(77, 100);
		edge.street[0].setSpeedLimit(-1);
		Lane[] street77_100 = new Lane[1];
		street77_100[0] = edge.street[0];
		streetList.add(street77_100);

		// -------------------------------------------

		edge = myGraph.getEdge(102, 24);
		edge.street[0].setSpeedLimit(-1);
		Lane[] street102_24 = new Lane[1];
		street102_24[0] = edge.street[0];
		streetList.add(street102_24);

		// -------------------------------------------

		edge = myGraph.getEdge(45, 48);
		edge.street[0].setSpeedLimit(2);
		Lane[] street45_48 = new Lane[1];
		street45_48[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(48, 50);
		edge.street[0].setSpeedLimit(2);
		Lane[] street48_50 = new Lane[1];
		street48_50[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(50, 48);
		edge.street[0].setSpeedLimit(2);
		Lane[] street50_48 = new Lane[1];
		street50_48[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(48, 49);
		edge.street[0].setSpeedLimit(2);
		Lane[] street48_49 = new Lane[1];
		street48_49[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(49, 48);
		edge.street[0].setSpeedLimit(2);
		Lane[] street49_48 = new Lane[1];
		street49_48[0] = edge.street[0];
		streetList.add(street49_48);

		// -------------------------------------------

		edge = myGraph.getEdge(48, 45);
		edge.street[0].setSpeedLimit(2);
		Lane[] street48_45 = new Lane[1];
		street48_45[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(32, 12);
		edge.street[0].setSpeedLimit(2);
		Lane[] street32_12 = new Lane[1];
		street32_12[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(41, 43);
		edge.street[0].setSpeedLimit(2);
		Lane[] street41_43 = new Lane[1];
		street41_43[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(43, 41);
		edge.street[0].setSpeedLimit(2);
		Lane[] street43_41 = new Lane[1];
		street43_41[0] = edge.street[0];

		// -------------------------------------------
		//-------------------------------------------
		
		edge = myGraph.getEdge(42, 41);
		edge.street[0].setSpeedLimit(2);
		Lane[] street42_41 = new Lane[1];
		street42_41[0] = edge.street[0];
		
		// -------------------------------------------
		//-------------------------------------------

		edge = myGraph.getEdge(53, 54);
		edge.street[0].setSpeedLimit(2);
		for (int k = 0; k < edge.street[0].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}
		Lane[] street53_54 = new Lane[1];
		street53_54[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(54, 55);
		edge.street[0].setSpeedLimit(2);
		for (int k = 0; k < edge.street[0].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}
		Lane[] street54_55 = new Lane[1];
		street54_55[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(55, 58);
		edge.street[0].setSpeedLimit(2);
		for (int k = 0; k < edge.street[0].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}
		Lane[] street55_58 = new Lane[1];
		street55_58[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(58, 60);
		edge.street[0].setSpeedLimit(2);
		for (int k = 0; k < edge.street[0].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}
		Lane[] street58_60 = new Lane[1];
		street58_60[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(60, 52);
		edge.street[0].setSpeedLimit(2);
		for (int k = 0; k < edge.street[0].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}
		Lane[] street60_52 = new Lane[1];
		street60_52[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(52, 60);
		edge.street[0].setSpeedLimit(2);
		for (int k = 0; k < edge.street[0].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}
		Lane[] street52_60 = new Lane[1];
		street52_60[0] = edge.street[0];
		streetList.add(street52_60);

		// -------------------------------------------

		edge = myGraph.getEdge(60, 58);
		edge.street[0].setSpeedLimit(2);
		for (int k = 0; k < edge.street[0].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}
		Lane[] street60_58 = new Lane[1];
		street60_58[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(58, 55);
		edge.street[0].setSpeedLimit(2);
		for (int k = 0; k < edge.street[0].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}
		Lane[] street58_55 = new Lane[1];
		street58_55[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(55, 56);
		edge.street[0].setSpeedLimit(2);
		for (int k = 0; k < edge.street[0].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}
		Lane[] street55_56 = new Lane[1];
		street55_56[0] = edge.street[0];
		streetList.add(street55_56);

		// -------------------------------------------

		edge = myGraph.getEdge(56, 55);
		edge.street[0].setSpeedLimit(2);
		for (int k = 0; k < edge.street[0].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}
		Lane[] street56_55 = new Lane[1];
		street56_55[0] = edge.street[0];
		streetList.add(street56_55);

		// -------------------------------------------

		edge = myGraph.getEdge(55, 54);
		edge.street[0].setSpeedLimit(2);
		for (int k = 0; k < edge.street[0].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}
		Lane[] street55_54 = new Lane[1];
		street55_54[0] = edge.street[0];

		// -------------------------------------------

		edge = myGraph.getEdge(12, 13);
		edge.street[0].setSpeedLimit(2);
		for (int k = 0; k < edge.street[0].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}
		Lane[] street12_13 = new Lane[1];
		street12_13[0] = edge.street[0];
		streetList.add(street12_13);

		// -------------------------------------------

		edge = myGraph.getEdge(13, 14);
		edge.street[0].setSpeedLimit(2);
		for (int k = 0; k < edge.street[0].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}
		Lane[] street13_14 = new Lane[1];
		street13_14[0] = edge.street[0];
		streetList.add(street13_14);

		// ------------------------------------------------

		edge = myGraph.getEdge(14, 3);

		for (int i = 0; i < 2; i++) {
			edge.street[i].setSpeedLimit(3);
		}
		for (int k = 0; k < edge.street[0].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}

		Lane[] street14_3 = new Lane[2];
		for (int i = 0; i < 2; i++) {
			street14_3[i] = edge.street[i];
		}
		streetList.add(street14_3);

		// -------------------------------------------

		edge = myGraph.getEdge(3, 14);

		for (int i = 0; i < 2; i++) {
			edge.street[i].setSpeedLimit(3);
		}
		for (int k = 0; k < edge.street[1].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}

		Lane[] street3_14 = new Lane[2];
		for (int i = 0; i < 2; i++) {
			street3_14[i] = edge.street[i];
		}
		streetList.add(street3_14);

		// -------------------------------------------

		edge = myGraph.getEdge(14, 13);

		for (int i = 0; i < 2; i++) {
			edge.street[i].setSpeedLimit(3);
		}
		for (int k = 0; k < edge.street[1].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}

		Lane[] street14_13 = new Lane[2];
		for (int i = 0; i < 2; i++) {
			street14_13[i] = edge.street[i];
		}
		streetList.add(street14_13);

		// -------------------------------------------

		edge = myGraph.getEdge(23, 22);

		for (int i = 0; i < 2; i++) {
			edge.street[i].setSpeedLimit(3);
		}
		for (int k = 0; k < edge.street[1].cellList.length; k++) {
			edge.street[1].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street23_22 = new Lane[2];
		for (int i = 0; i < 2; i++) {
			street23_22[i] = edge.street[i];
		}
		streetList.add(street23_22);
		streetList.add(street23_22);

		// -------------------------------------------

		edge = myGraph.getEdge(22, 21);

		for (int i = 0; i < 2; i++) {
			edge.street[i].setSpeedLimit(3);
		}
		for (int k = 0; k < edge.street[1].cellList.length - 1; k++) {
			edge.street[1].cellList[k].setForbidden(true); // buspas
		}

		Lane[] street22_21 = new Lane[2];
		for (int i = 0; i < 2; i++) {
			street22_21[i] = edge.street[i];
		}
	
		streetList.add(street22_21);

		// -----------------------------------------------

		for (int i = 0; i < 3; i++) {
			street16_13[i].forward = street13_5;
			street16_13[i].right = street13_12;
			street16_13[i].left = street13_14; //na

			street18_28[i].forward = street28_16;

			street28_16[i].forward = street16_13;

			street19_18[i].forward = street18_28;
			street19_18[i].right = street18_42;

			street4_14[i].forward = street14_15;
			street4_14[i].right = street14_3;

			street14_15[i].forward = street15_17;
			street14_15[i].left = street15_16;

			street15_17[i].forward = street17_20;
			street15_17[i].right = street17_21;

			street75_19[i].left = street19_17;
			street75_19[i].forward = street19_18;

			street80_75[i].forward = street75_19;
			street80_75[i].left = street75_76;

			street81_80[i].forward = street80_75;

			street17_20[i].forward = street20_76;

			street20_76[i].forward = street76_79;
			street20_76[i].right = street76_77;

			street76_79[i].forward = street79_82;

			street79_82[i].forward = street82_98;
		}

		street15_16[0].forward = street16_13;

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

		street38_35[0].left = street35_33;

		street35_33[0].forward = street33_32;
		street35_33[0].right = street33_34;

		street19_17[0].forward = street17_21;

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

		street32_40[0].forward = street40_53;
		street32_40[0].left = street40_38;

		street32_30[0].forward = street30_29;
		street32_30[0].right = street30_31;

		street31_30[0].right = street30_29;

		street30_29[0].right = street29_28;
		street30_29[0].left = street29_41;
		
		street29_28[0].right = street28_16;

		street29_41[0].left = street41_40;
		street29_41[0].right = street41_42;
		street29_41[0].forward = street41_43;

		street43_41[0].right = street41_40;
		street43_41[0].left = street41_42;

		street41_40[0].forward = street40_38;
		street41_40[0].left = street40_32;
		street41_40[0].right = street40_53;

		street40_38[0].forward = street38_39;
		street40_38[0].left = street38_35;

		street38_39[0].right = street39_44;

		street41_42[0].right = street42_18;
		street41_42[0].left = street42_73;

		street42_18[0].right = street18_28;
		street42_18[0].forward = street18_17;

		street18_17[0].forward = street17_21;

		street18_42[0].forward = street42_73;
		street18_42[0].left = street42_41;

		street24_25[0].forward = street25_26;

		street25_26[0].forward = street26_27;

		street26_27[0].forward = street27_2;

		street1_25[0].left = street25_26;

		street22_26[0].right = street26_27;
		street22_26[0].forward = street22_26; //naginanie

		street17_21[0].forward = street21_22;

		street21_22[0].forward = street22_23;
		street21_22[0].right = street22_26;

		street22_23[0].forward = street23_101;

		street75_76[0].forward = street76_77;
		street75_76[0].left = street76_79;

		// street76_77[0].forward = wyjazd

		street78_79[0].forward = street79_80;
		street78_79[0].right = street79_82;

		street79_80[0].left = street80_75;
		street79_80[0].forward = street80_75; //naginanie

		street42_73[0].forward = street73_72;

		street73_72[0].forward = street72_65;
		street73_72[0].right = street72_70;

		street72_70[0].forward = street70_71;

		street71_70[0].forward = street70_72;

		street70_72[0].right = street72_65;

		street72_65[0].right = street65_66;
		street72_65[0].left = street65_64;

		street65_66[0].forward = street66_68;

		street66_68[0].forward = street68_69;
		street66_68[0].right = street68_70;

		// street68_69[0].forward = koniec

		street67_66[0].right = street66_65;
		street67_66[0].left = street66_68;

		street69_68[0].forward = street68_66;
		street69_68[0].left = street68_70;

		street68_66[0].forward = street66_65;

		street66_65[0].forward = street65_64;

		street65_64[0].forward = street64_63;
		street65_64[0].left = street64_74;

		street64_74[0].left = street74_73;

		street53_74[0].forward = street74_73;

		street74_73[0].right = street73_42;
		street74_73[0].left = street73_72;

		street73_42[0].forward = street42_18;

		street64_63[0].right = street63_62;

		// street63_62[0].forward = koniec

		street8_39[0].forward = street39_44;

		street39_44[0].left = street44_45;
		street39_44[0].forward = street44_51;

		street21_20[0].forward = street20_76;

		street44_45[0].forward = street45_47;
		street44_45[0].right = street45_48;

		street45_47[0].forward = street47_90;

		street46_45[0].forward = street45_44;

		street45_44[0].left = street44_51;

		street45_48[0].forward = street48_50;
		street45_48[0].left = street48_49;

		street50_48[0].forward = street48_45;
		street50_48[0].right = street48_49;

		street49_48[0].right = street48_45;
		street49_48[0].left = street48_50;

		street48_45[0].right = street45_47;
		street48_45[0].left = street45_44;

		street48_49[0].forward = street49_91;

		street44_51[0].right = street51_54;
		street44_51[0].left = street51_52;

		street51_52[0].forward = street52_92;

		street51_54[0].right = street54_53;

		street54_53[0].left = street53_74;
		street54_53[0].forward = street53_40;

		street53_40[0].forward = street40_32;
		street53_40[0].right = street40_38;

		street40_32[0].left = street32_30;
		street40_32[0].forward = street32_12;

		street32_12[0].right = street12_11;
		street32_12[0].forward = street12_13;

		street12_13[0].forward = street13_14;

		street13_14[0].forward = street14_3;

		street40_53[0].right = street53_74;
		street40_53[0].forward = street53_54;

		street53_54[0].forward = street54_55;

		street54_55[0].right = street55_56;
		street54_55[0].left = street55_58;

		street55_56[0].forward = street56_93;

		street56_55[0].forward = street55_58;
		street56_55[0].left = street55_54;

		street55_58[0].forward = street58_60;

		street58_60[0].forward = street60_52;

		street60_52[0].forward = street52_92;

		street52_60[0].forward = street60_58;

		street60_58[0].forward = street58_55;

		street58_55[0].forward = street55_56;
		street58_55[0].right = street55_54;

		street55_54[0].forward = street54_53;

		street85_3[0].forward = street3_14;

		street97_81[0].forward = street81_80;

		street93_56[0].forward = street56_55;

		street92_52[0].forward = street52_60;

		street89_46[0].forward = street46_45;

		street91_49[0].forward = street49_48;

		street101_23[0].forward = street23_22;

		street86_4[0].forward = street4_14;

		street21_17[0].forward = street17_18;
		street21_17[0].right = street17_20; // naginanie

		street17_18[0].forward = street18_42;
		street17_18[0].left = street18_28;

		street102_24[0].forward = street24_25;

		street83_1[0].forward = street1_25;

		street95_67[0].forward = street67_66;

		street63_62[0].forward = street62_94;

		street96_69[0].forward = street69_68;

		street68_69[0].forward = street69_96;

		street99_78[0].forward = street78_79;
		
		street52_51[0].forward = street51_54;

		street76_77[0].forward = street77_100;

		street88_6[0].forward = street6_8;

		street27_2[0].forward = street2_84;
		
		street42_41[0].forward = street41_40;
		street42_41[0].right = street41_43;

		for (int i = 0; i < 2; i++) 
		{
			street23_22[i].forward = street22_21;
			street23_22[i].right = street22_23; //naginanie
			street23_22[i].left = street22_26; //naginanie
			street22_21[i].forward = street21_17;
			street22_21[i].right = street21_20;
			street14_13[i].forward = street13_12;
			street14_3[i].forward = street3_85;
			street3_14[i].forward = street14_13;
			street3_14[i].right = street14_15;
		}

		for (int i = 0; i < 3; i++) 
		{
			street13_5[i].forward = street5_87;
		}
	}

	public static Lane[] getStreet(int v1, int v2) 
	{
		Iterator<Lane[]> iter = streetList.iterator();
		while (iter.hasNext()) 
		{
			Lane[] street = iter.next();
			if (street[0].begin.toString().equals(Integer.toString(v1))
					&& street[0].end.toString().equals(Integer.toString(v2))) 
			{
				return street;
			}
		}
		//System.out.println("Util.getStreet(): no result – v1: " + v1 + " v2: " + v2);
		return null;
	}
	
	public static Lane[] getPedStreet(int v1, int v2) 
	{
		Iterator<Lane[]> iter = pedestriansStreetList.iterator();
		while (iter.hasNext()) 
		{
			Lane[] street = iter.next();
			if (street[0].begin.toString().equals(Integer.toString(v1))	&& (street[0].end.toString().equals(Integer.toString(v2)) && street[0].clDir)
				|| street[0].end.toString().equals(Integer.toString(v1)) && (street[0].begin.toString().equals(Integer.toString(v2)) && !street[0].clDir))
			{
				return street;
			}
		}
		//System.out.println("Util.getPedStreet(): no result – v1: " + v1 + " v2: " + v2);
		return null;
	}
}
