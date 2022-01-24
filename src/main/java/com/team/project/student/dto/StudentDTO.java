package com.team.project.student.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private int id;
    private String name;
    private String surname;
    private int teamName;
    private String password;
    private String email;
}
