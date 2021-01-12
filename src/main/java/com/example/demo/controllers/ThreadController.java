package com.example.demo.controllers;

import com.example.demo.Dtos.ThreadDto;
import com.example.demo.entities.Thread;
import com.example.demo.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/forums")
public class ThreadController {

    @Autowired
    ThreadService threadService;

    @GetMapping("/{id}/threads")
    public ResponseEntity<List<Thread>> getAllThreadsById(@PathVariable long id){
        var threads = threadService.getThreadsByForumId(id);
        return ResponseEntity.ok(threads);
    }

    @GetMapping("/{forum_id}/threads/{thread_id}")
    public ResponseEntity<Thread> getThreadById(@PathVariable long forum_id, @PathVariable long thread_id){
        var thread = threadService.getThreadById(forum_id,thread_id);
        return ResponseEntity.ok(thread);
    }

    @PostMapping("/{forum_id}/threads")
    public ResponseEntity<Thread> addThread(@PathVariable long forum_id, @RequestBody ThreadDto thread ){
        var newThread = threadService.addThread(thread, forum_id);
        return ResponseEntity.ok(newThread);
    }

    @PutMapping("/threads/{thread_id}")
    @Secured({"ROLE_ADMIN","ROLE_MODERATOR"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateThread(@RequestBody Thread thread, @PathVariable long thread_id){
        threadService.updateThread(thread, thread_id);
    }

    @DeleteMapping("/threads/{thread_id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteThread(@PathVariable long thread_id){
        threadService.deleteThread(thread_id);
    }


}
