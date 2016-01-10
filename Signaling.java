class Signaling
{
	private static boolean signaling1;
	public static void signaling()
	{
		if ((signaling1 == false && ContentPanel.counter % 20 == 0) || (signaling1 == true && ContentPanel.counter % 10 == 0))
		{
			signaling1 = !signaling1;
			System.out.println(signaling1);
			
			Lane[] street = Util.getStreet(20, 76);
			Lane[] street1 = Util.getStreet(81, 80);
			Lane[] street2 = Util.getStreet(78, 79);
			
			Lane[] street3 = Util.getStreet(15, 17);
			Lane[] street4 = Util.getStreet(19, 18);
			Lane[] street5 = Util.getStreet(19, 17);
			
			Lane[] street6 = Util.getStreet(21, 17);
			Lane[] street7 = Util.getStreet(17, 18);
			Lane[] street8 = Util.getStreet(18, 17);
			Lane[] street9 = Util.getStreet(42, 18);
			
			Lane[] street10 = Util.getStreet(16, 13);
			Lane[] street11 = Util.getStreet(4, 14);
			
			Lane[] street12 = Util.getStreet(12, 13);
			Lane[] street13 = Util.getStreet(13, 14);
			Lane[] street14 = Util.getStreet(14, 13);
			Lane[] street15 = Util.getStreet(3, 14);
	
			int length = street[0].cellList.length;
			for (int i = 0; i < street.length; i++)
			{
				street[i].cellList[length - 1].setOccupied(signaling1);
				street[i].cellList[length - 2].setOccupied(signaling1);
			}
			length = street1[0].cellList.length;
			for (int i = 0; i < street1.length; i++)
			{
				street1[i].cellList[length - 1].setOccupied(signaling1);
				street1[i].cellList[length - 2].setOccupied(signaling1);
			}
			length = street2[0].cellList.length;
			for (int i = 0; i < street2.length; i++)
			{
				street2[i].cellList[length - 1].setOccupied(!signaling1);
				street2[i].cellList[length - 2].setOccupied(!signaling1);
			}
			
			
			length = street3[0].cellList.length;
			for (int i = 0; i < street3.length; i++)
			{
				street3[i].cellList[length - 1].setOccupied(signaling1);
				street3[i].cellList[length - 2].setOccupied(signaling1);
			}
			length = street4[0].cellList.length;
			for (int i = 0; i < street4.length; i++)
			{
				street4[i].cellList[length - 1].setOccupied(signaling1);
				street4[i].cellList[length - 2].setOccupied(signaling1);
			}
			length = street5[0].cellList.length;
			for (int i = 0; i < street5.length; i++)
			{
				street5[i].cellList[length - 1].setOccupied(signaling1);
				street5[i].cellList[length - 2].setOccupied(signaling1);
			}
			length = street5[0].cellList.length;
			for (int i = 0; i < street5.length; i++)
			{
				street5[i].cellList[length - 1].setOccupied(!signaling1);
				street5[i].cellList[length - 2].setOccupied(!signaling1);
			}
			length = street6[0].cellList.length;
			for (int i = 0; i < street6.length; i++)
			{
				street6[i].cellList[length - 1].setOccupied(!signaling1);
				street6[i].cellList[length - 2].setOccupied(!signaling1);
			}
			
			
			length = street10[0].cellList.length;
			for (int i = 0; i < street10.length; i++)
			{
				street10[i].cellList[length - 1].setOccupied(signaling1);
				street10[i].cellList[length - 2].setOccupied(signaling1);
			}
			length = street11[0].cellList.length;
			for (int i = 0; i < street11.length; i++)
			{
				street11[i].cellList[length - 1].setOccupied(signaling1);
				street11[i].cellList[length - 2].setOccupied(signaling1);
			}
			length = street12[0].cellList.length;
			for (int i = 0; i < street12.length; i++)
			{
				street12[i].cellList[length - 1].setOccupied(!signaling1);
				street12[i].cellList[length - 2].setOccupied(!signaling1);
			}
			length = street13[0].cellList.length;
			for (int i = 0; i < street13.length; i++)
			{
				street13[i].cellList[length - 1].setOccupied(!signaling1);
				street13[i].cellList[length - 2].setOccupied(!signaling1);
			}
			length = street14[0].cellList.length;
			for (int i = 0; i < street14.length; i++)
			{
				street14[i].cellList[length - 1].setOccupied(!signaling1);
				street14[i].cellList[length - 2].setOccupied(!signaling1);
			}
			length = street15[0].cellList.length;
			for (int i = 0; i < street15.length; i++)
			{
				street15[i].cellList[length - 1].setOccupied(!signaling1);
				street15[i].cellList[length - 2].setOccupied(!signaling1);
			}
		}
	}	
}




