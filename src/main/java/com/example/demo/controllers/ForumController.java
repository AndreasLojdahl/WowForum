package com.example.demo.controllers;

import com.example.demo.entities.Forum;
import com.example.demo.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/forums")
public class ForumController {

    @Autowired
    ForumService forumService;

    @GetMapping
    public ResponseEntity<List<Forum>>  getAllForums(){
        var forums = forumService.getForums();
        return ResponseEntity.ok(forums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Forum> getForum(@PathVariable long id){
        var forum = forumService.getOneForum(id);
        return ResponseEntity.ok(forum);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Forum> addForum(@RequestBody Forum forum){
        var newForum = forumService.addForum(forum);
        var uri = URI.create("api/v1/forums" + newForum.getForum_id());
        return ResponseEntity.created(uri).body(newForum);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateForum(@Validated @RequestBody Forum forum, @PathVariable long id){
        forumService.updateForum(forum, id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteForum(@PathVariable long id){
        forumService.deleteForum(id);
    }


}
