package org.mili.interceptors;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Flume过滤器，用于对日志格式做一个粗略的过滤
 * @author mamenglong
 * @date 2022/09/29
 */
public class LogETLInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // 将一行数据按照 utf8编码转换为字符串
        String log = new String(event.getBody(), StandardCharsets.UTF_8);
        if (LogUtils.validateReportLog(log)) {
            return event;
        }
        return null;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        return list.stream()
                .map(this::intercept).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder {
        @Override
        public Interceptor build() {
            return new LogETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }

}
