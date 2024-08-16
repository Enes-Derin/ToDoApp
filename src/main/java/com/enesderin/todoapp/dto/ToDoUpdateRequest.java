package com.enesderin.todoapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoUpdateRequest {

    @NotBlank(message = "{title.notblank}")
    private String title;

    @NotBlank(message = "{description.notblank}")
    private String description;

    private boolean completed;
}
