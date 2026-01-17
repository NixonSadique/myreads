package com.nixon.myreads.repository;

import com.nixon.myreads.entity.BookProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookProgressRepository extends JpaRepository<BookProgress, Long> {
    Boolean existsBookById(Long id);

    List<BookProgress> findByBookId(Long bookId);

    List<BookProgress> findByUserId(Long bookId);

}
