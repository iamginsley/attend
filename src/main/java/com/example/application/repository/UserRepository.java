package com.example.application.repository;

import com.example.application.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findByRole_Name(String roleName);
    List<User> findByRole_Id(int roleId);

}
