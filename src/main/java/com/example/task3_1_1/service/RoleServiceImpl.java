package com.example.task3_1_1.service;

import com.example.task3_1_1.model.Role;
import com.example.task3_1_1.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }


    @Override
    public Role getRoleByName(String name) {
        return roleRepo.getRoleByName(name);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepo.getRoleById(id);
    }

    @Override
    public void addRole(Role role) {
        roleRepo.addRole(role);
    }

    @Override
    public void changeRole(Role role) {
        roleRepo.changeRole(role);
    }

    @Override
    public void removeRole(Role role) {
        roleRepo.removeRole(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.getAllRoles();
    }
}

