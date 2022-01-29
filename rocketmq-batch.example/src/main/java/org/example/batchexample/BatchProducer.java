package org.example.batchexample;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

public class BatchProducer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new
                DefaultMQProducer("batchproducer");
        producer.setNamesrvAddr("10.119.6.210:9876");
        producer.start();
        // 一批消息不超过限定
        String topic = "BatchTest";
        List<Message> messages1 = new ArrayList<>();
        List<Message> messages2 = new ArrayList<>();
        messages1.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
        messages1.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
        messages1.add(new Message(topic, "TagA", "OrderID003", "Hello world 2".getBytes()));

        try {
            producer.send(messages1);
        } catch (Exception e) {
            e.printStackTrace();
            //handle the error
        }

        messages2.add(new Message(topic, "TagA", "OrderID004", "Hello world 4".getBytes()));
        messages2.add(new Message(topic, "TagA", "OrderID005", "Hello world 5".getBytes()));
        messages2.add(new Message(topic, "TagA", "OrderID006", "Hello world 6".getBytes()));

        //当许多而小的消息的列表时，可以分割
        ListSplitter splitter = new ListSplitter(messages2);
        while (splitter.hasNext()) {
            try {
                List<Message>  listItem = splitter.next();
                producer.send(listItem);
            } catch (Exception e) {
                e.printStackTrace();
                //handle the error
            }
        }
    }
}
