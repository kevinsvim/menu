# 修改rec_log一些字段的默认值
alter table rec_log change column click_num click_num int(11) default 0;

alter table rec_log change column is_collect is_collect int(1) default 0;

alter table rec_log change column is_up is_up int(1) default 0;

alter table rec_log change column comment_num comment_num int(1) default 0;

alter table rec_log modify create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP;

alter table rec_log modify update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

insert into flavor (id, name) value (3, '超辣');
insert into flavor (id, name) value (4, '麻辣');
insert into flavor (id, name) value (5, '酸辣');
insert into flavor (id, name) value (6, '酸甜');
insert into flavor (id, name) value (7, '酸咸');



insert into kitchenware (id, name) value (1, '炒锅');
insert into kitchenware (id, name) value (2, '煮锅');
insert into kitchenware (id, name) value (3, '平底锅');
insert into kitchenware (id, name) value (4, '瓦锅');


insert into cook_technology (id, name) value (1, '炒');
insert into cook_technology (id, name) value (2, '煮');
insert into cook_technology (id, name) value (3, '蒸');