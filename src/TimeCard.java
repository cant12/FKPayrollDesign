import java.util.*;

class TimeCard
{
	private Date date;
	private int employee_id,hours;

	TimeCard(Date date,int employee_id, int hours)
	{
		this.date = date;
		this.employee_id = employee_id;
		this.hours = hours;
	}

	Date get_date(){return date;}

	int get_employee_id(){return employee_id;}

	int get_hours(){return hours;}
}