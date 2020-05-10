import java.util.*;
import java.sql.*;

class SqlComm implements Communicator
{
	private String url;
	private String username;
	private String password;
	private Connection conn;
	private Statement stmt;

	private Date string_to_date(String date_str,String day)
	{
		String[] arr =  date_str.split("-");
		arr[2] = arr[2].split(" ",2)[0];
		return new Date(Integer.parseInt(arr[2]),Integer.parseInt(arr[1]),Integer.parseInt(arr[0]),day);
	}

	SqlComm(String url,String username,String password)
	{
		this.url = url;
		this.username = username;
		this.password = password;
		try
		{
			conn = DriverManager.getConnection(url,username,password);	
			stmt = conn.createStatement();
		}
		catch(Exception e){System.out.println(e);}
	}

	public Date get_date()
	{
		try
		{
			ResultSet rs = stmt.executeQuery("select date,day from constants");
			rs.next();
			String date_str = rs.getString(1);
			String day = rs.getString(2);
			rs.close();
			return string_to_date(date_str,day);
		}
		catch(Exception e){System.out.println(e);}
		return null;
	}

	public void set_date(Date date)
	{
		try
		{
			String date_str = date.to_string();
			String day = date.get_today();
			stmt.executeUpdate("update constants set date = \'"+date_str+"\', day = \'"+day+"\'");
		}
		catch(Exception e){System.out.println(e);}
	}

	public void add_employee(Employee emp)
	{
		try
		{
			String insert_statement = "insert into employees (employee_id,name,password,date_of_joining,hourly,monthly,incentive,method_of_payment,union_membership) values ((select max(employee_id) from employees)+1,\'"+emp.get_name()+"\',\'"+emp.get_password()+"\',(select date from constants),"+emp.get_hourly()+","+emp.get_monthly()+","+emp.get_incentive()+",\'"+emp.get_method_of_payment()+"\',"+emp.get_union_membership()+")";
			stmt.executeUpdate(insert_statement);
		}
		catch(Exception e){System.out.println(e);}
	}

	public Employee get_employee(int id)
	{
		try
		{
			ResultSet rs = stmt.executeQuery("select * from employees where employee_id = "+id);
			rs.next();
			Date date = string_to_date(rs.getString(4),"");
			Employee emp = new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),date,rs.getDouble(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getBoolean(9));
			rs.close();
			return emp;
		}
		catch(Exception e){System.out.println(e);return null;}	
	}

	public void update_employee(Employee emp)
	{
		try
		{
			String update_statement = "update employees set name = \'"+emp.get_name()+"\', password = \'"+emp.get_password()+"\', hourly = "+emp.get_hourly()+", monthly = "+emp.get_monthly()+", incentive = "+emp.get_incentive()+", method_of_payment = \'"+emp.get_method_of_payment()+"\', union_membership = "+emp.get_union_membership()+" where employee_id = "+emp.get_id();
			stmt.executeUpdate(update_statement);
		}
		catch(Exception e){System.out.println(e);}
	}

	// boolean check_login(String username, String password);

	// void post_time_card(TimeCard tc);

	// void post_sale_record(SaleRecord sr);

	// void post_union_report(UnionReport ur);

	// ArrayList<TimeCard> get_timecards_of(Employee emp, Date date);

	// ArrayList<TimeCard> get_timecards_of(Employee emp);

	// ArrayList<SalesRecord> get_salesrecords_of(Employee emp, Date date);

	// ArrayList<SalesRecord> get_salesrecords_of(Employee emp);

	// void make_transaction(Transaction trans);

	// ArrayList<Transaction> get_all_transactions_of(Employee emp, Date date);

	// ArrayList<Transaction> get_all_transactions_of(Employee emp);

	// Date get_last_transaction_date_of(Employee emp);

	void close()
	{
		try
		{
			stmt.close();
			conn.close();
		}	
		catch(Exception e){System.out.println(e);}	
	}
}