package com.example.rocketmqproducedemo;

import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

@ExtRocketMQTemplateConfiguration(nameServer = "${demo.rocketmq.extNameServer}")
public class ExtRocketMQTemplate extends RocketMQTemplate {

}
