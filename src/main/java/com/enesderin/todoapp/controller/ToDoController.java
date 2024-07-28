package com.enesderin.todoapp.controller;

import com.enesderin.todoapp.dto.ToDoCreateRequest;
import com.enesderin.todoapp.dto.ToDoUpdateRequest;
import com.enesderin.todoapp.model.ToDo;
import com.enesderin.todoapp.service.ToDoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class ToDoController {

    private ToDoService toDoService;

    @GetMapping
    public List<ToDo> getAllToDos() {
        return this.toDoService.getAll();
    }

    @GetMapping("/getOne/{id}")
    public ToDo getToDoById(@PathVariable Long id) {
        return this.toDoService.getById(id);
    }

    @PostMapping
    public ResponseEntity<String> createToDo(@RequestBody ToDoCreateRequest toDoCreateRequest) {
        this.toDoService.createTodo(toDoCreateRequest);
        return ResponseEntity.ok("Created To Do");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateToDo(@PathVariable Long id,@RequestBody ToDoUpdateRequest toDoUpdateRequest) {
        this.toDoService.updateTodo(id, toDoUpdateRequest);
        return ResponseEntity.ok("Updated To Do");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteToDo(@PathVariable Long id) {
        this.toDoService.delete(id);
        return ResponseEntity.ok("Deleted To Do");
    }
}
