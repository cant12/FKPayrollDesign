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
		if(date_str==null)
			return null;
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

	public void add_employee(Employee emp) //shouold return int
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

	public ArrayList<Integer> get_all_employee_ids()
	{
		try
		{
			ResultSet rs = stmt.executeQuery("select employee_id from employees where employee_id != 0");
			ArrayList<Integer> answer = new ArrayList<Integer>();
			while(rs.next())
			{
				answer.add(rs.getInt(1));
			}
			rs.close();
			return answer;
		}
		catch(Exception e){System.out.println(e);}	
		return null;	
	}

	public boolean check_login(int id, String password)
	{
		try
		{
			ResultSet rs = stmt.executeQuery("select password from employees where employee_id = "+id);
			rs.next();
			String pass = rs.getString(1);
			rs.close();
			return password.equals(pass);
		}
		catch(Exception e){System.out.println(e);}	
		return false;
	}

	public void post_time_card(TimeCard tc)
	{
		try
		{
			String insert_statement = "insert into timecards (date,employee_id,hours_worked) values (\'"+tc.get_date().to_string()+"\',"+tc.get_employee_id()+","+tc.get_hours()+")";
			stmt.executeUpdate(insert_statement);
		}
		catch(Exception e){System.out.println(e);}
	}

	public void post_sales_record(SalesRecord sr)
	{
		try
		{
			String insert_statement = "insert into salesrecords (date,employee_id,sales) values (\'"+sr.get_date().to_string()+"\',"+sr.get_employee_id()+","+sr.get_sales()+")";
			stmt.executeUpdate(insert_statement);
		}
		catch(Exception e){System.out.println(e);}
	}

	public void post_union_report(UnionReport ur)
	{
		try
		{
			String insert_statement = "insert into unionreports (date_of_event,event,charges_per_member) values (\'"+ur.get_date().to_string()+"\',\'"+ur.get_event()+"\',"+ur.get_fee()+")";
			stmt.executeUpdate(insert_statement);
		}
		catch(Exception e){System.out.println(e);}
	}

	public void set_membership_fee_per_week(double fee)
	{
		try
		{
			String update_statement = "update constants set union_membership_fee_per_week = "+fee;
			stmt.executeUpdate(update_statement);
		}
		catch(Exception e){System.out.println(e);}	
	}

	public double get_membership_fee_per_week()
	{
		try
		{
			ResultSet rs = stmt.executeQuery("select union_membership_fee_per_week from constants");
			rs.next();
			double ans = rs.getDouble(1);
			rs.close();
			return ans;
		}
		catch(Exception e){System.out.println(e);}
		return -1;
	}

	public ArrayList<TimeCard> get_timecards_of(int id, Date date)
	{
		try
		{
			ResultSet rs = stmt.executeQuery("select * from timecards where employee_id = "+id+" and date > \'"+date.to_string()+"\'");
			ArrayList<TimeCard> answer = new ArrayList<TimeCard>();
			while(rs.next())
			{
				TimeCard temp = new TimeCard(string_to_date(rs.getString(1),""),rs.getInt(2),rs.getInt(3));
				answer.add(temp);
			}
			rs.close();
			return answer;
		}
		catch(Exception e){System.out.println(e);}	
		return null;
	}

	public ArrayList<TimeCard> get_timecards_of(int id)
	{
		try
		{
			ResultSet rs = stmt.executeQuery("select * from timecards where employee_id = "+id);
			ArrayList<TimeCard> answer = new ArrayList<TimeCard>();
			while(rs.next())
			{
				TimeCard temp = new TimeCard(string_to_date(rs.getString(1),""),rs.getInt(2),rs.getInt(3));
				answer.add(temp);
			}
			rs.close();
			return answer;
		}
		catch(Exception e){System.out.println(e);}	
		return null;
	}

	public ArrayList<SalesRecord> get_salesrecords_of(int id, Date date)
	{
		try
		{
			ResultSet rs = stmt.executeQuery("select * from salesrecords where employee_id = "+id+" and date > \'"+date.to_string()+"\'");
			ArrayList<SalesRecord> answer = new ArrayList<SalesRecord>();
			while(rs.next())
			{
				SalesRecord temp = new SalesRecord(string_to_date(rs.getString(1),""),rs.getInt(2),rs.getDouble(3));
				answer.add(temp);
			}
			rs.close();
			return answer;
		}
		catch(Exception e){System.out.println(e);}	
		return null;
	}

	public ArrayList<SalesRecord> get_salesrecords_of(int id)
	{
		try
		{
			ResultSet rs = stmt.executeQuery("select * from salesrecords where employee_id = "+id);
			ArrayList<SalesRecord> answer = new ArrayList<SalesRecord>();
			while(rs.next())
			{
				SalesRecord temp = new SalesRecord(string_to_date(rs.getString(1),""),rs.getInt(2),rs.getDouble(3));
				answer.add(temp);
			}
			rs.close();
			return answer;
		}
		catch(Exception e){System.out.println(e);}	
		return null;
	}

	public ArrayList<UnionReport> get_union_reports(Date date)
	{
		try
		{
			ResultSet rs = stmt.executeQuery("select * from unionreports where date_of_event > \'"+date.to_string()+"\'");
			ArrayList<UnionReport> answer = new ArrayList<UnionReport>();
			while(rs.next())
			{
				UnionReport temp = new UnionReport(string_to_date(rs.getString(1),""),rs.getString(2),rs.getDouble(3));
				answer.add(temp);
			}
			rs.close();
			return answer;
		}
		catch(Exception e){System.out.println(e);}	
		return null;
	}

	public ArrayList<UnionReport> get_union_reports()
	{
		try
		{
			ResultSet rs = stmt.executeQuery("select * from unionreports");
			ArrayList<UnionReport> answer = new ArrayList<UnionReport>();
			while(rs.next())
			{
				UnionReport temp = new UnionReport(string_to_date(rs.getString(1),""),rs.getString(2),rs.getDouble(3));
				answer.add(temp);
			}
			rs.close();
			return answer;
		}
		catch(Exception e){System.out.println(e);}	
		return null;
	}

	public void make_transaction(Transaction trans)
	{
		try
		{
			String insert_statement = "insert into transactions (date,money_transferred,employee_id,method_of_payment) values (\'"+trans.get_date().to_string()+"\',"+trans.get_amount()+","+trans.get_employee_id()+",\'"+trans.get_mode()+"\')";
			stmt.executeUpdate(insert_statement);
		}
		catch(Exception e){System.out.println(e);}	
	}

	// ArrayList<Transaction> get_all_transactions_of(int id, Date date);

	public ArrayList<Transaction> get_transactions_of(int id)
	{
		try
		{
			ResultSet rs = stmt.executeQuery("select * from transactions where employee_id = "+id);
			ArrayList<Transaction> answer = new ArrayList<Transaction>();
			while(rs.next())
			{
				Transaction temp = new Transaction(string_to_date(rs.getString(1),""),rs.getDouble(2),rs.getInt(3),rs.getString(4));
				answer.add(temp);
			}
			rs.close();
			return answer;
		}
		catch(Exception e){System.out.println(e);}	
		return null;
	}

	public Date get_last_transaction_date_of(int id)
	{
		try
		{
			ResultSet rs = stmt.executeQuery("select max(date) from transactions where employee_id = "+id);
			rs.next();
			String date_str = rs.getString(1);
			rs.close();
			return string_to_date(date_str,"");
		}
		catch(Exception e){System.out.println(e);}
		return null;
	}

	public void close()
	{
		try
		{
			stmt.close();
			conn.close();
		}	
		catch(Exception e){System.out.println(e);}	
	}
}