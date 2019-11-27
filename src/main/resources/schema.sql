

create table if not exists exchange_rates
(
	table_version varchar(3) null,
	rate_id varchar(30) not null ,
	trading_date date null,
	effective_date date null,
	constraint exchange_rates_pk
		primary key (rate_id)
);

create table if not exists actual_curriences
(
	currency_id int auto_increment,
	currency varchar(50) null,
	code varchar(5) null,
	mid double null,
	bid double null,
	ask double null,
	rate_id varchar(30) null,
	constraint actual_curriences_pk
		primary key (currency_id),
	constraint actual_curriences_exchange_rates_rate_id_fk
		foreign key (rate_id) references exchange_rates (rate_id)
);

create table if not exists request_logs
(
	log_id int auto_increment,
	type varchar(10) not null,
	url varchar(300) not null,
	url_parameters varchar(30) null,
	response_code int null,
	request_time timestamp default CURRENT_TIMESTAMP,
	constraint request_logs_pk
		primary key (log_id)
);
