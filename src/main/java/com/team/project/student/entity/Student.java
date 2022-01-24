package com.team.project.student.entity;

import lombok.Data;

import javax.persistence.*;


@Entity(name = "Student")
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    private int teamName;
    @Column(name="password")
    private String password;
    @Column(name = "email")
    private String email;
}
