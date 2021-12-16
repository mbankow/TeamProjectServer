package com.team.project.project.entity;


import javax.persistence.*;


@Entity(name = "Project")
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
}
