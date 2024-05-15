package com.example.application.repository;


import com.example.application.data.ParentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentCourseRepository extends JpaRepository<ParentCourse, Integer> {
    List<ParentCourse> findByLecturer_Id(int lecturerId);
}
