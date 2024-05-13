package com.example.application.service;

import com.example.application.data.CodeScanUnexpected;
import com.example.application.data.CodeScanUnexpectedId;
import com.example.application.repository.CodeScanUnexpectedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CodeScanUnexpectedService {

    @Autowired
    private CodeScanUnexpectedRepository codeScanUnexpectedRepository;

    @Transactional
    public CodeScanUnexpected saveCodeScanUnexpected(CodeScanUnexpected codeScanUnexpected) {
        return codeScanUnexpectedRepository.save(codeScanUnexpected);
    }

    public Optional<CodeScanUnexpected> getCodeScanUnexpectedById(CodeScanUnexpectedId id) {
        return codeScanUnexpectedRepository.findById(id);
    }

    public List<CodeScanUnexpected> getAllCodeScanUnexpecteds() {
        return codeScanUnexpectedRepository.findAll();
    }

    @Transactional
    public CodeScanUnexpected updateCodeScanUnexpected(CodeScanUnexpected codeScanUnexpected) {
        CodeScanUnexpectedId id = new CodeScanUnexpectedId(
                codeScanUnexpected.getStudents().getId(),
                codeScanUnexpected.getCourse().getId(),
                codeScanUnexpected.getType().getId()
        );

        if (codeScanUnexpectedRepository.existsById(id)) {
            return codeScanUnexpectedRepository.save(codeScanUnexpected);
        } else {
            throw new IllegalArgumentException("CodeScanUnexpected not found with ID: " + id);
        }
    }

    @Transactional
    public void deleteCodeScanUnexpected(CodeScanUnexpectedId id) {
        codeScanUnexpectedRepository.deleteById(id);
    }
}
