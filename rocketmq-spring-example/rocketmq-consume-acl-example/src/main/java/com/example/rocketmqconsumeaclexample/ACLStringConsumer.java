package com.example.rocketmqconsumeaclexample;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
        topic = "normal_topic_define_in_Aliware_MQ",
        consumerGroup = "group_define_in_Aliware_MQ"
        //accessKey = "AK" // It will read by `rocketmq.consumer.access-key` key
        //secretKey = "SK" // It will read by `rocketmq.consumer.access-key` key
)
public class ACLStringConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.printf("------- ACL StringConsumer received: %s \n", message);
    }
}