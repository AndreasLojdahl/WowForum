package com.example.demo.services;

import com.example.demo.Dtos.ThreadDto;
import com.example.demo.entities.Message;
import com.example.demo.entities.Thread;
import com.example.demo.repositories.ForumRepo;
import com.example.demo.repositories.MessageRepo;
import com.example.demo.repositories.ThreadRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ThreadService {

    @Autowired
    ThreadRepo threadRepo;
    @Autowired
    ForumRepo forumRepo;
    @Autowired
    MessageRepo messageRepo;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    UserRepo userRepo;

    public List<Thread> getThreadsByForumId(long forum_id) {
        if(forumRepo.existsById(forum_id)){
            return threadRepo.findAllByForumId(forum_id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"the thread with that id doesn't exist");
    }

    public Thread getThreadById(long forum_id, long thread_id){
        if(forumRepo.existsById(forum_id) && threadRepo.existsById(thread_id)){
            return threadRepo.findById(thread_id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"the thread with that id doesn't exist");
    }

    public Thread addThread(ThreadDto thread, long forum_id){


        var username = myUserDetailsService.getCurrentUser();
        var user = userRepo.findByUsername(username);
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }

        Thread threadToCreate = new Thread(thread.getTitle(), forum_id, user );
        var newThread = threadRepo.save(threadToCreate);

        var newMessage = new Message(thread.getInitialMessage(),false, newThread.getThread_id(), user);
        messageRepo.save(newMessage);
        return newThread;
    }

    public void updateThread(Thread thread, long thread_id){
        if(!threadRepo.existsById(thread_id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"the thread with that id doesn't exist");
        }
        thread.setThread_id(thread_id);
        threadRepo.save(thread);
    }

    public void deleteThread(long thread_id){
        if(!threadRepo.existsById(thread_id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"the thread with that id doesn't exist");
        }
        threadRepo.deleteById(thread_id);
    }
}
