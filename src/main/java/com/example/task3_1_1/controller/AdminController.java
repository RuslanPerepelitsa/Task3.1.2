package com.example.task3_1_1.controller;

import com.example.task3_1_1.model.Role;
import com.example.task3_1_1.model.User;
import com.example.task3_1_1.service.RoleService;
import com.example.task3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("userAuthorized", user);
        model.addAttribute("listUsers", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        List<Role> listRoles = roleService.getAllRoles();
        model.addAttribute("listRoles", listRoles);
        return "admin/allUsers";
    }

    @GetMapping("/create")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        List<Role> listRoles = roleService.getAllRoles();
        model.addAttribute("listRoles", listRoles);
        return "admin/addUser";
    }

    @PostMapping("/create")
    public String addUser(@RequestParam("idRoles") List<Long> idRoles,
                          User user) {
        Set<Role> roleList = new HashSet<>();
        for (Long id : idRoles) {
            roleList.add(roleService.getRoleById(id));
        }
        user.setRoles(roleList);
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model,
                           @PathVariable("id") long id) {
        List<Role> listRole = roleService.getAllRoles();
        model.addAttribute("listRoles", listRole);
        model.addAttribute("user", userService.getUserById(id));
        return "admin/editUser";
    }

    @PutMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("idRoles") List<Long> rolesId) {
        Set<Role> listRoles = new HashSet<>();
        for (Long idRole : rolesId) {
            listRoles.add(roleService.getRoleById(idRole));
        }
        user.setRoles(listRoles);
        System.out.println(user);
        userService.editUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}

