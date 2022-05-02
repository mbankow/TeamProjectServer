package com.team.project.user.dto;


import com.team.project.user.enumeration.RoleName;
import lombok.Data;


@Data
public class UserDTO {

    private int id;
    private String name;
    private String lastName;
    private String firm;
    private String email;
    private String password;
    private RoleName role;

}
