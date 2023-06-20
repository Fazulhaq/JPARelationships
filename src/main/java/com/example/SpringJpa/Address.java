package com.example.SpringJpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue
    private Integer id;
    private String streetName;
    private String houseNumber;
    private String zipCode;

    //@OneToOne
    //@JoinColumn(name = "employee_id")
    //private Employee employee;
}
