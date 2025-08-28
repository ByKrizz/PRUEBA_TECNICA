/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.accounts.infrastructure.adapter.out.message;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;

/**
 *
 * @author chris
 */
@Component
public class CustomerKafkaConection {

    @Value("${kafka.response.topic}")
    private String responseTopic;
    @Value("${kafka.request.topic}")
    private String requestTopic;

    private ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;

    public CustomerKafkaConection(ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate) {
        this.replyingKafkaTemplate = replyingKafkaTemplate;
    }

    public String requestMessageResponseMessage(String message) {
        try {
            long timeSecond = 10;
            ProducerRecord<String, String> record = new ProducerRecord<>(requestTopic, message);
            record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, responseTopic.getBytes()));
            RequestReplyFuture<String, String, String> future = replyingKafkaTemplate.sendAndReceive(record);
            SendResult<String, String> sendResult = future.getSendFuture().get(timeSecond, TimeUnit.SECONDS);
            ConsumerRecord<String, String> consumerRecord = future.get(timeSecond, TimeUnit.SECONDS);
            return consumerRecord.value();

        } catch (Exception ex) {
            return "{}";
        }
    }
}
