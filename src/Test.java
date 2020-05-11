import java.util.*;

class Test
{
	public static void main(String args[])
	{
		System.out.println("This is the testing file!");
		String url = "jdbc:postgresql://localhost:5432/fkpayroll";
		String username = "postgres";
		String password = "HVH";	
		Communicator comm = new SqlComm(url,username,password);
		Date date = comm.get_date();
		date.inc_day();
		comm.set_date(date);
		// date = comm.get_date();
		// while(true)
		// {
		// 	System.out.println(date.get_day()+" "+date.get_month()+" "+date.get_year()+" "+date.get_today());
		// 	if(date.is_last_workday())
		// 		System.out.println("LAST");
		// 	// if(date.get_year()!=2020)
		// 		break;
		// 	// date.inc_day();
		// }
		// Employee emp = new Employee(-1,"Mac","mac13",null,-1,50000,10,"card",false);
		// Employee emp = comm.get_employee(4);
		// emp.set_name("MacBoy");
		// emp.set_password("mac333");
		// comm.update_employee(emp);
		// System.out.println(emp.get_name()+" "+emp.get_monthly());

		// System.out.println(comm.check_login(3,"vik333"));

		// date.inc_day();
		// comm.set_date(date);
		// SalesRecord sc = new SalesRecord(date,3,1200);
		// comm.post_sales_record(sc);

		// SalesRecord sr = new SalesRecord(date,3,1000);
		// comm.post_sales_record(sr);

		// UnionReport ur = new UnionReport(date,"Festember",300);
		// comm.post_union_report(ur);

		// comm.set_membership_fee_per_week(500);

		// Employee emp = comm.get_employee(3);
		// Transaction trans = new Transaction(date,4000,emp.get_id(),emp.get_method_of_payment());
		// comm.make_transaction(trans);

		// Date date_1 = new Date(17,5,2020,"");
		Payroll proll = new Payroll(comm);
		ArrayList<Transaction> tcs = proll.run_payroll();

		for(Transaction tc : tcs)
			System.out.println(tc.get_date().to_string()+" "+date.get_today()+" "+tc.get_employee_id()+" "+tc.get_amount()+" "+tc.get_mode());

		comm.close();
	}
}
	