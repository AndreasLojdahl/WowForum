package com.example.demo.entities;


import com.fasterxml.jackson.annotation.*;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity()
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User implements Serializable {

    private static final long serialVersionUID = 9006661433789679964L;

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
        this.forumsToModerate = Set.of();
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(mappedBy = "moderators", fetch = FetchType.LAZY )
    private Set<Forum> forumsToModerate;

    public void removeForum(Forum forum){
        this.forumsToModerate.remove(forum);
        forum.getModerators().remove(this);
    }

    public List<Long> getForumsToModerate(){
        return forumsToModerate.stream().map(forum -> forum.getForum_id()).collect(Collectors.toList());
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
