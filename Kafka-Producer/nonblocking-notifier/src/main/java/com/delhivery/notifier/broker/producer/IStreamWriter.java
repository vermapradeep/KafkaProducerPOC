package com.delhivery.notifier.broker.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Created by administrator on 31/8/16.
 */
public interface IStreamWriter {
    void publishMessage(ProducerRecord<Object, Object> producerRecord);

    void publishMessage(ProducerRecord<Object, Object> producerRecord, Callback callback);

}
