package com.example.application.repository;

import com.example.application.data.CodeType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeTypeRepository extends JpaRepository<CodeType, Integer> {
}