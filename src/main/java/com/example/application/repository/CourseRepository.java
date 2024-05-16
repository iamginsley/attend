package com.example.application.repository;

import com.example.application.data.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    public List<Course> getCoursesByParentCourse_Id(Integer id);
}
