package com.example.rocketmqsimpleexample;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class SyncProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("syncproducer");
        // Specify name server addresses.
        producer.setNamesrvAddr("10.119.6.210:9876");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            try {
                //Create a message instance, specifying topic, tag and message body.
                Message msg = new Message("Topic1" /* Topic */,
                        "TagA" /* Tag */,
                        ("Hello RocketMQ " +
                                i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
                );
                //Call send message to deliver message to one of brokers.
                SendResult sendResult = producer.send(msg);
                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}

