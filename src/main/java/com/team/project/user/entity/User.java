package com.team.project.user.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team.project.project.entity.Project;
import com.team.project.team.entity.Team;
import com.team.project.user.enumeration.RoleName;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "lastname")
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
    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Team> assignedTeams;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Project> projects;
    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "assigned_team_id", nullable = true)
    @JsonBackReference
    private Team teamAffilation;


}
