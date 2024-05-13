package com.example.application.repository;

import com.example.application.data.AbsenceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceCategoryRepository extends JpaRepository<AbsenceCategory, Integer> {
}
