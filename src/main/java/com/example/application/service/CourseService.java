package com.example.application.service;

import com.example.application.data.Course;
import com.example.application.data.CourseCode;
import com.example.application.data.ParentCourse;
import com.example.application.repository.CourseRepository;
import com.example.application.repository.ParentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final ParentCourseRepository parentCourseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, ParentCourseRepository parentCourseRepository) {
        this.courseRepository = courseRepository;
        this.parentCourseRepository = parentCourseRepository;
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> findAllCourses(String filter) {
        if (filter == null || filter.isEmpty()) {
            return courseRepository.findAll();
        } else {
            return courseRepository.findByNameContainingIgnoreCase(filter);
        }
    }
    @Transactional
    public Course save(Course course) {
            return courseRepository.saveAndFlush(course);
    }

    public Optional<Course> findCourseById(int id) {
        return courseRepository.findById(id);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    public Course findById(int id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.orElse(null);
    }
    public Course updateCourse(Course course) {
        if (course != null && course.getId() > 0) {
            if (courseRepository.existsById(course.getId())) {
                return courseRepository.save(course);
            }
        }
        return null;
    }

    public List<ParentCourse> getParentCoursesByLecturerId(int lecturerId) {
        return this.parentCourseRepository.findByLecturer_Id(lecturerId);
    }

    private Course getFilteredCourseByLecturerId(int lecturerId, Predicate<Course> predicate) {
        List<ParentCourse> parentCourses = getParentCoursesByLecturerId(lecturerId);
        if (parentCourses.isEmpty()) {
            return null;
        }

        List<Course> courses = new ArrayList<>();
        for (ParentCourse parentCourse : parentCourses) {
            courses.addAll(parentCourse.getChildren());
        }

        System.out.println("Courses " + courses.size());

        for (Course course : courses) {
            if (predicate.test(course)) {
                return course;
            }
        }

        return null;
    }

    @Transactional(readOnly = true)
    public Course getCurrentCourseByLecturerId(int lecturerId) {
        return getFilteredCourseByLecturerId(lecturerId, course -> {
            CourseCode checkIn = course.getCourseCodes().stream()
                    .filter(CourseCode::isCheckIn)
                    .findFirst()
                    .orElse(null);
            CourseCode checkOut = course.getCourseCodes().stream()
                    .filter(CourseCode::isCheckOut)
                    .findFirst()
                    .orElse(null);

            if (checkIn == null || checkOut == null) {
                return false;
            }

            return checkIn.isAfter() && checkOut.isBefore();
        });
    }

    @Transactional(readOnly = true)
    public Course getNextCourseByLecturerId(int lecturerId) {
        return getFilteredCourseByLecturerId(lecturerId, course -> {
            CourseCode checkIn = course.getCourseCodes().stream()
                    .filter(CourseCode::isCheckIn)
                    .findFirst()
                    .orElse(null);

            if (checkIn == null) {
                return false;
            }

            return checkIn.isBefore();
        });
    }

    @Transactional(readOnly = true)
    public Course getNextCourseByLecturerIdTest(int lecturerId) {
        List<ParentCourse> parentCourses = getParentCoursesByLecturerId(lecturerId);
        if (parentCourses.isEmpty()) {
            return null;
        }

        List<Course> courses = new ArrayList<>();
        for (ParentCourse parentCourse : parentCourses) {
            courses.addAll(parentCourse.getChildren());
        }

        for (Course course : courses) {
            CourseCode checkIn = course.getCourseCodes().stream()
                    .filter(CourseCode::isCheckIn)
                    .findFirst()
                    .orElse(null);

            if (checkIn == null) {
                continue;
            }

            if (checkIn.isBefore()) {
                return course;
            }
        }

        return null;
    }

    @Transactional(readOnly = true)
    public String getCourseTime(int courseId, String type, String format) {
        if (!(type.equals("check-in") || type.equals("check-out"))) {
            return "";
        }

        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            return "";
        }

        List<CourseCode> courseCodes = course.getCourseCodes();
        if (courseCodes == null || courseCodes.isEmpty()) {
            return "";
        }

        var startEntry = courseCodes.stream()
                .filter(cc -> cc.getType().getName().equals(type))
                .findFirst()
                .orElse(null);
        if (startEntry == null) {
            return "";
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);


        return startEntry.getTime().format(formatter);
    }

    @Transactional
    public String getCourseTimeDate(int courseId, String type) {
        return this.getCourseTime(courseId, type, "dd/MM/yyyy");
    }

    @Transactional
    public String getCourseTimeTime(int courseId, String type) {
        return this.getCourseTime(courseId, type, "HH:mm");
    }

    public List<Course> getCoursesByParentId(Integer id) {
        return this.courseRepository.getCoursesByParentCourse_Id(id);
    }

    @Transactional
    public int getCourseSize(int courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        // Force initialization of the collection
        return course.getParentCourse().getStudents().stream().filter(s -> s.getRole().getId() == 2).toList().size();
    }
}