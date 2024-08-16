package com.enesderin.todoapp.service;

import com.enesderin.todoapp.dto.ToDoCreateRequest;
import com.enesderin.todoapp.dto.ToDoUpdateRequest;
import com.enesderin.todoapp.exception.NotFoundException;
import com.enesderin.todoapp.model.ToDo;
import com.enesderin.todoapp.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ToDoService {

    private ToDoRepository toDoRepository;

    public List<ToDo> getAll() {
        return toDoRepository.findAll();
    }

    public ToDo getById(Long id) {
        Optional<ToDo> todo= this.toDoRepository.findById(id);
        return  todo.get();
    }

    public ToDo createTodo(ToDoCreateRequest toDoCreateRequest) {
        ToDo newToDo = new ToDo();
        newToDo.setTitle(toDoCreateRequest.getTitle());
        newToDo.setDescription(toDoCreateRequest.getDescription());
        this.toDoRepository.save(newToDo);
        return newToDo;
    }

    public ToDo updateTodo(Long id,ToDoUpdateRequest toDoUpdateRequest){
        ToDo todo= this.toDoRepository.findById(id).orElseThrow();
        todo.setTitle(toDoUpdateRequest.getTitle());
        todo.setDescription(toDoUpdateRequest.getDescription());
        todo.setCompleted(toDoUpdateRequest.isCompleted());
        this.toDoRepository.save(todo);
        return todo;
    }

    public void delete(Long id) {
        Optional<ToDo> todo= this.toDoRepository.findById(id);
        if (todo.isPresent()) {
            this.toDoRepository.delete(todo.get());
        }else{
            throw new NotFoundException();
        }
    }
}
