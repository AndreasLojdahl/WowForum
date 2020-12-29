package com.example.demo.controllers;

import com.example.demo.Dtos.MessageDto;
import com.example.demo.Dtos.ThreadDto;
import com.example.demo.entities.Message;
import com.example.demo.entities.Thread;
import com.example.demo.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("/{thread_id}")
    public ResponseEntity<Message> addThread(@PathVariable long thread_id, @RequestBody MessageDto message ){
        var newMessage = messageService.addMessage(message, thread_id);
        return ResponseEntity.ok(newMessage);
    }
}
