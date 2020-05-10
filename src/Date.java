import java.util.*;

class Date
{
	private int day,month,year,today;	
	private int[] last_days = {31,28,31,30,31,30,31,31,30,31,30,31};
	private static final String[] days = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
	private static final Map<String,Integer> days_map = Map.of("Mon",0,"Tue",1,"Wed",2,"Thu",3,"Fri",4,"Sat",5,"Sun",6);

	Date(int day, int month, int year, String today)
	{
		this.day = day;
		this.month = month;
		this.year = year;
		this.today = days_map.get(today);
		if(year%400==0 || (year%100!=0 && year%4==0))//leap year
			last_days[1] = 29;
	}

	int get_day(){return day;}

	int get_month(){return month;}//month = [1,12]

	int get_year(){return year;}

	String get_today(){return days[today];}

	boolean is_last_day(){return day == last_days[month-1];}

	void inc_day()
	{
		if(is_last_day())
		{
			day = 1;
			if(month==12)
			{
				month = 1;
				year++;
			}
			else
				month++;
		}
		else
			day++;
		if(today==6)
			today=0;
		else
			today++;
	}

	boolean is_last_workday()
	{
		if(today<4)
			return is_last_day();
		else if(today==4)
		{
			boolean b0,b1,b2;
			Date temp = new Date(day,month,year,days[today]);
			b0 = temp.is_last_day();
			temp.inc_day();
			b1 = temp.is_last_day();
			temp.inc_day();
			b2 = temp.is_last_day();
			return b0||b1||b2;
		}
		else
			return false;
	}
}