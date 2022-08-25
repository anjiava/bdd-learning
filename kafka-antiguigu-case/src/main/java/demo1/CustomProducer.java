package demo1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author mamenglong
 * @date 2022/08/24
 */
public class CustomProducer {
    public static void main(String[] args) {
        Properties config = new Properties();
        // kafka集群地址
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.3.82:9092");
        // ack响应模式：所有follower同步完才发送ack
        config.put(ProducerConfig.ACKS_CONFIG,"-1");
        config.put(ProducerConfig.RETRIES_CONFIG,"1");
        // producer.sender满足该大小时，发送数据到broker
        config.put(ProducerConfig.BATCH_SIZE_CONFIG,"16384");
        // producer.sender 每隔一定时间就发送一次数据
        config.put(ProducerConfig.LINGER_MS_CONFIG,"1");
        // 缓冲区大小，sender从该缓冲区主动拉取数据
        config.put(ProducerConfig.BUFFER_MEMORY_CONFIG,"33554432");
        // 设置 key和value的序列化器
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        config.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, AddTimestampInterceptor.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(config);

        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>("helloworld", Integer.toString(i), Integer.toString(i)));
        }
        producer.close();

    }
}
