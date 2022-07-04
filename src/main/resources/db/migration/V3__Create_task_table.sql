create table if not exists task(
    id bigint primary key auto_increment,
    description varchar(255),
    user_id bigint,
    FOREIGN KEY(user_id) REFERENCES user(id)
);