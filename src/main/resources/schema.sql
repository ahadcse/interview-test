create table if not exists users (
username varchar(256),
password varchar(256),
enabled boolean
);
create table if not exists authorities (
username varchar(256),
authority varchar(256)
);


create table if not exists itemlist (
	itemname varchar(80),
	itemprice double ,
	itemowner varchar(80),
	itemamount int
);

create table if not exists customer (
	CUSTNAME varchar(80),
    CUSTPASS	varchar(80),
    TOTITEMSSOLD int,
    TOTITEMSBOUGHT int
);

create table if not exists wishlist (
	itemname varchar(80),
    itemprice double,
    itemowner varchar(80),
    itemamount int
);
