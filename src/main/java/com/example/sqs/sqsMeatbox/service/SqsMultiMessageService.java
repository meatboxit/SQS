package com.example.sqs.sqsMeatbox.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.example.sqs.sqsMeatbox.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SqsMultiMessageService {

    private static final String QUEUE_NAME = "sqs-meatbox";

    @Autowired
    private AmazonSQS amazonSqs;

    public void multiSendMessage(Person person) {
        String queueUrl = amazonSqs.getQueueUrl(QUEUE_NAME).getQueueUrl();
        log.info("SQS에 Person List를 전달합니다 : " + person.getPersonList());

        List<SendMessageBatchRequestEntry> entryList = new ArrayList<>();
        for (Person p : person.getPersonList()) {
            SendMessageBatchRequestEntry e = new SendMessageBatchRequestEntry();
            e.setId(p.getId());
            e.setMessageBody(p.createMessage());
            entryList.add(e);
        }

        SendMessageBatchRequest send_batch_request = new SendMessageBatchRequest()
                .withQueueUrl(queueUrl)
                .withEntries(entryList);

        amazonSqs.sendMessageBatch(send_batch_request);
    }


}
