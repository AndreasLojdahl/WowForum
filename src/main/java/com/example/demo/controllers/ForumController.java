package com.example.demo.controllers;

import com.example.demo.entities.Forum;
import com.example.demo.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.ServerEndpoint;
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

//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    @Secured("ROLE_ADMIN")
//    public ResponseEntity<Forum> addForum(@RequestBody Forum forum){
//        var newForum = forumService.addForum(forum);
//        var uri = URI.create("api/v1/forums" + newForum.getForum_id());
//        return ResponseEntity.created(uri).body(newForum);
//    }

    @PutMapping("/{forum_id}/moderators/{user_id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void promoteForumModerator(@PathVariable long forum_id, @PathVariable long user_id){
        forumService.promoteModerator(forum_id, user_id);
    }

    @DeleteMapping("/{forum_id}/moderators/{user_id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void demoteForumModerator(@PathVariable long forum_id, @PathVariable long user_id){
        forumService.demoteModerator(forum_id, user_id);
    }

    @DeleteMapping("/{forum_id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteForum(@PathVariable long forum_id){
        forumService.deleteForum(forum_id);
    }


}
