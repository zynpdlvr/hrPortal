package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "email_log")
@Check(constraints = "(delivery_status IN ('QUEUED', 'SENT', 'FAILED')) AND (attempt_count BETWEEN 0 AND 32767)")
@Getter
@Setter
@NoArgsConstructor
public class EmailLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_log_id", nullable = false)
    private Long emailLogId;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "email_template_id", nullable = true)
    private EmailTemplate emailTemplate;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "recipient_user_id", nullable = true)
    private User recipientUser;

    @Column(name = "recipient_email", nullable = false, length = 254)
    private String recipientEmail;

    @Column(name = "delivery_status", nullable = false, length = 20)
    private String deliveryStatus;

    @Column(name = "attempt_count", nullable = false)
    private Short attemptCount;

    @Column(name = "sent_at", nullable = true)
    private Instant sentAt;

}
