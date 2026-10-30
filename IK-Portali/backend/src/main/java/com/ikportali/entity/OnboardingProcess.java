package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "onboarding_process")
@Check(constraints = "(status IN ('NOT_STARTED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED')) AND (status <> 'COMPLETED' OR completed_at IS NOT NULL)")
@Getter
@Setter
@NoArgsConstructor
public class OnboardingProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "onboarding_process_id", nullable = false)
    private Long onboardingProcessId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "started_at", nullable = true)
    private Instant startedAt;

    @Column(name = "completed_at", nullable = true)
    private Instant completedAt;

}
