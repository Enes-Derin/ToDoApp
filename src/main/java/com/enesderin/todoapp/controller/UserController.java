package com.enesderin.todoapp.controller;

import com.enesderin.todoapp.core.Messages;
import com.enesderin.todoapp.dto.user.CreateUserRequest;
import com.enesderin.todoapp.dto.user.UpdateUserRequest;
import com.enesderin.todoapp.model.User;
import com.enesderin.todoapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody CreateUserRequest createUserRequest) {
        this.userService.save(createUserRequest);
        String message=Messages.getMessageForLocale("todo.user.created", LocaleContextHolder.getLocale());
        return ResponseEntity.ok(message);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,@RequestBody UpdateUserRequest updateUserRequest) {
        this.userService.update(id,updateUserRequest);
        String message = Messages.getMessageForLocale("todo.user.updated", LocaleContextHolder.getLocale());
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.userService.delete(id);
        String message = Messages.getMessageForLocale("todo.user.deleted", LocaleContextHolder.getLocale());
        return ResponseEntity.ok(message);
    }
}
