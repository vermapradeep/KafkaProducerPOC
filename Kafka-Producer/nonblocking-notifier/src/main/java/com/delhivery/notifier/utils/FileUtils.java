package com.delhivery.notifier.utils;

import com.delhivery.notifier.models.Notification;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * Created by administrator on 2/9/16.
 */
public class FileUtils {

    private static FileUtils fileOperations;

    private FileUtils() {

    }

    public static FileUtils getFileUtilsObject() {
        if (fileOperations == null) {
            fileOperations = new FileUtils();
            fileOperations.initLogFiles();
        }
        return fileOperations;
    }

    File producerLog;

    File consumerLog;

    private void initLogFiles() {
        try {
            producerLog = new File(System.getProperty("user.dir") + "/kafka-log/producer.txt");
            consumerLog = new File(System.getProperty("user.dir") + "/kafka-log/consumer.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToProducerLogFile(Notification notification) {
        try {
            Files.write(producerLog.toPath(), getFileWriteContent(notification).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeToConsumerLogFile(Notification notification) {
        try {
            Files.write(consumerLog.toPath(), getFileWriteContent(notification).getBytes(), StandardOpenOption.APPEND);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFileWriteContent(Notification notification) {
        return notification.getIndex() + "   " + System.currentTimeMillis() + "  " + notification.getEvent().getData() + "\n";
    }

}
