package com.example.application.service;

import com.example.application.data.CodeScan;
import com.example.application.data.CodeType;
import com.example.application.data.Course;
import com.example.application.data.User;
import com.example.application.repository.CodeScanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class CodeScanServiceTest {
    @MockBean
    private CodeScanRepository codeScanRepository;

    @Autowired
    private CodeScanService codeScanService;

    @Test
    void saveCodeScan() {
        User user = new User();
        Course course = new Course();
        CodeType type = new CodeType();

        CodeScan codeScan = new CodeScan();

        codeScan.setTime(new Date());
        codeScan.setStudents(user);
        codeScan.setType(type);
        codeScan.setCourse(course);

        when(codeScanRepository.save(any(CodeScan.class))).thenReturn(codeScan);

        CodeScan savedScan = codeScanService.saveCodeScan(codeScan);

        assertThat(savedScan).isNotNull();
        assertThat(savedScan).isEqualTo(codeScan);
    }

}