package com.example.application.service;

import com.example.application.data.CourseCode;
import com.example.application.repository.CourseCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseCodeService {

    private final CourseCodeRepository courseCodeRepository;

    @Autowired
    public CourseCodeService(CourseCodeRepository courseCodeRepository) {
        this.courseCodeRepository = courseCodeRepository;
    }
    public List<CourseCode> getAllCourseCodes() {
        return courseCodeRepository.findAll();
    }

    public List<CourseCode> findAll() {
        return courseCodeRepository.findAll();
    }
    public CourseCode createCourseCode(CourseCode courseCode) {
        return courseCodeRepository.save(courseCode);
    }

    public CourseCode save(CourseCode courseCode) {
        return courseCodeRepository.save(courseCode);
    }

    public void updateCourseCode(int id, CourseCode courseCodeDetails) {
        CourseCode courseCode = courseCodeRepository.findById(id).orElseThrow(() -> new RuntimeException("CourseCode not found for this id :: " + id));
        courseCode.setCourse(courseCodeDetails.getCourse());
        courseCode.setType(courseCodeDetails.getType());
        courseCode.setTime(courseCodeDetails.getTime());
        courseCode.setTimeOffset(courseCodeDetails.getTimeOffset());
        courseCode.setAttendanceCode(courseCodeDetails.getAttendanceCode());
        courseCodeRepository.save(courseCode);
    }

    public void deleteCourseCode(int id) {
        CourseCode courseCode = courseCodeRepository.findById(id).orElseThrow(() -> new RuntimeException("CourseCode not found for this id :: " + id));
        courseCodeRepository.delete(courseCode);
    }

    public Optional<CourseCode> getCourseCodeByCourseId(Integer id) {
        return courseCodeRepository.findCourseCodeByCourse_Id(id);
    }

    public CourseCode getCourseCodeByCourseIdAndType(Integer courseId, Integer codeType) {
        return courseCodeRepository.findCourseCodeByCourse_IdAndType_Id(courseId, codeType);
    }

    public void updateCode(int courseId, int codeType, String code) {
        CourseCode courseCode = this.getCourseCodeByCourseIdAndType(courseId, codeType);
        courseCode.setAttendanceCode(code);
        courseCodeRepository.save(courseCode);
    }
}