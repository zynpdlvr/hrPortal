package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;
import java.util.Map;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "entry_form")
@Check(constraints = "(status IN ('DRAFT', 'SUBMITTED', 'APPROVED', 'RETURNED'))")
@Getter
@Setter
@NoArgsConstructor
public class EntryForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_form_id", nullable = false)
    private Long entryFormId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "onboarding_process_id", nullable = false, unique = true)
    private OnboardingProcess onboardingProcess;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "form_data", nullable = true, columnDefinition = "jsonb")
    private Map<String, Object> formData;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "submitted_at", nullable = true)
    private Instant submittedAt;

}
