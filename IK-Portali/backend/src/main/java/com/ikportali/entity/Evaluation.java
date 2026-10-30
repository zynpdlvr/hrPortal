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
@Table(name = "evaluation")
@Check(constraints = "(type IN ('MENTOR_TO_MENTEE', 'MENTEE_TO_MENTOR')) AND (status IN ('DRAFT', 'SUBMITTED'))")
@Getter
@Setter
@NoArgsConstructor
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id", nullable = false)
    private Long evaluationId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mentorship_id", nullable = false)
    private Mentorship mentorship;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_employee_id", nullable = false)
    private Employee authorEmployee;

    @Column(name = "type", nullable = false, length = 30)
    private String type;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "answers_payload", nullable = true, columnDefinition = "jsonb")
    private Map<String, Object> answersPayload;

    @Column(name = "submitted_at", nullable = true)
    private Instant submittedAt;

}
