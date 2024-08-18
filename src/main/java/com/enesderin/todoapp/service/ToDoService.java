package com.enesderin.todoapp.service;

import com.enesderin.todoapp.core.Messages;
import com.enesderin.todoapp.dto.ToDoCreateRequest;
import com.enesderin.todoapp.dto.ToDoUpdateRequest;
import com.enesderin.todoapp.exception.NotFoundException;
import com.enesderin.todoapp.model.ToDo;
import com.enesderin.todoapp.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ToDoService {

    private ToDoRepository toDoRepository;

    public List<ToDo> getAll() {

        if (toDoRepository.findAll().isEmpty()) {
            String message = Messages.getMessageForLocale("todo.not.found", LocaleContextHolder.getLocale());
            throw new NotFoundException(message);
        }

        return toDoRepository.findAll();

    }

    public ToDo getById(Long id) {
        String message = Messages.getMessageForLocale("todo.not.found", LocaleContextHolder.getLocale());
        ToDo todo= this.toDoRepository.findById(id).orElseThrow(() -> new NotFoundException(message));
        return  todo;
    }

    public void createTodo(ToDoCreateRequest toDoCreateRequest) {
        ToDo newToDo = new ToDo();
        newToDo.setTitle(toDoCreateRequest.getTitle());
        newToDo.setDescription(toDoCreateRequest.getDescription());
        this.toDoRepository.save(newToDo);
    }

    public void updateTodo(Long id,ToDoUpdateRequest toDoUpdateRequest){
        ToDo todo= this.toDoRepository.findById(id).orElseThrow();
        todo.setTitle(toDoUpdateRequest.getTitle());
        todo.setDescription(toDoUpdateRequest.getDescription());
        todo.setCompleted(toDoUpdateRequest.isCompleted());
        this.toDoRepository.save(todo);
    }

    public void delete(Long id) {
        String message = Messages.getMessageForLocale("todo.not.found", LocaleContextHolder.getLocale());
        ToDo todo= this.toDoRepository.findById(id).orElseThrow(() -> new NotFoundException(message));
        this.toDoRepository.delete(todo);
    }
}
