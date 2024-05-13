package com.example.application.service;

import com.example.application.data.ParentCourse;
import com.example.application.data.Role;
import com.example.application.repository.ParentCourseRepository;
import com.example.application.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    Role role;

    private final String roleName = "Admin";

    @BeforeEach
    void setUp() {
        this.role = new Role();

        this.role.setName(this.roleName);
        this.role.setId(1);
    }

    @Test
    void createRole() {
        when(roleRepository.save(any(Role.class))).thenReturn(this.role);

        Role savedRole = roleService.createRole(role);

        assertThat(savedRole).isNotNull();
        assertThat(savedRole.getName()).isEqualTo(this.roleName);
    }

    @Test
    void findRoleById() {

        when(roleRepository.findById(role.getId())).thenReturn(Optional.of(role));

        Role fetchedRole = roleService.findRoleById(role.getId());

        assertThat(fetchedRole).isNotNull();
        assertThat(fetchedRole.getId()).isEqualTo(role.getId());
    }
}