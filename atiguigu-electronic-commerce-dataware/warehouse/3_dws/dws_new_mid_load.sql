insert into table dws_new_mid_day
select
       ud.mid_id,
       ud.user_id ,
       ud.version_code ,
       ud.version_name ,
       ud.lang ,
       ud.source,
       ud.os,
       ud.area,
       ud.model,
       ud.brand,
       ud.sdk_version,
       ud.gmail,
       ud.height_width,
       ud.app_time,
       ud.network,
       ud.lng,
       ud.lat,
       '2022-10-17' create_date
from dws_uv_detail_day ud left join dws_new_mid_day nd on nd.mid_id=ud.mid_id
     where ud.dt='2022-10-17' and nd.mid_id is null;
