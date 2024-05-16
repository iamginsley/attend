package com.example.application.service;

import com.example.application.data.Course;
import com.example.application.data.ParentCourse;
import com.example.application.data.UserCourse;
import com.example.application.repository.CourseRepository;
import com.example.application.repository.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCoursesByParentId(Integer id) {

        return this.courseRepository.getCoursesByParentCourse_Id(id);

    }
}