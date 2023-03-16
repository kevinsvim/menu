# 修改rec_log一些字段的默认值
alter table rec_log change column click_num click_num int(11) default 0;

alter table rec_log change column is_collect is_collect int(1) default 0;

alter table rec_log change column is_up is_up int(1) default 0;

alter table rec_log change column comment_num comment_num int(1) default 0;

alter table rec_log modify create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP;

alter table rec_log modify update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;