use BestDealDB;
drop table Accounts;
drop table CustomerOrders;
create table Accounts(
UserID INT NOT NULL auto_increment,
UserName varchar(40) NOT NULL,
Password varchar(40) NOT NULL,
Usertype varchar(40) NOT NULL,
PRIMARY KEY(UserID)
);

create table CustomerOrders
(
OrderId integer NOT NULL auto_increment,
userName varchar(40) NOT NULL,
orderName varchar(40) NOT NULL,
orderPrice double NOT NULL,
userAddress varchar(40) NOT NULL,
creditCard varchar(40) NOT NULL,
primary key(orderid,userName,orderName)
);

INSERT INTO Accounts(UserName, Password, Usertype)
Values
('test', 'pass', 'customer'),
('bob', 'pass', 'retailer'),
('manager', 'pass', 'manager');