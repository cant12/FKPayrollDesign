import java.util.*;

class Payroll
{
	private Communicator comm;

	Payroll(Communicator comm)
	{
		this.comm = comm;
	}

	private double get_sal_hr(int hours, double rate)
	{
		if(hours<=8)
			return rate*hours;
		else
			return rate*8 + 1.5*rate*(hours-8);
	} 

	Transaction run_payroll_employee(Employee emp)
	{
		int id = emp.get_id();
		Date last = comm.get_last_transaction_date_of(id);
		Date current_date = comm.get_date();
		double amount = 0.0;
		if(emp.get_hourly()>-1)
		{
			ArrayList<TimeCard> tcs;
			if(last==null)
				tcs = comm.get_timecards_of(id);
			else
				tcs = comm.get_timecards_of(id,last);
			for(TimeCard tc : tcs)
				amount += get_sal_hr(tc.get_hours(),emp.get_hourly());		  
		}
		else 
		{
			if(current_date.is_last_workday())
				amount += emp.get_monthly();
			if(current_date.get_today().equals("Fri"))
			{
				ArrayList<SalesRecord> srs;
				if(last==null)
					srs = comm.get_salesrecords_of(id);
				else
					srs = comm.get_salesrecords_of(id,last);
				for(SalesRecord sr : srs)
					amount += sr.get_sales()*emp.get_incentive()/100 ;
			}
		}

		if(emp.get_union_membership() && current_date.get_today().equals("Fri"))
		{
			amount -= comm.get_membership_fee_per_week();
			ArrayList<UnionReport> urs;
			if(last==null)
				urs = comm.get_union_reports();
			else
				urs = comm.get_union_reports(last);
			for(UnionReport ur : urs)
				amount -= ur.get_fee();
		}

		Transaction answer = new Transaction(current_date,amount,id,emp.get_method_of_payment());
		return answer;
	} 

	ArrayList<Transaction> run_payroll()
	{
		ArrayList<Transaction> answer = new ArrayList<Transaction>();
		ArrayList<Integer> ids = comm.get_all_employee_ids();
		for(int i : ids)
		{
			Employee emp = comm.get_employee(i);
			answer.add(run_payroll_employee(emp));
		}	
		return answer;
	}
}