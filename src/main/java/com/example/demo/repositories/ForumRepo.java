package com.example.demo.repositories;

import com.example.demo.entities.Forum;
import com.example.demo.entities.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumRepo extends JpaRepository<Forum, Long> {

}
