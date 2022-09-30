package org.mili.interceptors;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Flume过滤器，用于实现将 埋点的日志类型，放到header中
 *
 * @author mamenglong
 * @date 2022/09/29
 */
public class LogTypeInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // 将一行数据按照 utf8编码转换为字符串
        String log = new String(event.getBody(), StandardCharsets.UTF_8);
        Map<String, String> headers = event.getHeaders();
        if (log.contains("start")) {
            headers.put("logType", "start");
        } else {
            headers.put("logType", "event");
        }
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        list.forEach(this::intercept);
        return list;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder {
        @Override
        public Interceptor build() {
            return new LogTypeInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }

}
