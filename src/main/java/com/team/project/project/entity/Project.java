package com.team.project.project.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.team.project.user.entity.User;
import lombok.Data;

import javax.persistence.*;


@Entity(name = "Project")
@Table(name = "project")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_owner", nullable = false)
    @JsonBackReference
    private User owner;
}
