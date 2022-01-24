package com.team.project.mentor.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "Mentor")
@Table(name = "mentor")
@Data
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mentor")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "firm")
    private String firm;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
}
