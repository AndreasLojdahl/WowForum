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
    private boolean locked;

    public Thread(String title, long forum_id , User user){
        this.title = title;
        this.forum_id = forum_id;
        this.timestamp = new Date().getTime();
        this.threadOwner = user;
        this.locked = false;
    }

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @OrderBy("timestamp ASC")
    @JoinColumn(name = "thread_id")
    private Set<Message> messages;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User threadOwner;

}
