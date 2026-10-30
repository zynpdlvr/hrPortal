package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "employee_document_requirement")
@Check(constraints = "(status IN ('REQUESTED', 'PENDING_REVIEW', 'APPROVED', 'REJECTED'))")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeDocumentRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_requirement_id", nullable = false)
    private Long documentRequirementId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "onboarding_process_id", nullable = true)
    private OnboardingProcess onboardingProcess;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "due_date", nullable = true)
    private LocalDate dueDate;

}
