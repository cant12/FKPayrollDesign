import java.util.*;

public interface Communicator
{
	public Date get_date();

	public void set_date(Date date);

	public void add_employee(Employee emp);

	public Employee get_employee(int id);

	public void update_employee(Employee emp);

	public ArrayList<Integer> get_all_employee_ids();

	public boolean check_login(int id, String password);

	public void post_time_card(TimeCard tc);

	public void post_sales_record(SalesRecord sr);

	public void post_union_report(UnionReport ur);

	public void set_membership_fee_per_week(double fee);

	public default ArrayList<TimeCard> get_timecards_of(int id, Date date){return null;}

	public ArrayList<TimeCard> get_timecards_of(int id);

	public default ArrayList<SalesRecord> get_salesrecords_of(int id, Date date){return null;}

	public ArrayList<SalesRecord> get_salesrecords_of(int id);

	public ArrayList<UnionReport> get_union_reports(Date date);

	public void make_transaction(Transaction trans);

	public default ArrayList<Transaction> get_transactions_of(int id, Date date){return null;}

	public ArrayList<Transaction> get_transactions_of(int id);

	public Date get_last_transaction_date_of(int id);

	public void close();
}