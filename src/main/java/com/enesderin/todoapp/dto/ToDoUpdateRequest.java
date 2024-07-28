package com.enesderin.todoapp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoUpdateRequest {

    private String title;

    private boolean completed;
}
