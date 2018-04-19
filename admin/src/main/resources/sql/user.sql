DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(50) NOT NULL,
  `name` varchar(255) NOT NULL,
  `age` int DEFAULT 18,
  PRIMARY KEY (`id`)
);


CREATE TABLE users (
  id varchar(50) NOT NULL,
  password varchar(255) not null,
  name varchar(255) NOT NULL,
  age number DEFAULT 18,
  PRIMARY KEY (id)
);

create table users
(
  ID         VARCHAR(32) not null,
  NAME       VARCHAR(20),
  PASSWORD   VARCHAR(20),
  SEX        VARCHAR(4),
  INSERTDATE DATE,
  MESSAGE    CLOB,
  PIC        BLOB,
  BIRTHDAY   DATE
)

  -- Add comments to the columns
comment on column users.ID
is 'id';
comment on column users.NAME
is '用户名';
comment on column users.PASSWORD
is '密码';
comment on column users.SEX
is '性别';
comment on column users.INSERTDATE
is '入库时间';
comment on column users.MESSAGE
is '长数据';
comment on column users.PIC
is '头像';