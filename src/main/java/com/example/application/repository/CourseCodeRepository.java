package com.example.application.repository;

import com.example.application.data.CourseCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseCodeRepository extends JpaRepository<CourseCode, Integer> {

   Optional<CourseCode> findCourseCodeByCourse_Id(Integer courseId);

   Optional<CourseCode> findCourseCodeByCourse_IdAndAndAttendanceCode(Integer courseId, String attendanceCode);

   @Query("SELECT c FROM CourseCode c WHERE c.course.id = :courseId AND c.type.id = :codeType")
   CourseCode findCourseCodeByCourse_IdAndType_Id(Integer courseId, Integer codeType);
}