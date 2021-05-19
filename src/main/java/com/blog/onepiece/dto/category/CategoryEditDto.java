package com.blog.onepiece.dto.category;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CategoryEditDto {

    @NotNull
    private int id;

    @NotEmpty(message = "description is required")
    @Size(max = 60)
    private String description;
}
