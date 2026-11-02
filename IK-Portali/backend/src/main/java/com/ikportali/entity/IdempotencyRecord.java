package com.ikportali.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.Instant;

/** Tekrar edilen POST isteğini güvenli biçimde tek bir işleme bağlama kaydı. */
@Entity
@Table(name = "idempotency_record", indexes = {
        @Index(name = "idx_idempotency_expiry", columnList = "expires_at")
})
public class IdempotencyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "request_key", nullable = false, unique = true, length = 128)
    private String requestKey;

    // Asagidaki alanlar ER v3'te yoktur; gercek idempotency davranisi icin onerilen ilavelerdir.
    @Column(name = "http_method", nullable = false, length = 10)
    private String httpMethod;

    @Column(name = "request_path", nullable = false, length = 500)
    private String requestPath;

    @Column(name = "request_hash", nullable = false, length = 64)
    private String requestHash;

    @Column(name = "response_status")
    private Integer responseStatus;

    @Column(name = "response_body", columnDefinition = "text")
    private String responseBody;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false, length = 20)
    private State state = State.PROCESSING;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    protected IdempotencyRecord() {}

    public IdempotencyRecord(User user, String requestKey, String httpMethod,
                             String requestPath, String requestHash, Instant expiresAt) {
        this.user = user;
        this.requestKey = requestKey;
        this.httpMethod = httpMethod;
        this.requestPath = requestPath;
        this.requestHash = requestHash;
        this.expiresAt = expiresAt;
    }

    @PrePersist
    void onCreate() { createdAt = Instant.now(); }

    public enum State { PROCESSING, COMPLETED, FAILED }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public String getRequestKey() { return requestKey; }
    public String getHttpMethod() { return httpMethod; }
    public String getRequestPath() { return requestPath; }
    public String getRequestHash() { return requestHash; }
    public Integer getResponseStatus() { return responseStatus; }
    public String getResponseBody() { return responseBody; }
    public State getState() { return state; }
    public Instant getExpiresAt() { return expiresAt; }
    public Instant getCreatedAt() { return createdAt; }

    public void complete(int responseStatus, String safeResponseBody) {
        this.responseStatus = responseStatus;
        this.responseBody = safeResponseBody;
        this.state = State.COMPLETED;
    }

    public void markFailed() { this.state = State.FAILED; }
}
