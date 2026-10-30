package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "onboarding_step")
@Check(constraints = "(sequence_no BETWEEN 1 AND 32767)")
@Getter
@Setter
@NoArgsConstructor
public class OnboardingStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "onboarding_step_id", nullable = false)
    private Long onboardingStepId;

    @Column(name = "code", nullable = false, unique = true, length = 50)
    private String code;

    @Column(name = "title", nullable = false, length = 120)
    private String title;

    @Column(name = "sequence_no", nullable = false)
    private Short sequenceNo;

    @Column(name = "is_required", nullable = false)
    private Boolean isRequired;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

}
