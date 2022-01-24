package com.team.project.team.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "Team")
@Table(name = "team")
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_team")
    private int id;
    @Column(name = "name")
    private String name;
    private int projectName;
}
