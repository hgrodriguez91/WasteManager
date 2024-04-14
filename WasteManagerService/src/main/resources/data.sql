CREATE TABLE WASTE_MANAGER_ADDRESS
(
    id                 bigint not null auto_increment,
    address            varchar(255),
    is_enabled         boolean,
    created_date       timestamp(6),
    last_modified_date timestamp(6),
    version            bigint,
    primary key (id)
);

create table WASTE_MANAGER
(
    id                 bigint not null auto_increment,
    name               varchar(255),
    nif                varchar(255),
    version            bigint,
    is_enabled         boolean,
    created_date       timestamp(6),
    last_modified_date timestamp(6),
    manager_address_id bigint not null,
    primary key (id),
    constraint FK_waste_manager_address_id foreign key (manager_address_id) references WASTE_MANAGER_ADDRESS (id)
);

CREATE TABLE WASTE_CENTER_AUTHORIZATION
(
    id                   bigint not null auto_increment,
    AUTHORIZATION_NUMBER varchar(255),
    waste_manager_id     bigint not null,
    primary key (id),
    constraint FK_waste_manager_id foreign key (waste_manager_id) references WASTE_MANAGER (id)
);

INSERT INTO WASTE_MANAGER_ADDRESS(address, is_enabled, created_date, last_modified_date, version)
VALUES ('Street', true, '2024-04-13', '2024-04-13', 1);
INSERT INTO WASTE_MANAGER (name, nif, version, is_enabled, created_date, last_modified_date, manager_address_id)
VALUES ('Jon Doe', 'A description text', 1, true, '2024-04-13', '2024-04-13', 1);
INSERT INTO WASTE_CENTER_AUTHORIZATION (AUTHORIZATION_NUMBER, waste_manager_id)
VALUES ('123456789', 1);
INSERT INTO WASTE_CENTER_AUTHORIZATION (AUTHORIZATION_NUMBER, waste_manager_id)
VALUES ('987654321', 1);
INSERT INTO WASTE_CENTER_AUTHORIZATION (AUTHORIZATION_NUMBER, waste_manager_id)
VALUES ('281934675', 1);