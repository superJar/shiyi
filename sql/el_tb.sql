-- 拾壹商品表
CREATE TABLE SYS_PRODUCT
(
    ID               INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    NAME             VARCHAR(32) COMMENT '商品名称',
    TYPE             INT COMMENT '商品类型 0-酒水小吃类，1-桌游类，2-包房类',
    PRICE            DECIMAL(6, 2) COMMENT '单价价格',
    ADDITIONAL_PRICE DECIMAL(6, 2) COMMENT '附加价格',
    PIC              VARCHAR(32) COMMENT '商品图片',
    CREATED_BY       VARCHAR(32) COMMENT '创建人',
    CREATED_TIME     DATETIME COMMENT '创建时间',
    UPDATED_BY       VARCHAR(32) COMMENT '更新人',
    UPDATED_TIME     DATETIME COMMENT '更新时间',
    SPARE1           VARCHAR(1) COMMENT '备用字段1',
    SPARE2           VARCHAR(1) COMMENT '备用字段2',
    SPARE3           VARCHAR(1) COMMENT '备用字段3'
) COMMENT = '拾壹商品表 ';

-- 新增商品数据
insert into sys_product(name, type, price, additional_price, pic, created_by, created_time, updated_by, updated_time)
values('狼人杀','1',45,20,'','superJar',sysdate(),'superJar',sysdate());

insert into sys_product(name, type, price, additional_price, pic, created_by, created_time, updated_by, updated_time)
values('剧本杀','1',165,30,'','superJar',sysdate(),'superJar',sysdate());

insert into sys_product(name, type, price, additional_price, pic, created_by, created_time, updated_by, updated_time)
values('中包房','2',599,799,'','superJar',sysdate(),'superJar',sysdate());

insert into sys_product(name, type, price, additional_price, pic, created_by, created_time, updated_by, updated_time)
values('大包房','2',699,999,'','superJar',sysdate(),'superJar',sysdate());

insert into sys_product(name, type, price, additional_price, pic, created_by, created_time, updated_by, updated_time)
values('可乐','0',3.5,0,'','superJar',sysdate(),'superJar',sysdate());

insert into sys_product(name, type, price, additional_price, pic, created_by, created_time, updated_by, updated_time)
values('RIO鸡尾酒','0',15,0,'','superJar',sysdate(),'superJar',sysdate());

insert into sys_product(name, type, price, additional_price, pic, created_by, created_time, updated_by, updated_time)
values('百威','0',10,0,'','superJar',sysdate(),'superJar',sysdate());

insert into sys_product(name, type, price, additional_price, pic, created_by, created_time, updated_by, updated_time)
values('嘉士伯','0',12,0,'','superJar',sysdate(),'superJar',sysdate());

insert into sys_product(name, type, price, additional_price, pic, created_by, created_time, updated_by, updated_time)
values('雪碧','0',3.5,0,'','superJar',sysdate(),'superJar',sysdate());

insert into sys_product(name, type, price, additional_price, pic, created_by, created_time, updated_by, updated_time)
values('可乐','0',3.5,0,'','superJar',sysdate(),'superJar',sysdate());



-- 会员表
CREATE TABLE SYS_MEMBER
(
    ID                 INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    CARD_NUM           VARCHAR(16) COMMENT '卡号',
    NAME               VARCHAR(16) COMMENT '姓名',
    NICKNAME           VARCHAR(16) COMMENT '昵称',
    GENDER             VARCHAR(1) COMMENT '性别 0-女性，1-男性',
    PHONE              VARCHAR(15) COMMENT '手机号',
    BALANCE            DECIMAL(7, 2) COMMENT '余额',
    ADDITIONAL_BALANCE DECIMAL(7, 2) COMMENT '附加余额 该余额不算作正式余额',
    SUM_OF_TOP_UP      DECIMAL(8, 2) COMMENT '充值总计',
    SUM_OF_CONSUMPTION DECIMAL(8, 2) COMMENT '消费总计',
    REMARK             VARCHAR(64) COMMENT '备注',
    CREATED_BY         VARCHAR(32) COMMENT '创建人',
    CREATED_TIME       DATETIME COMMENT '创建时间',
    UPDATED_BY         VARCHAR(32) COMMENT '更新人',
    UPDATED_TIME       DATETIME COMMENT '更新时间',
    SPARE1             VARCHAR(1) COMMENT '备用字段1',
    SPARE2             VARCHAR(1) COMMENT '备用字段2',
    SPARE3             VARCHAR(1) COMMENT '备用字段3',
    PRIMARY KEY (ID)
) COMMENT = '会员表 ';

-- 新增会员数据
INSERT INTO sys_member(card_num, name, nickname, gender,
                       phone, balance, additional_balance,
                       sum_of_top_up,
                       sum_of_consumption, remark, created_by,
                       created_time, updated_by, updated_time)
VALUES ( 'A10001', '加内特', 'KG', '1', '13666666118', 1000, 0, 100, 0
       , '', 'superJar', sysdate(), 'superJar', sysdate());

INSERT INTO sys_member(card_num, name, nickname, gender,
                       phone, balance, additional_balance,
                       sum_of_top_up,
                       sum_of_consumption, remark, created_by,
                       created_time, updated_by, updated_time)
VALUES ( 'A10002', '詹姆斯', 'lbj', '1', '13666666888', 1000, 0, 100, 0
       , '', 'superJar', sysdate(), 'superJar', sysdate());

INSERT INTO sys_member(card_num, name, nickname, gender, phone, balance, additional_balance, sum_of_top_up,
                       sum_of_consumption, remark, created_by, created_time, updated_by, updated_time)
VALUES ( 'A10003', '欧文', 'irving', '1', '13666666666', 100, 0, 100, 0
       , '', 'superJar', sysdate(), 'superJar', sysdate());

INSERT INTO sys_member(card_num, name, nickname, gender, phone, balance, additional_balance,
                       sum_of_top_up, sum_of_consumption, remark, created_by, created_time, updated_by, updated_time)
VALUES ( 'A10004', '小矮子', 'KG', '1', '13666666238', 1000, 0, 100, 0
       , '', 'superJar', sysdate(), 'superJar', sysdate());
