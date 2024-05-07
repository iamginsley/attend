package com.example.application.service;

import com.example.application.data.*;
import com.example.application.repository.ParentCourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ParentCourseServiceTest {

    @Mock
    private ParentCourseRepository courseRepository;

    @InjectMocks
    private ParentCourseService courseService;

    private User lecturer;

    private Faculty faculty;

    @BeforeEach
    void setUp() {
        Role role = new Role();

        this.lecturer = new User();

        this.faculty = new Faculty();

    }

    @Test
    public void createCourse() {
        ParentCourse course1 = new ParentCourse();
        course1.setId(1);
        course1.setFaculty(faculty);
        course1.setLecturer(lecturer);
        course1.setName("Software Engineering 2");

        when(courseRepository.save(any(ParentCourse.class))).thenReturn(course1);

        ParentCourse savedCourse = courseService.saveCourse(course1);

        assertThat(savedCourse).isNotNull();
        assertThat(savedCourse.getName()).isEqualTo("Software Engineering 2");
    }

    @Test
    public void findCourseById() {
        ParentCourse course1 = new ParentCourse();
        course1.setId(1);
        course1.setFaculty(faculty);
        course1.setLecturer(lecturer);
        course1.setName("Software Engineering 2");

        when(courseRepository.findById(course1.getId())).thenReturn(Optional.of(course1));


        Optional<ParentCourse> fetchedCourse = courseService.findCourseById(course1.getId());


        assertThat(fetchedCourse).isNotNull();
        assertThat(fetchedCourse.get().getId()).isEqualTo(course1.getId());
    }
}