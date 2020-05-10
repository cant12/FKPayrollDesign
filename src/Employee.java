import java.util.*;

class Employee
{
	private int id;
	private String name,password,method_of_payment;
	private double monthly,hourly,incentive;
	private Date joining_date;
	private boolean union_membership;

	Employee(int id, String name, String password, Date joining_date, double hourly, double monthly, double incentive, String method_of_payment, boolean union_membership)
	{
		this.id = id;
		this.name = name;
		this.password = password;
		this.method_of_payment = method_of_payment;
		this.monthly = monthly;
		this.hourly = hourly;
		this.incentive = incentive;
		this.joining_date = joining_date;
		this.union_membership = union_membership;
	}

	int get_id(){return id;}

	String get_name(){return name;}

	String get_password(){return password;}

	String get_method_of_payment(){return method_of_payment;}

	double get_monthly(){return monthly;}

	double get_hourly(){return hourly;}

	double get_incentive(){return incentive;}

	Date get_joining_date(){return joining_date;}

	boolean get_union_membership(){return union_membership;}

	void set_name(String name){this.name = name;}

	void set_password(String password){this.password = password;}

	void set_hourly(double hourly){this.hourly = hourly;}

	void set_monthly(double monthly){this.monthly = monthly;}

	void set_incentive(double incentive){this.incentive = incentive;}

	void set_method_of_payment(String method_of_payment){this.method_of_payment = method_of_payment;}

	void set_union_membership(boolean union_membership){this.union_membership = union_membership;}
}