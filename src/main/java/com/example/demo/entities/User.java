package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

//@EqualsAndHashCode(exclude = {"messages", "threads"})
@Entity()
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;
    private String username;
    private String email;
    private String password;
    private String roles;

    public User(String username, String email, String password, String roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }



//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "messageOwner")
//    @JsonIgnore
//    private Set<Message> messages;
//
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "threadOwner")
//    @JsonIgnore
//    private Set<Thread> threads;
}
