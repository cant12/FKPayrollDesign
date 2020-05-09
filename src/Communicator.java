import java.util.*;

public interface Communicator
{
	Date get_date();

	void set_date(Date date);

	void add_employee(Employee emp);

	Employee get_employee(int id);

	void update_employee(Employee emp);

	boolean check_login(String username, String password);

	void post_sale_record(int id, double sales, Date date);

	void post_time_card(int id, int hours, Date date);
}