create database HotelDB;
use HotelDB;
create table costumer(
costumerid int primary key auto_increment not null,
costumeremail varchar(256)  not null,
costumerFirstName varchar(128) not null,
costumerLastName varchar(128) not null,
costumerPhone varchar(256) not null,
costumerPassword varchar(256) not null);

 create table CostumerPermission(CostumerId int,
 Permission varchar(128),
  FOREIGN KEY (CostumerId) REFERENCES costumer(costumerid) 
);

create table CostumerLogin(CostumerId int,
loginTime datetime,
logoutTime datetime,
FOREIGN KEY (CostumerId) REFERENCES costumer(costumerid)
);
  
create table RoomDetails(
RoomKind varchar(128) primary key,
price float,
details varchar(1024)
);
  
insert into RoomDetails values("Standard",95,
"Standard room Twin bed, Guest room, 1 Twin/Single Bed");

insert into RoomDetails values("Deluxe King Bed",120,
"Deluxe Room King bed, 1 King bed");

insert into RoomDetails values("Deluxe Twin Beds",120,
"Deluxe Room Twin beds, 2 Twin/Single Beds");

insert into RoomDetails values("Suite", 150,
"Suite Room King bed, Larger Guest room, 1 King");

create table Room(
RoomNumber varchar(128) primary key not null,
RoomKind varchar(128) not null,
FOREIGN KEY (RoomKind) REFERENCES RoomDetails(RoomKind));

insert into Room values(101,"Standard");
insert into Room values(102,"Deluxe King Bed");
insert into Room values(103,"Deluxe Twin Beds");
insert into Room values(104,"Suite");
insert into Room values(105,"Standard");

create table roomReservation(
ReservationId int auto_increment primary key,
costumerid int not null,
RoomNumber varchar(128) not null,
FromDate date not null,
ToDate  date not null,
 FOREIGN KEY (costumerid) REFERENCES costumer(costumerid),
 FOREIGN KEY (RoomNumber) REFERENCES Room(RoomNumber));
 
insert into roomReservation (costumerId,RoomNumber,fromDate, toDate) values(1,"101",'2019-02-03', '2019-02-15');
insert into roomReservation (costumerId,RoomNumber,fromDate, toDate) values(1,"102",'1990-02-03', '1990-02-15');
insert into roomReservation (costumerId,RoomNumber,fromDate, toDate) values(1,"103",'1990-02-03', '1990-02-15');
insert into roomReservation (costumerId,RoomNumber,fromDate, toDate) values(1,"104",'1990-02-03', '1990-02-15');
insert into roomReservation (costumerId,RoomNumber,fromDate, toDate) values(1,"105",'1990-02-03', '1990-02-15');

create table Roles ( role_name varchar(128) primary key not null);
insert into Roles values('General Manger');
insert into Roles values('root.restaurant Manger');
insert into Roles values('root.restaurant Employee');


create table employee(
employeeid int primary key auto_increment not null,
employeeemail varchar(256)  not null,
employeeFirstName varchar(128) not null,
employeeLastName varchar(128) not null,
employeePhone varchar(256) not null,
employeePassword varchar(256) not null,
employeeRole varchar(128) not null references Role(role_name)
);

create table RolePermission(
employeeid int references Role(employee) ,
employeeRole varchar(128) not null references Role(role_name)
);

create table menu (
ItemName varchar(128) primary key  not null,
ItemPrice float not null,
section varchar(128) not null);
 
drop table RestaurantOrder;
create table RestaurantOrder(
orderId int primary key auto_increment not null,
costumerid int,
ReservationId int references roomReservation(ReservationId),
orderTotal Float,
status varchar(64),
FOREIGN KEY (costumerid) REFERENCES costumer(costumerid)
);

create table OrderItems(
orderId int REFERENCES ResturantOrder(orderId) ,
ItemName varchar(128),
quantity int,
FOREIGN KEY (ItemName) REFERENCES menu(ItemName)
);

 insert into menu values("hamburger",2,"sandwichs");
  insert into menu values("club sandwich",7,"sandwichs");
  insert into menu values("water",1,"bevreges");
  insert into menu values("soda",2,"bevreges");
  insert into menu values("mac and cheese ",1,"kids");


