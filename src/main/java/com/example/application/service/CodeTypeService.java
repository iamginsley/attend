package com.example.application.service;

import com.example.application.data.CodeType;
import com.example.application.repository.CodeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeTypeService {

    private final CodeTypeRepository codeTypeRepository;

    @Autowired
    public CodeTypeService(CodeTypeRepository codeTypeRepository) {
        this.codeTypeRepository = codeTypeRepository;
    }

    public List<CodeType> getAllCodeTypes() {
        return codeTypeRepository.findAll();
    }

    public CodeType getCodeTypeById(Integer id) {
        return codeTypeRepository.findById(id).orElse(null);
    }

    public CodeType saveCodeType(CodeType codeType) {
        return codeTypeRepository.save(codeType);
    }

    public void deleteCodeType(Integer id) {
        codeTypeRepository.deleteById(id);
    }
}