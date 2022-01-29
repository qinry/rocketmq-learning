package com.example.rocketmqconsumeaclexample;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
        topic = "${demo.rocketmq.transTopic}",
        consumerGroup = "group_define_in_Aliware_MQ",
        accessKey = "AK", // if accessKey is empty, it will read by `rocketmq.consumer.access-key` key
        secretKey = "SK"  // if accessKey is empty, it will read by `rocketmq.consumer.secret-key` key
)
public class ACLStringTransactionalConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.printf("------- ACL StringTransactionalConsumer received: %s \n", message);

    }
}
