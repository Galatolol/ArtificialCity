
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
			hours = hours + 1;
			minutesToAdd = minutes + minutesToAdd % 60;
			minutes = minutesToAdd;
		}
		else
		{
			minutes = minutes + minutesToAdd;
		}
		return hours.toString() + ":" + minutes.toString();
	}
}
