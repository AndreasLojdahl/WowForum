package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity()
@Table(name = "forums")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long forum_id;
    private String name;

    @OneToMany
    @JoinColumn(name = "forum_id")
    private Set<Thread> threads;




}
