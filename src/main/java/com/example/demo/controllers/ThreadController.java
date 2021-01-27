package com.example.demo.controllers;

import com.example.demo.Dtos.ThreadDto;
import com.example.demo.Dtos.ThreadUpdateDto;
import com.example.demo.entities.Thread;
import com.example.demo.services.ThreadService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/forums")
public class ThreadController {

    @Autowired
    ThreadService threadService;

    @Operation(summary = "Requires roles : ALL")
    @GetMapping("/{id}/threads")
    public ResponseEntity<List<Thread>> getAllThreadsById(@PathVariable long id){
        var threads = threadService.getThreadsByForumId(id);
        return ResponseEntity.ok(threads);
    }

    @Operation(summary = "Requires roles : ALL")
    @GetMapping("/threads/{thread_id}")
    public ResponseEntity<Thread> getThreadById(@PathVariable long thread_id){
        var thread = threadService.getThreadById(thread_id);
        return ResponseEntity.ok(thread);
    }

    @Operation(summary = "Requires roles : ALL")
    @PostMapping("/{forum_id}/threads")
    public ResponseEntity<Thread> addThread(@PathVariable long forum_id,@Validated @RequestBody ThreadDto thread ){
        var newThread = threadService.addThread(thread, forum_id);
        return ResponseEntity.ok(newThread);
    }

    @Operation(summary = "Requires roles : ADMIN, MODERATOR")
    @PutMapping("/threads/{thread_id}")
    @Secured({"ROLE_ADMIN","ROLE_MODERATOR"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateThread(@Validated @RequestBody ThreadUpdateDto thread, @PathVariable long thread_id){
        threadService.updateThread(thread, thread_id);
    }

    @Operation(summary = "Requires roles : ADMIN")
    @DeleteMapping("/threads/{thread_id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteThread(@PathVariable long thread_id){
        threadService.deleteThread(thread_id);
    }


}
