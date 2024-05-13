package com.example.application.repository;

import com.example.application.data.CodeScan;
import com.example.application.data.CodeScanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeScanRepository extends JpaRepository<CodeScan, CodeScanId> {
    // Find all CodeScans for a specific user
    List<CodeScan> findByStudents_Id(Integer userId);
}
