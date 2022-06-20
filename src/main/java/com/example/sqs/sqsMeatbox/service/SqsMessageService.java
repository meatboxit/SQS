package com.example.sqs.sqsMeatbox.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.example.sqs.sqsMeatbox.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SqsMessageService {

    private static final String QUEUE_NAME = "sqs-meatbox";
    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public SqsMessageService(AmazonSQS amazonSqs) {
        this.queueMessagingTemplate = new QueueMessagingTemplate((AmazonSQSAsync) amazonSqs);
    }

    /**
     * SQS 메시지 가져오기
     */
    public void getMessage() {
        Person person = queueMessagingTemplate.receiveAndConvert(QUEUE_NAME, Person.class);
        log.info("SQS로부터 받은 메시지 : " + person);
    }

    /**
     * SQS 메시지 넣기
     * @param person
     */
    public void sendMessage(Person person) {
        log.info("SQS에 Person을 전달합니다 : " + person);
        queueMessagingTemplate.convertAndSend(QUEUE_NAME, person);
    }
}
