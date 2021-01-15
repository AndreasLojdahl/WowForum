package com.example.demo.services;

import com.example.demo.entities.Forum;
import com.example.demo.repositories.ForumRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ForumService {

    @Autowired
    ForumRepo forumRepo;

    @Autowired
    UserRepo userRepo;

    public List<Forum> getForums() {
        return forumRepo.findAll();
    }

    public Forum getOneForum(long id){
        return forumRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find that forum"));
    }

    public Forum addForum(Forum forum){
        return forumRepo.save(forum);
    }


    public void deleteForum(long id){
        if(!forumRepo.existsById(id)){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"the forum with this id doesn't exist");
        }
    }

    public void promoteModerator(long forum_id, long user_id){

        var forum = forumRepo.findById(forum_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Couldn't find that forum"));
        var user = userRepo.findById(user_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find that user"));

        if(forum.getModerators().contains(user)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User already moderates this forum");
        }
        if(!user.getRoles().contains("MODERATOR")){
            user.setRoles(user.getRoles() + ",MODERATOR");
        }
        forum.getModerators().add(user);
        forumRepo.save(forum);

    }

    public void demoteModerator(long forum_id, long user_id){

        var forum = forumRepo.findById(forum_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Couldn't find that forum"));
        var user = userRepo.findById(user_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find that user"));

        if(!user.getForumsToModerate().contains(forum.getForum_id())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User doesn't moderates that forum");
        }
        user.removeForum(forum);
        if(user.getForumsToModerate().size() == 0){
            user.setRoles(user.getRoles().replace(",MODERATOR",""));
        }
        userRepo.save(user);
    }







}
