package com.team.project.mentor.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "Mentor")
@Table(name = "mentor")
@Data
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String firm;
    private String email;
    private String password;
}
