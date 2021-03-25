create table blog
(
    id int primary key auto_increment not null comment '主键，自增',
    title varchar(32) not null comment '标题',
    content text not null comment '内容',
    create_time timestamp not null DEFAULT CURRENT_TIMESTAMP  comment '创建时间',
    modify_time timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间'
) ENGINE=InnoDB comment '博客';