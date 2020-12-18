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

    public Message(String message, long thread_id){
        this.messageContent = message;
        this.thread_id = thread_id;
        this.timestamp = new Date().getTime();

    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User messageOwner;
}
