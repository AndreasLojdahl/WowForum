package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    //@Query(value = "SELECT * FROM users WHERE username LIKE ?1", nativeQuery = true)
    List<User> findByUsernameContains(String username);

//    @Query(value = "UPDATE user_id FROM threads")
//    void setUserIdToNull(long user_id);
}
