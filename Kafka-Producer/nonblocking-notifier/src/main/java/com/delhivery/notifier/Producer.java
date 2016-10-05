package com.delhivery.notifier;

import com.delhivery.notifier.broker.producer.IStreamWriter;
import com.delhivery.notifier.broker.producer.KafkaConfig;
import com.delhivery.notifier.models.Notification;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by marutsingh on 8/30/16.
 */
@SpringBootApplication
@RestController
public class Producer {

    private static final Logger logger =
            LoggerFactory.getLogger(Producer.class);

    @Autowired
    IStreamWriter publisher;

    @Autowired
    KafkaConfig config;

    @Autowired
    ApplicationContext applicationContext;

    @Value("${activity.partitions}")
    int partition;


    public static void main(String[] args) {
        SpringApplication.run(Producer.class, args);
    }


    @RequestMapping("/publish/")
    void publish() {
        // for testing using main thread to publishMessage time in interval 1000 milliseconds
        long startTime = new Date().getTime();
        logger.info("publishing messages start time : " + startTime);
        int testRecordCount = 1000000;
        int index = 0;
        while (true) {
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            Notification notification = getTestNotification(++index);
            //logger.info("publish index " + index);
            // here we can use producer record based on partition no and dynamic key.
            int partitionNumber = getRandomPartitionNumber();
            ProducerRecord producerRecord = new ProducerRecord(notification.getQueue(), partitionNumber, null, notification);
            publisher.publishMessage(producerRecord);
//            FileUtils.getFileUtilsObject().writeToProducerLogFile(notification);
            if (notification.getIndex() == testRecordCount) {
                logger.info(notification.getIndex() + " records publish finish end time : " + (new Date().getTime() - startTime));
                break;
            }

        }
    }

    private Integer getRandomPartitionNumber() {
        return (int) (Math.random() * partition);
    }

    private Notification getTestNotification(int index) {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        //test notification
        return new Notification(config.getActivityTopicName(), null, "03e6ad43a0144525baf6e8e556a74ef2", "c58fac7d5b543da8b4413b990be5d09", "InventoryUpdate:76", new Notification.MetaData("http://www.voonik.com/variants/BRJHDFCADE%20%23%2saJHK094A2XL.json", null, "PUT", null), index);
    }
}
