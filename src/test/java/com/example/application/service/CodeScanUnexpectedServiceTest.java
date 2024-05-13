package com.example.application.service;

import com.example.application.data.*;
import com.example.application.repository.CodeScanUnexpectedRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CodeScanUnexpectedServiceTest {

    @MockBean
    private CodeScanUnexpectedRepository codeScanUnexpectedRepository;

    @Autowired
    private CodeScanUnexpectedService codeScanUnexpectedService;

    @Test
    void saveCodeScanUnexpected() {
        User user = new User();
        Course course = new Course();
        CodeType type = new CodeType();

        CodeScanUnexpected codeScan = new CodeScanUnexpected();
        codeScan.setStudents(user);
        codeScan.setCourse(course);
        codeScan.setType(type);
        codeScan.setProofDocument("test.pdf");
        codeScan.setAccepted(true);

        when(codeScanUnexpectedRepository.save(any(CodeScanUnexpected.class))).thenReturn(codeScan);

        CodeScanUnexpected savedScan = codeScanUnexpectedService.saveCodeScanUnexpected(codeScan);

        assertThat(savedScan).isNotNull();
        assertThat(savedScan).isEqualTo(codeScan);
    }
}
