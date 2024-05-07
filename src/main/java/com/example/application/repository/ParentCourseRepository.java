package com.example.application.repository;


import com.example.application.data.ParentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentCourseRepository extends JpaRepository<ParentCourse, Integer> {
    // Add optional custom queries.
}
