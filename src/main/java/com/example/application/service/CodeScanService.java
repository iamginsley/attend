package com.example.application.service;

import com.example.application.data.CodeScan;
import com.example.application.data.CodeScanId;
import com.example.application.data.CodeType;
import com.example.application.data.CourseCode;
import com.example.application.repository.CodeScanRepository;
import com.example.application.repository.CourseCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CodeScanService {

    @Autowired
    private CodeScanRepository codeScanRepository;

    @Autowired
    private CourseCodeRepository courseCodeRepository;


    @Transactional
    public CodeScan saveCodeScan(CodeScan codeScan) {
        return codeScanRepository.save(codeScan);
    }
    
    public Optional<CodeScan> getCodeScanById(CodeScanId id) {
        return codeScanRepository.findById(id);
    }


    public List<CodeScan> getAllCodeScans() {
        return codeScanRepository.findAll();
    }

    @Transactional
    public CodeScan updateCodeScan(CodeScan codeScan) {
        if (codeScan != null && codeScanRepository.existsById(new CodeScanId(
                codeScan.getStudents().getId(),
                codeScan.getCourse().getId(),
                codeScan.getType().getId()))) {
            return codeScanRepository.save(codeScan);
        } else {
            throw new IllegalArgumentException("CodeScan not found");
        }
    }

    @Transactional
    public void deleteCodeScan(CodeScanId id) {
        codeScanRepository.deleteById(id);
    }

    public List<CodeScan> getCodeScansByUserId(Integer userId) {
        return codeScanRepository.findByStudents_Id(userId);
    }

    public Optional<CodeScan> getCodeScanByStudentAndUser(Integer studentId, Integer courseId) {
        return codeScanRepository.findCodeScanByStudents_IdAndCourse_Id(studentId, courseId);
    }

    public boolean isUserCheckedIn(Integer studentId, Integer courseId) {
        var t = codeScanRepository.findCodeScansByStudents_IdAndCourse_Id(studentId, courseId);

        return !t.isEmpty();
    }

    public void insertCodeScan(Integer userId, Integer courseId, Integer typeId, Date time) {
        // Call the repository method to insert the record
        codeScanRepository.insertCodeScan(userId, courseId, typeId, time);
    }


    public CodeType checkIfValidCode(Integer courseId, String code) {
        var t = courseCodeRepository.findCourseCodeByCourse_IdAndAndAttendanceCode(courseId, code);

        if(t.isEmpty()) {
            return null;
        }

        return t.get().getType();


    }

    public Boolean userAlreadyCheckedIn(Integer courseId, Integer userId, Integer typeId) {
        var c =  codeScanRepository.findCodeScanByStudents_IdAndCourse_IdAndType_Id(userId, courseId, typeId);

        return c.isPresent();
    }
}
