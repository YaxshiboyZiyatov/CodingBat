package com.example.codingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


//    @Email(message = "Email valid emas!")
//    @Column(unique = true)
//
//    @NotEmpty(message = "Empty bo'lmasin")
    private String email;

//    @NotNull(message = "bo'sh bo'lmasin")
    private String password;


    @OneToMany
    private List<Task> taskList;

    @ManyToOne
    private StarBadge starBadge;


}