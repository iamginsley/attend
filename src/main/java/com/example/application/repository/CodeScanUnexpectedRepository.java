package com.example.application.repository;

import com.example.application.data.CodeScanUnexpected;
import com.example.application.data.CodeScanUnexpectedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeScanUnexpectedRepository extends JpaRepository<CodeScanUnexpected, CodeScanUnexpectedId> {
}
