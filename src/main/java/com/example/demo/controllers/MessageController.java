package com.example.demo.controllers;

import com.example.demo.Dtos.MessageDto;
import com.example.demo.Dtos.ThreadDto;
import com.example.demo.entities.Message;
import com.example.demo.entities.Thread;
import com.example.demo.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Operation(summary = "Requires roles : ALL")
    @PostMapping("/{thread_id}")
    public ResponseEntity<Message> addMessage(@PathVariable long thread_id,@Validated @RequestBody MessageDto message ){
        var newMessage = messageService.addMessage(message, thread_id);
        return ResponseEntity.ok(newMessage);
    }

    @Operation(summary = "Requires roles : ADMIN, MODERATOR")
    @DeleteMapping("/{message_id}")
    @Secured({"ROLE_ADMIN","ROLE_MODERATOR"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessage(@PathVariable long message_id){
        messageService.deleteMessage(message_id);
    }
}
