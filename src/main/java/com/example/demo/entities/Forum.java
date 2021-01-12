package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
//@Data
@Entity()
@Table(name = "forums")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Forum implements Serializable {

    private static final long serialVersionUID = -7256628674075121500L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long forum_id;
    private String name;

    @OneToMany
    @OrderBy("timestamp ASC")
    @JoinColumn(name = "forum_id")
    private Set<Thread> threads;

    @ManyToMany
    @JoinTable(name = "moderators", joinColumns = @JoinColumn(name = "forum_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> moderators;



}
