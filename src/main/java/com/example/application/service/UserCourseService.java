package com.example.application.service;

import com.example.application.data.Course;
import com.example.application.data.ParentCourse;
import com.example.application.data.Role;
import com.example.application.data.UserCourse;
import com.example.application.repository.CourseRepository;
import com.example.application.repository.RoleRepository;
import com.example.application.repository.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserCourseService {

    private final UserCourseRepository userCourseRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public UserCourseService(UserCourseRepository userCourseRepository, CourseRepository courseRepository) {
        this.userCourseRepository = userCourseRepository;
        this.courseRepository = courseRepository;
    }

    public List<Course> getCoursesByUser(Integer id) {

        List<Course> courses = new ArrayList<>();

        var t = this.userCourseRepository.findUserCoursesByUser_Id(id);

        for (UserCourse c : t) {
            var cs = this.courseRepository.getCoursesByParentCourse_Id(c.getCourse().getId());

            courses.addAll(cs);

        }

        return courses;

    }
}