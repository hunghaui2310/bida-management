create table if not exists DATABASECHANGELOG
(
    ID            varchar(255) not null,
    AUTHOR        varchar(255) not null,
    FILENAME      varchar(255) not null,
    DATEEXECUTED  datetime     not null,
    ORDEREXECUTED int          not null,
    EXECTYPE      varchar(10)  not null,
    MD5SUM        varchar(35)  null,
    DESCRIPTION   varchar(255) null,
    COMMENTS      varchar(255) null,
    TAG           varchar(255) null,
    LIQUIBASE     varchar(20)  null,
    CONTEXTS      varchar(255) null,
    LABELS        varchar(255) null,
    DEPLOYMENT_ID varchar(10)  null
);

create table if not exists DATABASECHANGELOGLOCK
(
    ID          int          not null
        primary key,
    LOCKED      bit          not null,
    LOCKGRANTED datetime     null,
    LOCKEDBY    varchar(255) null
);

create table if not exists tbl_authority
(
    name varchar(50) not null
        primary key
);

create table if not exists tbl_billiards
(
    id     bigint auto_increment
        primary key,
    name   varchar(25)      null,
    status int default 1    not null,
    price  double           not null,
    state  bit default b'0' not null
);

create table if not exists tbl_customer
(
    id           bigint auto_increment
        primary key,
    name         varchar(100) null,
    phone_number varchar(50)  not null,
    constraint tbl_customer_phone_number_uindex
        unique (phone_number)
);

create table if not exists tbl_employee
(
    id                   bigint auto_increment
        primary key,
    login                varchar(50)  not null,
    password_hash        varchar(60)  not null,
    first_name           varchar(50)  null,
    last_name            varchar(50)  null,
    phone_number         varchar(50)  null,
    identity_card_number varchar(50)  null,
    address              varchar(255) null,
    salary               double       null,
    position             varchar(100) null,
    start_date           timestamp    null,
    end_date             timestamp    null,
    email                varchar(191) null,
    image_url            varchar(256) null,
    activated            bit          not null,
    lang_key             varchar(10)  null,
    activation_key       varchar(20)  null,
    reset_key            varchar(20)  null,
    created_by           varchar(50)  not null,
    created_date         timestamp    null,
    reset_date           timestamp    null,
    last_modified_by     varchar(50)  null,
    last_modified_date   timestamp    null,
    constraint ux_user_email
        unique (email),
    constraint ux_user_login
        unique (login)
);

create table if not exists tbl_employee_authority
(
    user_id        bigint      not null,
    authority_name varchar(50) not null,
    primary key (user_id, authority_name),
    constraint fk_authority_name
        foreign key (authority_name) references tbl_authority (name),
    constraint fk_user_id
        foreign key (user_id) references tbl_employee (id)
);

create table if not exists tbl_history_billiards
(
    id                bigint auto_increment
        primary key,
    start_time_active timestamp not null,
    end_time_active   timestamp null,
    billiards_id      bigint    not null,
    employee_id       bigint    not null,
    constraint tbl_history_billibards_tbl_billiards_id_fk
        foreign key (billiards_id) references tbl_billiards (id),
    constraint tbl_history_billibards_tbl_employee_id_fk
        foreign key (employee_id) references tbl_employee (id)
);

create table if not exists tbl_product
(
    id       bigint auto_increment
        primary key,
    name     varchar(255) null,
    quantity int          null,
    price    double       not null
);

create table if not exists tbl_bill
(
    id                    bigint auto_increment
        primary key,
    customer_name         varchar(100) null,
    customer_phone_number varchar(50)  null,
    product_name          varchar(255) null,
    quantity              int          null,
    price                 double       null,
    created_date          timestamp    null,
    total_price           double       not null,
    history_billiards_id  bigint       not null,
    employee_id           bigint       not null,
    customer_id           bigint       null,
    product_id            bigint       null,
    constraint tbl_bill_tbl_customer_id_fk
        foreign key (customer_id) references tbl_customer (id),
    constraint tbl_bill_tbl_employee_id_fk
        foreign key (employee_id) references tbl_employee (id),
    constraint tbl_bill_tbl_history_billiards_id_fk
        foreign key (history_billiards_id) references tbl_history_billiards (id),
    constraint tbl_bill_tbl_product_id_fk
        foreign key (product_id) references tbl_product (id)
);

create table if not exists tbl_provider
(
    id           bigint auto_increment
        primary key,
    name         varchar(255) not null,
    address      varchar(255) null,
    phone_number varchar(50)  null,
    constraint tbl_provider_phone_number_uindex
        unique (phone_number)
);

create table if not exists tbl_history_product
(
    id           bigint auto_increment
        primary key,
    product_name varchar(255) null,
    quantity     int          not null,
    price        double       not null,
    receive_date datetime     not null,
    employee_id  bigint       not null,
    provider_id  bigint       null,
    constraint tbl_history_product_tbl_employee_id_fk
        foreign key (employee_id) references tbl_employee (id),
    constraint tbl_history_product_tbl_provider_id_fk
        foreign key (provider_id) references tbl_provider (id)
);

INSERT INTO bida_management.DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID)
VALUES ('00000000000001', 'jhipster', 'config/liquibase/changelog/00000000000000_initial_schema.xml', '2022-01-15 16:23:58', 1, 'EXECUTED', '8:46d889d3454217aee205917d68e060b9', 'createTable tableName=tbl_employee; createTable tableName=tbl_authority; createTable tableName=tbl_employee_authority; addPrimaryKey tableName=tbl_employee_authority; addForeignKeyConstraint baseTableName=tbl_employee_authority, constraintName=fk_...', '', null, '4.5.0', null, null, '2238638076');

INSERT INTO bida_management.tbl_employee (id, login, password_hash, first_name, last_name, phone_number, identity_card_number, address, salary, position, start_date, end_date, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date) VALUES (1, 'admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'Administrator', '0763554653', '0637374674833', 'Xom1', null, '', null, null, 'admin@localhost', '', true, 'vi', null, null, 'system', null, null, 'admin', '2022-01-23 03:38:03');
INSERT INTO bida_management.tbl_employee (id, login, password_hash, first_name, last_name, phone_number, identity_card_number, address, salary, position, start_date, end_date, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date) VALUES (2, 'user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'User', 'User', '0763554653', '0637374673412', 'Xom1', null, '', null, null, 'user@localhost', '', true, 'en', null, null, 'system', null, null, 'system', null);

INSERT INTO bida_management.tbl_authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO bida_management.tbl_authority (name) VALUES ('ROLE_USER');

INSERT INTO bida_management.tbl_employee_authority (user_id, authority_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO bida_management.tbl_employee_authority (user_id, authority_name) VALUES (1, 'ROLE_USER');
INSERT INTO bida_management.tbl_employee_authority (user_id, authority_name) VALUES (2, 'ROLE_USER');
