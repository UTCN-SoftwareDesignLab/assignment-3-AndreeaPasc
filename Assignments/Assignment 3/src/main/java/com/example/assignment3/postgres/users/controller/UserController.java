package com.example.assignment3.postgres.users.controller;

import com.example.assignment3.postgres.users.dto.UserMinimalDTO;
import com.example.assignment3.postgres.users.service.UserService;
import com.example.assignment3.postgres.users.dto.UserListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
