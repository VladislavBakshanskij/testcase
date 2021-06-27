package com.example.journalconsumer.repository;

import com.example.journalconsumer.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {
    @Query(value = "select * from keyauto.journal as t order by t.id offset :offset limit :limit", nativeQuery = true)
    List<Journal> findAll(@Param("limit") int limit, @Param("offset") int offset);
}
