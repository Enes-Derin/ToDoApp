package com.enesderin.todoapp.service;

import com.enesderin.todoapp.dto.user.CreateUserRequest;
import com.enesderin.todoapp.dto.user.UpdateUserRequest;
import com.enesderin.todoapp.model.User;
import com.enesderin.todoapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User getUser(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void save(CreateUserRequest createUserRequest) {
        User newUser = new User();
        newUser.setUsername(createUserRequest.getUsername());
        newUser.setEmail(createUserRequest.getEmail());
        newUser.setPassword(createUserRequest.getPassword());
        this.userRepository.save(newUser);
    }


    public void update(Long id,UpdateUserRequest updateUserRequest) {
        User user = this.userRepository.findById(id).orElse(null);
        user.setUsername(updateUserRequest.getUsername());
        user.setEmail(updateUserRequest.getEmail());
        user.setPassword(updateUserRequest.getPassword());
        this.userRepository.save(user);
    }

    public void delete(Long id) {
        this.userRepository.delete(this.userRepository.findById(id).orElse(null));
    }

}
