package com.enesderin.todoapp.service;

import com.enesderin.todoapp.dto.ToDoCreateRequest;
import com.enesderin.todoapp.dto.ToDoUpdateRequest;
import com.enesderin.todoapp.model.ToDo;
import com.enesderin.todoapp.repository.ToDoRepository;
import lombok.AllArgsConstructor;
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
        if (todo.isPresent()){
            return  todo.get();
        }else
            return null;
    }

    public ToDo createTodo(ToDoCreateRequest toDoCreateRequest) {
        ToDo newToDo = new ToDo();
        newToDo.setTitle(toDoCreateRequest.getTitle());
        this.toDoRepository.save(newToDo);
        return newToDo;
    }

    public ToDo updateTodo(Long id,ToDoUpdateRequest toDoUpdateRequest){
        Optional<ToDo> todo= this.toDoRepository.findById(id);
        if (todo.isPresent()){
            ToDo updatedToDo = todo.get();
            updatedToDo.setTitle(toDoUpdateRequest.getTitle());
            updatedToDo.setCompleted(toDoUpdateRequest.isCompleted());
            this.toDoRepository.save(updatedToDo);
            return updatedToDo;
        }
        return null;
    }

    public void delete(Long id) {
        this.toDoRepository.deleteById(id);
    }
}
