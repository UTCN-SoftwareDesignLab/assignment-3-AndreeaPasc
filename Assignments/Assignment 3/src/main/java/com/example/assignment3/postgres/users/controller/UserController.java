package com.example.assignment3.mySql.users.controller;

import com.example.assignment3.mySql.patients.model.dto.PatientDto;
import com.example.assignment3.mySql.users.dto.UserMinimalDTO;
import com.example.assignment3.mySql.users.service.UserService;
import com.example.assignment3.mySql.users.dto.UserListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.assignment3.UrlMapping.ENTITY;
import static com.example.assignment3.UrlMapping.USERS;

@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserListDTO> allUsers() {
        return userService.allUsersForList();
    }

    @PostMapping
    public UserMinimalDTO create(@RequestBody UserMinimalDTO user){
        return userService.create(user);
    }

    @PutMapping(ENTITY)
    public UserMinimalDTO update(@PathVariable Long id, @RequestBody UserMinimalDTO user) {
        return userService.update(id, user);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

}
