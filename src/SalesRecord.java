import java.util.*;

class SalesRecord
{
	private Date date;
	private int employee_id;
	private double sales;

	SalesRecord(Date date,int employee_id, double sales)
	{
		this.date = date;
		this.employee_id = employee_id;
		this.sales = sales;
	}

	Date get_date(){return date;}

	int get_employee_id(){return employee_id;}

	double get_sales(){return sales;}
}