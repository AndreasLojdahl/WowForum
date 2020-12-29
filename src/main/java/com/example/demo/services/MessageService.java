package com.example.demo.services;

import com.example.demo.Dtos.MessageDto;
import com.example.demo.entities.Message;
import com.example.demo.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;

    public Message addMessage(MessageDto message, long thread_id){
        var newMessage = new Message(message.getMessageContent(), thread_id);
        return messageRepo.save(newMessage);
    }
}
