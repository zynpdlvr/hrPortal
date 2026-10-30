package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.Instant;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "employee_insurance_selection")
@Check(constraints = "(status IN ('SELECTED', 'CANCELLED', 'EXPIRED'))")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeInsuranceSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_insurance_selection_id", nullable = false)
    private Long employeeInsuranceSelectionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "insurance_plan_id", nullable = false)
    private InsurancePlan insurancePlan;

    @Column(name = "selected_at", nullable = false)
    private Instant selectedAt;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "effective_from", nullable = true)
    private LocalDate effectiveFrom;

}
