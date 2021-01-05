package com.example.demo.services;

import com.example.demo.Dtos.MessageDto;
import com.example.demo.entities.Message;
import com.example.demo.repositories.MessageRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    UserRepo userRepo;

    public Message addMessage(MessageDto message, long thread_id){

        var username = myUserDetailsService.getCurrentUser();
        var user = userRepo.findByUsername(username);
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }
        //var thread = threadRepo.findById(thread_id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Couldn't find thread"));

        var newMessage = new Message(message.getMessageContent(),message.isWarningPost(), thread_id, user);
        return messageRepo.save(newMessage);
    }

    public void deleteMessage(long thread_id){
        if(!messageRepo.existsById(thread_id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"the message with that id doesn't exist");
        }
        messageRepo.deleteById(thread_id);
    }
}
