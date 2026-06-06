package org.example.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {
    private Integer id;

    @NotBlank
    @Size(min = 3, max = 10)
    private String name;

    @Email
    @NotBlank
    private String email;
}
