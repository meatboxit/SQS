package com.example.sqs.sqsMeatbox.model;


import lombok.*;
import org.json.simple.JSONObject;

import java.util.List;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private String id;
    private int age;
    private List<Person> personList;

    public String createMessage() {
        JSONObject orderMessage = new JSONObject();
        orderMessage.put("name", this.name);
        orderMessage.put("id", this.id);
        orderMessage.put("age", this.age);
        return orderMessage.toJSONString();
    }
}
