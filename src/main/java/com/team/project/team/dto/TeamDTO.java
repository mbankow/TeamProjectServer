package com.team.project.team.dto;

import com.team.project.team.entity.Team;
import com.team.project.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TeamDTO {
    private int id;
    private String name;
    private String choices;
    private Date createAt;
    private List<User> students;
    private Team mentor;
}
