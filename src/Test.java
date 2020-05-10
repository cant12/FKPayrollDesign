import java.util.*;

class Test
{
	public static void main(String args[])
	{
		System.out.println("This is the testing file!");
		String url = "jdbc:postgresql://localhost:5432/fkpayroll";
		String username = "postgres";
		String password = "HVH";	
		SqlComm comm = new SqlComm(url,username,password);
		// Date date = comm.get_date();
		// date.inc_day();
		// comm.set_date(date);
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
		// Employee emp = new Employee(-1,"Vikki","vik32",null,700,-1,0,"Bank",true);
		Employee emp = comm.get_employee(3);
		emp.set_name("MacBoy");
		emp.set_password("vik333");
		comm.update_employee(emp);
		System.out.println(emp.get_name()+" "+emp.get_monthly());
		comm.close();
	}
}
	