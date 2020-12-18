package com.example.demo.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity()
@Table(name = "threads")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long thread_id;
    private long timestamp;
    private String title;
    private long forum_id;

    public Thread(String title, long forum_id){
        this.title = title;
        this.forum_id = forum_id;
        this.timestamp = new Date().getTime();
    }

    @OneToMany
    @JoinColumn(name = "thread_id")
    private Set<Message> messages;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User threadOwner;

}
