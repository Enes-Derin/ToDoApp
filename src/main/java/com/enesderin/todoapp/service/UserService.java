package com.enesderin.todoapp.service;

import com.enesderin.todoapp.core.Messages;
import com.enesderin.todoapp.dto.user.CreateUserRequest;
import com.enesderin.todoapp.dto.user.UpdateUserRequest;
import com.enesderin.todoapp.exception.NotFoundException;
import com.enesderin.todoapp.model.User;
import com.enesderin.todoapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User getUser(Long id){
        String message= Messages.getMessageForLocale("todo.user.not.found", LocaleContextHolder.getLocale());
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException(message));
    }

    public void save(CreateUserRequest createUserRequest) {
        User newUser = new User();
        newUser.setUsername(createUserRequest.getUsername());
        newUser.setEmail(createUserRequest.getEmail());
        newUser.setPassword(createUserRequest.getPassword());
        this.userRepository.save(newUser);
    }


    public void update(Long id,UpdateUserRequest updateUserRequest) {
        String message= Messages.getMessageForLocale("todo.user.not.found", LocaleContextHolder.getLocale());
        User user = this.userRepository.findById(id).orElseThrow(()-> new NotFoundException(message));
        user.setUsername(updateUserRequest.getUsername());
        user.setEmail(updateUserRequest.getEmail());
        user.setPassword(updateUserRequest.getPassword());
        this.userRepository.save(user);
    }

    public void delete(Long id) {
        String message = Messages.getMessageForLocale("todo.user.not.found", LocaleContextHolder.getLocale());
        this.userRepository.delete(this.userRepository.findById(id).orElseThrow(()-> new NotFoundException(message)));
    }

}
