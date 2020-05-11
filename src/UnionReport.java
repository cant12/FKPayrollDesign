import java.util.*;

class UnionReport
{
	private Date date;
	private String event;
	private double fee;

	UnionReport(Date date,String event, double fee)
	{
		this.date = date;
		this.event = event;
		this.fee = fee;
	}

	Date get_date(){return date;}

	String get_event(){return event;}

	double get_fee(){return fee;}
}