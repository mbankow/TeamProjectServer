package com.team.project.user.entity;


import com.team.project.user.enumeration.RoleName;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "User")
@Table(name ="person")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "firm")
    private String firm;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleName role;

}
