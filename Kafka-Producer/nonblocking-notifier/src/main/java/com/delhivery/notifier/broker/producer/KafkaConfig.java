package com.delhivery.notifier.broker.producer;

/**
 * Created by marutsingh on 4/19/16.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KafkaConfig {

    @Value("${zookeeper}")
    private String zookeeper;

    @Value("${activity.partitions}")
    private int partitions;

    @Value("${bootstrap.servers}")
    private String boostrapServers;

    @Value("${activity.topicName}")
    private String activityTopicName;

    @Value("${activity.orderTrackingActivity}")
    private String orderTrackingActivityName;

    @Value("${activity.groupId}")
    private String groupId;

    @Value("${activity.pollingInterval}")
    private int pollingInterval;

    @Value("${activity.thread.pool.size}")
    private int activityPoolSize;

    @Value("${kafka.broker.list}")
    private String producerBrokerList;

    public String getProducerBrokerList() {
        return producerBrokerList;
    }

    public void setProducerBrokerList(String producerBrokerList) {
        this.producerBrokerList = producerBrokerList;
    }

    public String getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(String zookeeper) {
        this.zookeeper = zookeeper;
    }

    public String getBoostrapServers() {
        return boostrapServers;
    }

    public void setBoostrapServers(String boostrapServers) {
        this.boostrapServers = boostrapServers;
    }

    public String getActivityTopicName() {
        return activityTopicName;
    }

    public void setActivityTopicName(String activityTopicName) {
        this.activityTopicName = activityTopicName;
    }

    public String getOrderTrackingActivityName() {
        return orderTrackingActivityName;
    }

    public void setOrderTrackingActivityName(String orderTrackingActivityName) {
        this.orderTrackingActivityName = orderTrackingActivityName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getPollingInterval() {
        return pollingInterval;
    }

    public int getActivityPoolSize() {
        return activityPoolSize;
    }

    public void setActivityPoolSize(int activityPoolSize) {
        this.activityPoolSize = activityPoolSize;
    }

    public void setPollingInterval(int pollingInterval) {
        this.pollingInterval = pollingInterval;
    }

    public int getPartitions() {
        return partitions;
    }

    public void setPartitions(int partitions) {
        this.partitions = partitions;
    }
}
