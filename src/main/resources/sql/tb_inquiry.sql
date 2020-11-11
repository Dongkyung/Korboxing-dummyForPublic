create table tb_inquiry (
    seq int unsigned primary key AUTO_INCREMENT comment '고유 seq',
    nickName varchar(50) not null comment '닉네임',
    userName varchar(50) comment '이름',
    phoneNumber varchar(13)  comment '연락처',
    email varchar(255) not null comment 'e메일',
    destination_email varchar(255) not null comment '발송처 e메일',
    category varchar(50)  not null comment '문의분야',
    content text not null comment '내용',
    reg_date datetime not null comment '등록일'
) default charset = utf8;