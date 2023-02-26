package com.team.project.user.dto;


import com.team.project.project.entity.Project;
import com.team.project.team.entity.Team;
import com.team.project.user.enumeration.RoleName;
import lombok.Data;

import java.util.List;


@Data
public class UserDTO {

    private int id;
    private String name;
    private String lastName;
    private String firm;
    private String email;
    private String password;
    private RoleName role;
    private List<Team> assignedTeams;
    private List<Project> projects;
    private Team teamAffilation;

}
