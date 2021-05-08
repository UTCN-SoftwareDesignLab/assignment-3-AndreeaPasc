package com.example.assignment3.postgres.users.service;

import com.example.assignment3.postgres.users.model.User;
import com.example.assignment3.postgres.users.repository.UserRepository;
import com.example.assignment3.postgres.users.dto.UserListDTO;
import com.example.assignment3.postgres.users.dto.UserMinimalDTO;
import com.example.assignment3.postgres.users.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }


    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public List<UserListDTO> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }



    public UserMinimalDTO create(UserMinimalDTO user){
        return userMapper.toDto(userRepository.save(userMapper.fromDto(user)));
    }

    public UserMinimalDTO update(Long id, UserMinimalDTO user){
        User actUser = findById(id);
        actUser.setUsername(user.getUsername());
        actUser.setEmail(user.getEmail());

        return userMapper.toDto(userRepository.save(actUser));
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
