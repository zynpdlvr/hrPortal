package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "insurance_plan")
@Getter
@Setter
@NoArgsConstructor
public class InsurancePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_plan_id", nullable = false)
    private Long insurancePlanId;

    @Column(name = "code", nullable = false, unique = true, length = 40)
    private String code;

    @Column(name = "name", nullable = false, length = 120)
    private String name;

    @Column(name = "provider_name", nullable = true, length = 120)
    private String providerName;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

}
