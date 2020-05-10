import java.util.*;

public interface Communicator
{
	public Date get_date();

	public void set_date(Date date);

	void add_employee(Employee emp);

	Employee get_employee(int id);

	void update_employee(Employee emp);

	boolean check_login(String username, String password);

	void post_time_card(TimeCard tc);

	void post_sale_record(SaleRecord sr);

	void post_union_report(UnionReport ur);

	ArrayList<TimeCard> get_timecards_of(Employee emp, Date date);

	ArrayList<TimeCard> get_timecards_of(Employee emp);

	ArrayList<SalesRecord> get_salesrecords_of(Employee emp, Date date);

	ArrayList<SalesRecord> get_salesrecords_of(Employee emp);

	void make_transaction(Transaction trans);

	ArrayList<Transaction> get_all_transactions_of(Employee emp, Date date);

	ArrayList<Transaction> get_all_transactions_of(Employee emp);

	Date get_last_transaction_date_of(Employee emp);
}