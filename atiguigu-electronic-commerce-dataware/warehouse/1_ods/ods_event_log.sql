use gmall;
drop table if exists ods_start_log;

CREATE EXTERNAL TABLE `ods_event_log` (`line` string)
PARTITIONED BY (`dt` string)
STORED AS INPUTFORMAT 'com.hadoop.mapred.DeprecatedLzoTextInputFormat'
OUTPUTFORMAT 'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat'
LOCATION '/warehouse/gmall/ods/ods_event_log';



load data inpath '/origin_data/gmall/log/topic_event/2022-10-09' into table gamll.ods_event_log partition(dt='2022-10-09');