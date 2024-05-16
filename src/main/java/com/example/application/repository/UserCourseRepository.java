package com.example.application.repository;

import com.example.application.data.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Integer> {
    List<UserCourse> findUserCoursesByUser_Id(Integer userId);
}