package com.delhivery.notifier.broker.producer;

import com.delhivery.notifier.utils.JackSonSerializer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * Created by administrator on 31/8/16.
 */
@Service("publishMessage")
public class KafkaWriter implements IStreamWriter {

    @Autowired
    KafkaConfig kafkaConfig;

    KafkaProducer<String, String> producer;

    @PostConstruct
    void init() {
        producer = new KafkaProducer<String, String>(getProducerProperties());
    }

    private Properties getProducerProperties() {

        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaConfig.getBoostrapServers());
        //hardcoding below for testing can use properties files also
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
//        props.put("num.partitions",kafkaConfig.getPartitions());
//        props.put("topic.partition.count.map","dsns-notifications:"+kafkaConfig.getPartitions());
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", JackSonSerializer.class.getName());
        return props;
    }

    @Override
    public void publishMessage(ProducerRecord producerRecord) {
        producer.send(producerRecord);
    }

    @Override
    public void publishMessage(ProducerRecord<Object, Object> producerRecord, Callback callback) {
        // implement to verify message pubished successfully.
    }

    void closeProducer() {
        producer.close();
    }
}
