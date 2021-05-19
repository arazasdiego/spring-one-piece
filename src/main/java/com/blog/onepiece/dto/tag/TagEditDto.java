package com.blog.onepiece.dto.tag;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class TagEditDto {

    @NotNull
    private String id;

    @NotEmpty(message = "name is required")
    private String name;
}
