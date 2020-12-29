package com.example.demo.services;

import com.example.demo.entities.Forum;
import com.example.demo.repositories.ForumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@Service
public class ForumService {

    @Autowired
    ForumRepo forumRepo;

    public List<Forum> getForums() {
        return forumRepo.findAll();
    }

    public Forum getOneForum(String name){
        System.out.println("hämtar 1");
        return forumRepo.findByName(name);
    }

    public Forum addForum(Forum forum){
        return forumRepo.save(forum);
    }

    public void updateForum(Forum forum, long id){
        if(!forumRepo.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The forum with that id doesn't exist");
        }
        forum.setForum_id(id);
        forumRepo.save(forum);
    }

    public void deleteForum(long id){
        if(!forumRepo.existsById(id)){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"the forum with this id doesn't exist");
        }
    }







}
