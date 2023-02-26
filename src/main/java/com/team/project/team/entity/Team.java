package com.team.project.team.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team.project.user.entity.User;
import lombok.Data;
import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    @Column(name = "choices")
    private String choices;
    @Column(name = "create_at")
    private Date createAt;
    @OneToMany(mappedBy = "teamAffilation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> students;
    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "assigned_mentor_id", nullable = true)
    @JsonBackReference
    private User mentor;
}
