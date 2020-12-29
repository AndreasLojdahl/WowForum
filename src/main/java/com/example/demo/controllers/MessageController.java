package com.example.demo.controllers;

import com.example.demo.Dtos.ThreadDto;
import com.example.demo.entities.Thread;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/messages")
public class MessageController {

    @PostMapping("/{forum_id}/threads")
    public ResponseEntity<Thread> addThread(@PathVariable long forum_id, @RequestBody ThreadDto thread ){
        var newThread = threadService.addThread(thread, forum_id);
        return ResponseEntity.ok(newThread);
    }
}
