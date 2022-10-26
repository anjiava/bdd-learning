set hive.exec.dynamic.partition.mode=nonstrict;
insert overwrite table dws_uv_detail_day partition(dt)
select
    mid_id,
    collect_set(user_id)[0] user_id,
    collect_set(version_code)[0] version_code,
    collect_set(version_name)[0] version_name,
    collect_set(lang)[0] lang,
    collect_set(source)[0] source,
    collect_set(os)[0] os,
    collect_set(area)[0] area,
    collect_set(model)[0] model,
    collect_set(brand)[0] brand,
    collect_set(sdk_version)[0] sdk_version,
    collect_set(gmail)[0] gmail,
    collect_set(height_width)[0] height_width,
    collect_set(app_time)[0] app_time,
    collect_set(network)[0] network,
    collect_set(lng)[0] lng,
    collect_set(lat)[0] lat,
    '2022-10-17' dt
from dwd_start_log where dt='2022-10-17' group by mid_id;


insert overwrite table dws_uv_detail_wk partition(wk_dt)
select
    mid_id,
    collect_set(user_id)[0] user_id,
    collect_set(version_code)[0] version_code,
    collect_set(version_name)[0] version_name,
    collect_set(lang)[0] lang,
    collect_set(source)[0] source,
    collect_set(os)[0] os,
    collect_set(area)[0] area,
    collect_set(model)[0] model,
    collect_set(brand)[0] brand,
    collect_set(sdk_version)[0] sdk_version,
    collect_set(gmail)[0] gmail,
    collect_set(height_width)[0] height_width,
    collect_set(app_time)[0] app_time,
    collect_set(network)[0] network,
    collect_set(lng)[0] lng,
    collect_set(lat)[0] lat,
    date_add(next_day('2022-10-17','MO'),-7) monday_date,
    date_add(next_day('2022-10-17','MO'),-1) sunday_date,
    concat(date_add(next_day('2022-10-17','MO'),-7),'_',date_add(next_day('2022-10-17','MO'),-1)) wk_dt
from dws_uv_detail_day where dt >=date_add(next_day('2022-10-17','MO'),-7) and dt<=date_add(next_day('2022-10-17','MO'),-1)
group by mid_id;


insert overwrite table dws_uv_detail_mn partition(mn)
select
    mid_id,
    collect_set(user_id)[0] user_id,
    collect_set(version_code)[0] version_code,
    collect_set(version_name)[0] version_name,
    collect_set(lang)[0] lang,
    collect_set(source)[0] source,
    collect_set(os)[0] os,
    collect_set(area)[0] area,
    collect_set(model)[0] model,
    collect_set(brand)[0] brand,
    collect_set(sdk_version)[0] sdk_version,
    collect_set(gmail)[0] gmail,
    collect_set(height_width)[0] height_width,
    collect_set(app_time)[0] app_time,
    collect_set(network)[0] network,
    collect_set(lng)[0] lng,
    collect_set(lat)[0] lat,
    date_format('2022-10-17','yyyy-MM') mn
from dws_uv_detail_day where  date_format(dt,'yyyy-MM') = date_format('2022-10-17','yyyy-MM')
group by mid_id;