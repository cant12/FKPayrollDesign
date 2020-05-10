create table Constants
(
	Date timestamp,
	Day text,
	Union_Mebership_Fee real
);

create table Employees
(
	Employee_ID int, 
	Name text,
	Password text,
	Date_of_joining timestamp,
	Hourly real,
	Monthly real,
	Incentive real,
	Method_of_Payment text,
	Union_Membership boolean
);

create table Union_report
(
	Due_Date timestamp,
	Charges_Per_Member real
);

create table Timecards
(
	Date timestamp,
	Hours_worked int
);

create table Salesreports
(
	Date timestamp,
	Value_Sold real
);

create table Transactions
(
	Date timestamp,
	Money_Transfered real,
	Employee_ID int,
	Method_of_Payment text
);

insert into constants (Date,Day) values ('5.10.2020','Sun');
insert into Employees (Employee_ID,Name,Password,Date_of_joining) values (0,'ADMIN','admin1234','5.10.2020');	

-- update constants set date = '5.10.2020', day = 'Sun';	