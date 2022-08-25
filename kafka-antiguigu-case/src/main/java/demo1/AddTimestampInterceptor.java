package demo1;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author mamenglong
 * @date 2022/08/24
 */
public class AddTimestampInterceptor implements ProducerInterceptor<String,String> {
    long success=0,error = 0;
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        return new ProducerRecord<String, String>(producerRecord.topic(), producerRecord.partition()
                , producerRecord.timestamp(), producerRecord.key(), System.currentTimeMillis() + ":" + producerRecord.value());
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (null == e) {
            success++;
        } else {
            error++;
        }
    }

    @Override
    public void close() {
        System.out.println("success："+success);
        System.out.println("error："+error);
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
