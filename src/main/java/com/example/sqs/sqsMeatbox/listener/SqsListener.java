package com.example.sqs.sqsMeatbox.listener;

import com.example.sqs.sqsMeatbox.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SqsListener {
    //@org.springframework.cloud.aws.messaging.listener.annotation.SqsListener(value = "sqs-meatbox", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void listen(@Payload Person person, Acknowledgment ack) {
        log.info("{}", person);
        ack.acknowledge();
    }
}
