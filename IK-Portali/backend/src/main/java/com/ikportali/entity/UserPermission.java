package com.ikportali.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.Instant;

/** Bir hesaba rolünden bağımsız verilen ek işlem izni. */
@Entity
@Table(name = "user_permission")
public class UserPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_permission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    // ER v3'te mevcut; ilk sürümde kapsamlı yetki davranışı A2'de ETKINLESTIRILMEYECEK.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope_type", length = 30)
    private ScopeType scopeType;

    @Column(name = "scope_value", length = 150)
    private String scopeValue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "granted_by_user_id", nullable = false)
    private User grantedByUser;

    @Column(name = "valid_until")
    private Instant validUntil;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected UserPermission() {}

    public UserPermission(User user, Permission permission, User grantedByUser) {
        this.user = user;
        this.permission = permission;
        this.grantedByUser = grantedByUser;
    }

    @PrePersist
    void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() { updatedAt = Instant.now(); }

    public enum ScopeType { ALL, DEPARTMENT, EMPLOYEE_TYPE }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public Permission getPermission() { return permission; }
    public Department getDepartment() { return department; }
    public ScopeType getScopeType() { return scopeType; }
    public String getScopeValue() { return scopeValue; }
    public User getGrantedByUser() { return grantedByUser; }
    public Instant getValidUntil() { return validUntil; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    public void setDepartment(Department department) { this.department = department; }
    public void setScopeType(ScopeType scopeType) { this.scopeType = scopeType; }
    public void setScopeValue(String scopeValue) { this.scopeValue = scopeValue; }
    public void setValidUntil(Instant validUntil) { this.validUntil = validUntil; }
}
