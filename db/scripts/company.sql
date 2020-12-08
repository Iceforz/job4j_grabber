create database company;
CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer,
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name) values (1, 'BBC');
insert into company(id, name) values (2, 'Amazon');
insert into company(id, name) values (3, 'Twitch');
insert into company(id, name) values (4, 'Google');
insert into company(id, name) values (5, 'Apple');

insert into person(id, name, company_id) values(1, 'Mark', 1);
insert into person(id, name, company_id) values(2, 'Kent', 1);
insert into person(id, name, company_id) values(3, 'Ivan', 2);
insert into person(id, name, company_id) values(4, 'Vin', 3);
insert into person(id, name, company_id) values(5, 'Stacey', 3);
insert into person(id, name, company_id) values(6, 'Liam', 4);
insert into person(id, name, company_id) values(7, 'Paul', 5);
insert into person(id, name, company_id) values(8, 'Sam', 5);
insert into person(id, name, company_id) values(9, 'Kevin', 3);


select per.name, comp.name from person as per
left join company comp on per.company_id = comp.id
where company_id != 5 or company_id is null;

select comp.name, count(p.id) as num
from company comp left join person p on comp.id = p.company_id
group by comp.id
order by num desc
limit 1;
