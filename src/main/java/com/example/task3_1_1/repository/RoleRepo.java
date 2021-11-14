package com.example.task3_1_1.repository;

import com.example.task3_1_1.model.Role;

import java.util.List;

public interface RoleRepo {
    Role getRoleByName(String name);

    Role getRoleById(Long id);

    void addRole(Role role);

    void changeRole(Role role);

    void removeRole(Role role);

    List<Role> getAllRoles();
}
