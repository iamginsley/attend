package com.example.application.repository;

import com.example.application.data.CodeScan;
import com.example.application.data.CodeScanId;
import jakarta.transaction.Transactional;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CodeScanRepository extends JpaRepository<CodeScan, CodeScanId> {
    // Find all CodeScans for a specific user
    List<CodeScan> findByStudents_Id(Integer userId);

    Optional<CodeScan> findCodeScanByStudents_IdAndCourse_Id(Integer studentId, Integer courseId);

    Optional<CodeScan> findCodeScanByStudents_IdAndCourse_IdAndType_Id(Integer studentId, Integer courseId, Integer typeId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO code_scan (userId, courseId, type, time) VALUES (:userId, :courseId, :typeId, :time)", nativeQuery = true)
    void insertCodeScan(@Param("userId") Integer userId, @Param("courseId") Integer courseId, @Param("typeId") Integer typeId, @Param("time") Date time);
}
