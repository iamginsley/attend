package com.example.application.service;

import com.example.application.data.AbsenceCategory;
import com.example.application.repository.AbsenceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AbsenceCategoryService {

    @Autowired
    private AbsenceCategoryRepository absenceCategoryRepository;

    @Transactional
    public AbsenceCategory saveAbsenceCategory(AbsenceCategory absenceCategory) {
        return absenceCategoryRepository.save(absenceCategory);
    }

    public Optional<AbsenceCategory> getAbsenceCategoryById(int id) {
        return absenceCategoryRepository.findById(id);
    }

    public List<AbsenceCategory> getAllAbsenceCategories() {
        return absenceCategoryRepository.findAll();
    }

    @Transactional
    public AbsenceCategory updateAbsenceCategory(AbsenceCategory absenceCategory) {
        if (absenceCategory != null && absenceCategoryRepository.existsById(absenceCategory.getId())) {
            return absenceCategoryRepository.save(absenceCategory);
        } else {
            throw new IllegalArgumentException("AbsenceCategory not found");
        }
    }

    @Transactional
    public void deleteAbsenceCategory(int id) {
        absenceCategoryRepository.deleteById(id);
    }
}
