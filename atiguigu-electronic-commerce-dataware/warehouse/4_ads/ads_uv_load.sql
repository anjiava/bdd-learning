
insert into table ads_uv_count
select
    '2022-10-17' dt,
    day.ct day_count,
    wk.ct wk_count,
    mn.ct mn_count,
    if(date_add((next_day('2022-10-17','MO')),-1)='2022-10-17','Y','N') is_weekend,
    if(last_day('2022-10-17')='2022-10-17','Y','N') is_monthend
from
    (select '2022-10-17' dt,count(*) ct from dws_uv_detail_day where dt='2022-10-17') day
join
    (select '2022-10-17' dt,count(*) ct from dws_uv_detail_wk where wk_dt = CONCAT(date_add(next_day('2022-10-17','MO'),-7),'_',date_add(next_day('2022-10-17','MO'),-1))) wk
on day.dt=wk.dt
join
    (select '2022-10-17' dt,count(*) ct from dws_uv_detail_mn where mn =date_format('2022-10-17','yyyy-MM')) mn
on mn.dt=day.dt;