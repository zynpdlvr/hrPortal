package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "employee")
@Check(constraints = "(employment_status IN ('PREHIRE', 'ACTIVE', 'LEAVE', 'TERMINATED'))")
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_id", nullable = true, unique = true)
    private User user;

    @Column(name = "employee_number", nullable = true, unique = true, length = 32)
    private String employeeNumber;

    @Column(name = "temporary_reference", nullable = true, unique = true, length = 32)
    private String temporaryReference;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "position_id", nullable = true)
    private Position position;

    @Column(name = "employment_status", nullable = false, length = 20)
    private String employmentStatus;

    @Column(name = "start_date", nullable = true)
    private LocalDate startDate;

}
