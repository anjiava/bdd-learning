package demo1;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

/**
 * @author mamenglong
 * @date 2022/08/24
 */
public class CustomConsumer {
    public static void main(String[] args) {
        Properties conf = new Properties();
        conf.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "node002:9092");
        // 消费者所属组
        conf.put(ConsumerConfig.GROUP_ID_CONFIG, "hello");
        // 关闭自动提交offset
        conf.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        conf.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        conf.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(conf);
        consumer.subscribe(Collections.singleton("helloworld"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                System.out.println("=========当前已有分区===========");
                collection.forEach(item-> System.out.println(item.toString()));

            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                System.out.println("=========重新分配后的分区===========");
                collection.forEach(item-> System.out.println(item.toString()));
            }
        });

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000L);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.toString());
            }
            consumer.commitSync();
        }

    }
}
