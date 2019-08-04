create sequence hibernate_sequence start with 2 increment by 1;
create table auction
(
    id                     bigint       not null,
    auction_name           varchar(255) not null,
    beginning_price        decimal(19, 2),
    filename               varchar(255),
    user_id                bigint,
    auction_status         integer,
    beginning_auction_time timestamp,
    private_auction        boolean      not null,
    description            varchar(2000),
    primary key (id)
);
create table user_role
(
    user_id bigint not null,
    roles   varchar(255)
);
create table usr
(
    id                bigint       not null,
    activation_code   varchar(255),
    active            boolean      not null,
    email             varchar(255),
    password          varchar(255) not null,
    username          varchar(255) not null,
    balance           decimal(19, 2),
    user_statistic_id integer,
    primary key (id)
);

create table usr_statistic
(
    id               integer not null,
    created_auctions integer not null,
    took_part        integer not null,
    won_auctions     integer not null,
    primary key (id)
);

create table auction_participants
(
    user_id    bigint not null,
    auction_id bigint not null,
    primary key (auction_id, user_id)
);

alter table auction
    add constraint auction_user_fk foreign key (user_id) references usr;
alter table user_role
    add constraint user_role_user_fk foreign key (user_id) references usr;
alter table usr
    add constraint usr_user_statistic_fk foreign key (user_statistic_id) references usr_statistic;
alter table auction_participants
    add constraint auction_participants_auction_id_fk foreign key (auction_id) references auction;
alter table auction_participants
    add constraint auction_participants_user_id_fk foreign key (user_id) references usr;