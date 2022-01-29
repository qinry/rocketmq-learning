package org.example.logappenderexample;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer {
    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("logappenderproducer");

        producer.setNamesrvAddr("10.119.6.210:9876");

        producer.start();

        for (int i = 0; i < 10; i++) {
            Message msg = new Message("TopicTest" ,
                    "TagA",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );

            SendResult sendResult = producer.send(msg);
            log.debug(sendResult.toString());
        }

        producer.shutdown();
    }
}
