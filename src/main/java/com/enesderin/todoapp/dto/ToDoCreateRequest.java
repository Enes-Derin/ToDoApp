package com.enesderin.todoapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoCreateRequest {

    @NotBlank(message = "{title.notblank}")
    private String title;
    @NotBlank(message = "{description.notblank}")
    private String description;

}
