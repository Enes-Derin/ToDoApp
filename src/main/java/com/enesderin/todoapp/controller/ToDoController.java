package com.enesderin.todoapp.controller;

import com.enesderin.todoapp.core.Messages;
import com.enesderin.todoapp.dto.ToDoCreateRequest;
import com.enesderin.todoapp.dto.ToDoUpdateRequest;
import com.enesderin.todoapp.model.ToDo;
import com.enesderin.todoapp.service.ToDoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
    public ResponseEntity<String> createToDo(@RequestBody @Valid ToDoCreateRequest toDoCreateRequest) {
        this.toDoService.createTodo(toDoCreateRequest);
        String message = Messages.getMessageForLocale("todo.is.created", LocaleContextHolder.getLocale());
        return ResponseEntity.ok(message);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateToDo(@PathVariable Long id,@RequestBody @Valid ToDoUpdateRequest toDoUpdateRequest) {
        this.toDoService.updateTodo(id, toDoUpdateRequest);
        String message = Messages.getMessageForLocale("todo.is.updated",  LocaleContextHolder.getLocale());
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteToDo(@PathVariable Long id) {
        this.toDoService.delete(id);
        String message = Messages.getMessageForLocale("todo.is.deleted", LocaleContextHolder.getLocale());
        return ResponseEntity.ok(message);
    }
}
