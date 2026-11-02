
package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "app_user")
@Check(constraints =
        "(account_status IN ('PENDING', 'ACTIVE', 'LOCKED', 'DISABLED'))")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "public_id", nullable = false,
            unique = true, updatable = false)
    private UUID publicId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "login_email", nullable = false,
            unique = true, length = 254)
    private String loginEmail;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "must_change_password", nullable = false)
    private Boolean mustChangePassword;

    @Column(name = "account_status", nullable = false, length = 20)
    private String accountStatus;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @PrePersist
    public void beforeInsert() {
        if (publicId == null) {
            publicId = UUID.randomUUID();
        }

        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }
}
