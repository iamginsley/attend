package com.example.application.service;

import com.example.application.data.ParentCourse;
import com.example.application.repository.ParentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentCourseService {

    private final ParentCourseRepository courseRepository;

    @Autowired
    public ParentCourseService(ParentCourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<ParentCourse> findAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<ParentCourse> findCourseById(int id) {
        return courseRepository.findById(id);
    }

    public ParentCourse saveCourse(ParentCourse course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    public ParentCourse updateCourse(ParentCourse course) {
        if (course != null && course.getId() > 0) {
            if (courseRepository.existsById(course.getId())) {
                return courseRepository.save(course);
            }
        }
        return null; // or throw an exception as per your error handling strategy
    }
}