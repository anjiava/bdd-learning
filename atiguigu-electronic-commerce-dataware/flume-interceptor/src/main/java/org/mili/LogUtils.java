package org.mili;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mamenglong
 * @date 2022/09/29
 */
public class LogUtils {
    private static Logger logger = LoggerFactory.getLogger(LogUtils.class);

    public static boolean validateReportLog(String log){
        try {
            // 判断日志的基本格式是否正确， 日志格式为 时间戳 | Json Str
            String[] logArray = log.split("\\|");
            if (logArray.length < 2){
                return false;
            }

            // 检查是否为时间戳
            if (logArray[0].length() != 13 || !NumberUtils.isDigits(logArray[0])) {
                return false;
            }

            // 粗略判断json格式是否有问题
            String json = logArray[1];
            if (!json.startsWith("{") || !json.endsWith("}")) {
                return false;
            }
        } catch (Exception e) {
            logger.error("parse error,message is {},error is {} ",log,e);
        }
        return true;

    }
}
