package com.delhivery.notifier.utils;

import com.delhivery.notifier.models.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created by administrator on 1/9/16.
 */
public class JackSonSerializer implements Serializer<Notification>, Deserializer<Notification> {

    private static final Logger logger = LoggerFactory.getLogger(JackSonSerializer.class);

    private ObjectMapper mapper;

    public JackSonSerializer() {
    }

    public JackSonSerializer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Notification deserialize(String topic, byte[] data) {
        try {
            return mapper.readValue(data, Notification.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        mapper = mapper == null ? new ObjectMapper() : mapper;
    }

    @Override
    public byte[] serialize(String topic, Notification data) {
        try {
            return mapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {

    }
}
