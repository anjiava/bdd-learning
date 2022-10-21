use gmall;

drop table if exists dwd_display_log;
CREATE EXTERNAL TABLE `dwd_display_log`(
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
    `action` string,
    `newsid` string,
    `place` string,
    `extend1` string,
    `category` string,
    `server_time` string
) PARTITIONED BY (dt string) location '/warehouse/gmall/dwd/dwd_display_log';

drop table if exists dwd_newsdetail_log;
CREATE EXTERNAL TABLE `dwd_newsdetail_log`(
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
    `entry` string,
    `action` string,
    `newsid` string,
    `showtype` string,
    `news_staytime` string,
    `loading_time` string,
    `type1` string,
    `category` string,
    `server_time` string
) PARTITIONED BY (dt string) location '/warehouse/gmall/dwd/dwd_newsdetail_log/';


drop table if exists dwd_loading_log;
CREATE EXTERNAL TABLE `dwd_loading_log`(
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
    `action` string,
    `loading_time` string,
    `loading_way` string,
    `extend1` string,
    `extend2` string,
    `type` string,
    `type1` string,
    `server_time` string
) PARTITIONED BY (dt string) location '/warehouse/gmall/dwd/dwd_loading_log/';

drop table if exists dwd_ad_log;
CREATE EXTERNAL TABLE `dwd_ad_log`(
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
    `entry` string,
    `action` string,
    `content` string,
    `detail` string,
    `ad_source` string,
    `behavior` string,
    `newstype` string,
    `show_style` string,
    `server_time` string
) PARTITIONED BY (dt string) location '/warehouse/gmall/dwd/dwd_ad_log/';

drop table if exists dwd_notification_log;
CREATE EXTERNAL TABLE `dwd_notification_log`(
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
    `action` string,
    `noti_type` string,
    `ap_time` string,
    `content` string,
    `server_time` string
) PARTITIONED BY (dt string) location '/warehouse/gmall/dwd/dwd_notification_log/';


drop table if exists dwd_active_foreground_log;
CREATE EXTERNAL TABLE `dwd_active_foreground_log`(
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
    `active_source` string,
    `server_time` string
)
PARTITIONED BY (dt string) location '/warehouse/gmall/dwd/dwd_foreground_log/';


drop table if exists dwd_active_background_log;
CREATE EXTERNAL TABLE `dwd_active_background_log`(
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
    `active_source` string,
    `server_time` string
) PARTITIONED BY (dt string) location '/warehouse/gmall/dwd/dwd_background_log/';


drop table if exists dwd_comment_log;
CREATE EXTERNAL TABLE `dwd_comment_log`(
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
    `comment_id` int,
    `userid` int,
    `p_comment_id` int,
    `content` string,
    `addtime` string,
    `other_id` int,
    `praise_count` int,
    `reply_count` int,
    `server_time` string
) PARTITIONED BY (dt string) location '/warehouse/gmall/dwd/dwd_comment_log/';


drop table if exists dwd_favorites_log;
CREATE EXTERNAL TABLE `dwd_favorites_log`(
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
    `id` int,
    `course_id` int,
    `userid` int,
    `add_time` string,
    `server_time` string
) PARTITIONED BY (dt string) location '/warehouse/gmall/dwd/dwd_favorites_log/';


drop table if exists dwd_praise_log;
CREATE EXTERNAL TABLE `dwd_praise_log`(
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
    `id` string,
    `userid` string,
    `target_id` string,
    `type` string,
    `add_time` string,
    `server_time` string
) PARTITIONED BY (dt string) location '/warehouse/gmall/dwd/dwd_praise_log/';


drop table if exists dwd_start_log;
CREATE EXTERNAL TABLE `dwd_start_log`(
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
    `entry` string,
    `open_ad_type` string,
    `action` string,
    `loading_time` string,
    `detail` string,
    `extend1` string,
    `server_time` string
) PARTITIONED BY (dt string) location '/warehouse/gmall/dwd/dwd_start_log/';

drop table if exists dwd_error_log;
CREATE EXTERNAL TABLE `dwd_error_log`(
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
    `errorBrief` string,
    `errorDetail` string,
    `server_time` string
) PARTITIONED BY (dt string) location '/warehouse/gmall/dwd/dwd_error_log/';
