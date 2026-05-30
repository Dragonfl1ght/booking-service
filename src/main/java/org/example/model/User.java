package org.example.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class User {
    private Integer id;
    @Length(min = 3, max = 10)
    private String name;
    private String email;
}
