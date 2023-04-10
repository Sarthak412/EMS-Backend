package com.sarthak.Employee_Management_System.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

}
