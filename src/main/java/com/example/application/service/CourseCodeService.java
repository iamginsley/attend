package com.example.application.service;

import com.example.application.data.CourseCode;
import com.example.application.repository.CourseCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CourseCodeService {
    @Autowired
    private CourseCodeRepository courseCodeRepository;

    public List<CourseCode> getAllCourseCodes() {
        return courseCodeRepository.findAll();
    }

    public Optional<CourseCode> getCourseCode(Long id) {
        return courseCodeRepository.findById(id);
    }

    public CourseCode createCourseCode(CourseCode courseCode) {
        return courseCodeRepository.save(courseCode);
    }

    public CourseCode updateCourseCode(Long id, CourseCode courseCodeDetails) {
        CourseCode courseCode = courseCodeRepository.findById(id).orElseThrow(() -> new RuntimeException("CourseCode not found for this id :: " + id));
        courseCode.setCourse(courseCodeDetails.getCourse());
        courseCode.setType(courseCodeDetails.getType());
        courseCode.setTime(courseCodeDetails.getTime());
        courseCode.setTimeOffset(courseCodeDetails.getTimeOffset());
        courseCode.setAttendanceCode(courseCodeDetails.getAttendanceCode());
        return courseCodeRepository.save(courseCode);
    }

    public void deleteCourseCode(Long id) {
        CourseCode courseCode = courseCodeRepository.findById(id).orElseThrow(() -> new RuntimeException("CourseCode not found for this id :: " + id));
        courseCodeRepository.delete(courseCode);
    }

    public Optional<CourseCode> getCourseCodeByCourseId(Integer id) {
        return courseCodeRepository.findCourseCodeByCourse_Id(id);
    }


}