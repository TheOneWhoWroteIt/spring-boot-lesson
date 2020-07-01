insert into company(id, name)
values (1,'Google'),
       (2, 'Oracle');

select setval('company_id_seq', (select max(id) from company));