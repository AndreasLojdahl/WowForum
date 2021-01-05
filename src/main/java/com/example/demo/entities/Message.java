package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity()
@Table(name = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long message_id;
    private long timestamp;
    private String messageContent;
    private long thread_id;
    private boolean warningPost;

    public Message(String message, boolean warningPost, long thread_id, User user){
        this.messageContent = message;
        this.warningPost = warningPost;
        this.thread_id = thread_id;
        //this.thread = thread;
        this.timestamp = new Date().getTime();
        this.messageOwner = user;

    }

//    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
//    private Thread thread;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User messageOwner;
}
