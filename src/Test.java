import java.util.*;

class Test
{
	public static void main(String args[])
	{
		System.out.println("This is the testing file!");
		Date date = new Date(10,5,2020,"Sun");
		while(true)
		{
			System.out.println(date.get_day()+" "+date.get_month()+" "+date.get_year()+" "+date.get_today());
			if(date.is_last_workday())
				System.out.println("LAST");
			if(date.get_year()!=2020)
				break;
			date.inc_day();
		}
	}
}
	