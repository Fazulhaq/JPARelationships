package com.example.SpringJpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "mission")
public class Mission {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private int duration;

    @ManyToMany(mappedBy = "missions")
    private List<Employee> employees;


}
