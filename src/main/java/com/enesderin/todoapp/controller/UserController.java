package com.enesderin.todoapp.controller;

import com.enesderin.todoapp.dto.user.CreateUserRequest;
import com.enesderin.todoapp.dto.user.UpdateUserRequest;
import com.enesderin.todoapp.model.User;
import com.enesderin.todoapp.service.UserService;
import lombok.AllArgsConstructor;
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
        return ResponseEntity.ok("User saved");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,@RequestBody UpdateUserRequest updateUserRequest) {
        this.userService.update(id,updateUserRequest);
        return ResponseEntity.ok("User updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.ok("User deleted");
    }
}
