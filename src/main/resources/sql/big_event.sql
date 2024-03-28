drop database if exists big_event;

-- 创建数据库
create database big_event;

-- 使用数据库
use big_event;

-- 用户表
drop table if exists user;
create table user
(
    id       bigint primary key auto_increment comment 'ID',
    email    varchar(32) not null comment '邮箱',
    password varchar(32) not null comment '密码'
) comment '用户表';

-- 用户详情表
drop table if exists user_detail;
create table user_detail
(
    id          bigint primary key auto_increment comment 'ID',
    user_id     bigint not null comment '用户ID',
    nickname    varchar(32)  not null comment '昵称',
    mobile      varchar(11)  not null comment '手机号',

    create_time datetime     not null comment '创建时间',
    update_time datetime     not null comment '修改时间'
) comment '用户详情表';


-- 分类表
create table category
(
    id             bigint primary key auto_increment comment 'ID',
    category_name  varchar(32)  not null comment '分类名称',
    category_alias varchar(32)  not null comment '分类别名',
    create_user    bigint not null comment '创建人ID',
    create_time    datetime     not null comment '创建时间',
    update_time    datetime     not null comment '修改时间'
) comment '分类表';

-- 文章表
create table article
(
    id          bigint primary key auto_increment comment 'ID',
    title       varchar(30)    not null comment '文章标题',
    content     varchar(10000) not null comment '文章内容',
    cover_img   varchar(128)   not null comment '文章封面',
    state       varchar(3) default '草稿' comment '文章状态: 只能是[已发布] 或者 [草稿]',
    category_id bigint comment '文章分类ID',
    create_user bigint   not null comment '创建人ID',
    create_time datetime       not null comment '创建时间',
    update_time datetime       not null comment '修改时间'
) comment '文章表';