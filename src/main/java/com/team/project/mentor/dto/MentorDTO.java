package com.team.project.mentor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MentorDTO {
    private int id;
    private String name;
    private String surname;
    private String firm;
    private String email;
    private String password;
}
