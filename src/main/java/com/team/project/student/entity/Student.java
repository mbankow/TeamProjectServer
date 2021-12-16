package com.team.project.student.entity;

import javax.persistence.*;


@Entity(name = "Student")
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private int teamName;
    private String password;
    private String email;
}
