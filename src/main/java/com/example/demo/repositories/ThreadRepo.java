package com.example.demo.repositories;

import com.example.demo.entities.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadRepo extends JpaRepository<Thread, Long> {

    @Query(value = "SELECT * FROM threads WHERE forum_id = ?1 ORDER BY timestamp ASC", nativeQuery = true)
    List<Thread> findAllByForumId(long forum_id);

}
