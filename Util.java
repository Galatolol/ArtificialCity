import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Util {
	private static ArrayList<Lane[]> streetList = new ArrayList<Lane[]>();
	private static ArrayList<Lane[]> pedestriansStreetList = new ArrayList<Lane[]>();

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

	public static boolean isBusStop(Lane lane, int cellNr) {
		if (lane.begin.toString().equals("13")
				&& lane.end.toString().equals("5") && cellNr == 6) {
			return true;
		} else if (lane.begin.toString().equals("14")
				&& lane.end.toString().equals("15") && cellNr == 6) {
			return true;
		} else if (lane.begin.toString().equals("22")
				&& lane.end.toString().equals("21") && cellNr == 17) {
			return true;
		}
		return false;
	}

	// ------------------------------------------------------

	public static boolean isTramStop(Lane lane, int cellNr) {
		if (lane.begin.toString().equals("3")
				&& lane.end.toString().equals("14") && cellNr == 20) {
			return true;
		} else if (lane.begin.toString().equals("12")
				&& lane.end.toString().equals("13") && cellNr == 16) {
			return true;
		} else if (lane.begin.toString().equals("32")
				&& lane.end.toString().equals("40") && cellNr == 15) {
			return true;
		} else if (lane.begin.toString().equals("54")
				&& lane.end.toString().equals("55") && cellNr == 40) {
			return true;
		} else if (lane.begin.toString().equals("56")
				&& lane.end.toString().equals("55") && cellNr == 30) {
			return true;
		} else if (lane.begin.toString().equals("58")
				&& lane.end.toString().equals("55") && cellNr == 20) {
			return true;
		} else if (lane.begin.toString().equals("53")
				&& lane.end.toString().equals("40") && cellNr == 65) {
			return true;
		}
		return false;
	}
	
	//--------------------------------------------------------
	
	public static Lane[] createPedestriansStreets(PedestriansGraph pGraph)
	{
		E edge = pGraph.getEdge(6, 7);  System.out.println("dupa1");
		Lane[] street6_7 = new Lane[1]; System.out.println("dupa2");
		street6_7[0] = edge.street[0]; System.out.println("dupa3");
		
		pedestriansStreetList.add(street6_7); System.out.println("dupa4");

		// -------------------------------------------

		edge = pGraph.getEdge(7, 10);
		Lane[] street7_10 = new Lane[1];
		street7_10[0] = edge.street[0];
		pedestriansStreetList.add(street7_10);
		
		
		
		
		
		
		
		//-----------------------------------------
		
		street6_7[0].forward = street7_10;
		return street6_7;
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

		// -------------------------------------------

		edge = myGraph.getEdge(13, 14);
		edge.street[0].setSpeedLimit(2);
		for (int k = 0; k < edge.street[0].cellList.length; k++) {
			edge.street[0].cellList[k].setForbidden(true); // tramwaj
		}
		Lane[] street13_14 = new Lane[1];
		street13_14[0] = edge.street[0];

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
		System.out.println("Util.getStreet(): no result – v1: " + v1 + " v2: "
				+ v2);
		return null;
	}
}
