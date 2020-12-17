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
    private long timestamp = new Date().getTime();
    private String title;
    private long forum_id;

    @OneToMany
    @JoinColumn(name = "thread_id")
    private Set<Message> messages;

    @OneToMany
    @JoinColumn(name = "user_id")
    private User threadOwner;






}
