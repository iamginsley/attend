package com.example.application.repository;

import com.example.application.data.CourseCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseCodeRepository extends JpaRepository<CourseCode, Long> {

   Optional<CourseCode> findCourseCodeByCourse_Id(Integer courseId);
}