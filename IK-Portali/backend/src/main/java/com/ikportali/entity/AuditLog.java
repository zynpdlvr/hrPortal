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
@Table(name = "audit_log")
@Check(constraints = "(action IN ('CREATE', 'UPDATE', 'DELETE', 'APPROVE', 'REJECT'))")
@Getter
@Setter
@NoArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_log_id", nullable = false)
    private Long auditLogId;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "actor_user_id", nullable = true)
    private User actorUser;

    @Column(name = "action", nullable = false, length = 30)
    private String action;

    @Column(name = "entity_name", nullable = false, length = 80)
    private String entityName;

    @Column(name = "record_pk", nullable = false, length = 64)
    private String recordPk;

    @Column(name = "occurred_at", nullable = false)
    private Instant occurredAt;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "change_summary", nullable = true, columnDefinition = "jsonb")
    private Map<String, Object> changeSummary;

}
