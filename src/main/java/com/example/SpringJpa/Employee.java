package com.example.SpringJpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true, name = "ID")
    private Integer id;
    @Column(name = "Identifier", nullable = false, unique = true)
    private String identifier;
    @Column(name = "First_Name", nullable = false)
    private String firstName;
    @Column(name = "Last_Name", nullable = false)
    private String lastName;
    @Column(name = "Email", nullable = false, unique = true)
    private String email;
    @Column(name = "Date_Of_Birth", nullable = false)
    private LocalDate dateOfBirth;
    @Column(name = "Role", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "employee_mission",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "mission_id")
    )
    private List<Mission> missions;
}
