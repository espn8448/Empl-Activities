package com.usk.activityservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
 
@Service
public class KafKaConsumerService 
{
    private final Logger logger 
        = LoggerFactory.getLogger(KafKaConsumerService.class);
    
//    @Value("${}")
//    private   String GROUPiDVAL;
    
    @Value("${spring.kafka.consumer.group-id}")
	  private String groupIdVal;
     
    @KafkaListener(topics = "emptopic", groupId = "myGroup")
    public void consume(String message) {
        logger.info(String.format("Message recieved -> %s", message));
    }
 
//    @KafkaListener(topics = "${user.topic.name}", 
//            groupId = "${user.topic.group.id}",
//            containerFactory = "userKafkaListenerContainerFactory")
//    public void consume(User user) {
//        logger.info(String.format("User created -> %s", user));
//    }
}
