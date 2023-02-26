package com.team.project.project.dto;

import com.team.project.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProjectDTO {
    private int id;
    private String name;
    private String description;
    private User owner;
}
