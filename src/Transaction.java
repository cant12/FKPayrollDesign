import java.util.*;

class Transaction
{
	private Date date;
	private double amount;
	private int employee_id;
	private String mode;

	Transaction(Date date, double amount, int employee_id, String mode)
	{
		this.date = date;
		this.employee_id = employee_id;
		this.amount = amount;
		this.mode = mode;
	}

	Date get_date(){return date;}

	int get_employee_id(){return employee_id;}

	double get_amount(){return amount;}

	String get_mode(){return mode;}
}