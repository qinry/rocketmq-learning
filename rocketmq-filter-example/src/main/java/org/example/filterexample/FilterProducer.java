package org.example.filterexample;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class FilterProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("filterproducer");
        producer.setNamesrvAddr("10.119.6.210:9876");
        producer.start();

        String tag = "TagA";

        for (int i = 0; i < 100; i++) {
            Message msg = new Message("TopicTest",
                    tag,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            // Set some properties.
            msg.putUserProperty("a", String.valueOf(i));

            SendResult sendResult = producer.send(msg);
        }

        producer.shutdown();

    }
}
