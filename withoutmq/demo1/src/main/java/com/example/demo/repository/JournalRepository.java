package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Journal;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {
    @Query(value = "select * from Journal as j offset :offset limit :limit", nativeQuery = true)
    List<Journal> findAll(@Param("limit") int limit, @Param("offset") int offset);
}
