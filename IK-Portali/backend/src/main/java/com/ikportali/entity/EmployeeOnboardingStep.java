package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "employee_onboarding_step", uniqueConstraints = {@UniqueConstraint(columnNames = {"onboarding_process_id", "onboarding_step_id"})})
@Check(constraints = "(status IN ('PENDING', 'IN_PROGRESS', 'COMPLETED', 'REJECTED'))")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeOnboardingStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_onboarding_step_id", nullable = false)
    private Long employeeOnboardingStepId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "onboarding_process_id", nullable = false)
    private OnboardingProcess onboardingProcess;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "onboarding_step_id", nullable = false)
    private OnboardingStep onboardingStep;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "completed_at", nullable = true)
    private Instant completedAt;

}
