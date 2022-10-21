drop table if exists dwd_base_event_log;
CREATE EXTERNAL TABLE `dwd_base_event_log`(
`mid_id` string,
`user_id` string,
`version_code` string,
`version_name` string,
`lang` string,
`source` string,
`os` string,
`area` string,
`model` string,
`brand` string,
`sdk_version` string,
`gmail` string,
`height_width` string,
`app_time` string,
`network` string,
`lng` string,
`lat` string,
`event_name` string,
`event_json` string,
`server_time` string
)
PARTITIONED BY (`dt` string)
stored as  parquet
location '/warehouse/gmall/dwd/dwd_base_event_log/';
# 定义存储格式为parquet,一种列式存储



set hive.exec.dynamic.partition.mode=nonstrict;
add jar /opt/bdd/hive-1.2.1/lib/self/dwd-hive-function-1.0-SNAPSHOT.jar;
create temporary function resolve_common as 'org.mili.udf.CommonFieldUDF';
create temporary function resolve_event as 'org.mili.udtf.EventFieldUDTF';

insert overwrite table dwd_base_event_log PARTITION(dt) select
        mid_id,
       user_id,
       version_code,
       version_name,
       lang,
       source,
       os,
       area,
       model,
       brand,
       sdk_version,
       gmail,
       height_width,
       app_time,
       network,
       lng,
       lat,
       event_name ,
       event_json ,
       server_time ,
       dt
from
 (
    SELECT
           resolve_common(line,'mid') AS mid_id,
           resolve_common(line,'uid') AS user_id,
           resolve_common(line,'vc') AS version_code,
           resolve_common(line,'vn') AS version_name,
           resolve_common(line,'l') AS lang,
           resolve_common(line,'sr') AS source,
           resolve_common(line,'os') AS os,
           resolve_common(line,'ar') AS area,
           resolve_common(line,'md') AS model,
           resolve_common(line,'ba') AS brand,
           resolve_common(line,'sv') AS sdk_version,
           resolve_common(line,'g') AS gmail,
           resolve_common(line,'hw') AS height_width,
           resolve_common(line,'t') AS app_time,
           resolve_common(line,'nw') AS network,
           resolve_common(line,'ln') AS lng,
           resolve_common(line,'la') AS lat,
           resolve_common(line,'server_time') AS server_time,
           line as ops,
           dt AS dt
    from ods_event_log  where dt='2022-10-15'
 )sdk_log lateral view resolve_event(ops) tmp_k AS event_name,event_json;
