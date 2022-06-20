package com.example.sqs.sqsMeatbox.controller;

import com.example.sqs.sqsMeatbox.model.Person;
import com.example.sqs.sqsMeatbox.service.SqsMessageService;
import com.example.sqs.sqsMeatbox.service.SqsMultiMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SqsController {

    @Autowired
    private final SqsMessageService messageService;

    @Autowired
    private final SqsMultiMessageService multiMessageService;

    /**
     * SQS 메시지 가져오기
     */
    @GetMapping("/message")
    public void getMessage() {
        messageService.getMessage();
    }

    /**
     * SQS 메시지 넣기
     * @param person
     */
    @PostMapping("/message")
    public void sendMessage(@RequestBody Person person) {
        messageService.sendMessage(person);
    }

    /**
     * SQS 다중 메시지 넣기
     * @param person
     * @throws Exception
     */
    @PostMapping("/multiMessage")
    public void multiSendMessage(@RequestBody Person person) {
        multiMessageService.multiSendMessage(person);
    }
}
