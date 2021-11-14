package com.example.task3_1_1.fillDB;

import com.example.task3_1_1.model.Role;
import com.example.task3_1_1.model.User;
import com.example.task3_1_1.service.RoleService;
import com.example.task3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestUsers {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public TestUsers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void addTestUsers() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        Set<Role> adminRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);
        userRoles.add(roleUser);
        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);
        User user1 = new User("Ruslan", "Perepelitsa", "Ruslan0709", "admin", "ruslan@gmail.com");
        User user2 = new User("Petr", "Ivanov", "Petr777", "user", "petr777@mail.ru");
        user1.setRoles(adminRoles);
        user2.setRoles(userRoles);
        userService.addUser(user1);
        userService.addUser(user2);
    }
}
