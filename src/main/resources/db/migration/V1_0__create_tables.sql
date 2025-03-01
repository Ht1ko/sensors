CREATE TABLE if not exists SENSOR
(id          uuid         not null primary key default gen_random_uuid(),
 name        varchar(255) not null,
 model       varchar(255) not null,
 fk_range_id uuid         not null,
 type        varchar(255) not null,
 unit        varchar(255),
 location    varchar(255),
 description varchar(255)
);

CREATE TABLE if not exists RANGE
(id     uuid    not null primary key default gen_random_uuid(),
 "from" integer,
 "to"   integer not null
);

alter table SENSOR
add constraint fk_range_id_range foreign key(fk_range_id) references range(id) on delete cascade;