package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "insurance_eligibility")
@Check(constraints = "(valid_to IS NULL OR valid_from IS NULL OR valid_to >= valid_from)")
@Getter
@Setter
@NoArgsConstructor
public class InsuranceEligibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_eligibility_id", nullable = false)
    private Long insuranceEligibilityId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "insurance_plan_id", nullable = false)
    private InsurancePlan insurancePlan;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "position_id", nullable = true)
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;

    @Column(name = "valid_from", nullable = true)
    private LocalDate validFrom;

    @Column(name = "valid_to", nullable = true)
    private LocalDate validTo;

}
