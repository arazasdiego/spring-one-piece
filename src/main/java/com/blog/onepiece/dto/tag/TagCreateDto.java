package com.blog.onepiece.dto.tag;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class TagCreateDto {

    @NotEmpty(message = "name is required")
    private String name;
}
