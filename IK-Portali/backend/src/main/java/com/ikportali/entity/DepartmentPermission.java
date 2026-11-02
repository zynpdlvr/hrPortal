package com.ikportali.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/** Departmandaki ortak veya yalnızca yöneticiye açık işlem izni. */
@Entity
@Table(name = "department_permission")
public class DepartmentPermission {

    @EmbeddedId
    private Id id = new Id();

    @MapsId("departmentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @MapsId("permissionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    @Column(name = "manager_only", nullable = false)
    private boolean managerOnly = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected DepartmentPermission() {}

    public DepartmentPermission(Department department, Permission permission, boolean managerOnly) {
        this.department = Objects.requireNonNull(department);
        this.permission = Objects.requireNonNull(permission);
        this.managerOnly = managerOnly;
    }

    @PrePersist
    void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() { updatedAt = Instant.now(); }

    public Id getId() { return id; }
    public Department getDepartment() { return department; }
    public Permission getPermission() { return permission; }
    public boolean isManagerOnly() { return managerOnly; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setManagerOnly(boolean managerOnly) { this.managerOnly = managerOnly; }

    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "department_id")
        private Long departmentId;

        @Column(name = "permission_id")
        private Long permissionId;

        public Id() {}
        public Id(Long departmentId, Long permissionId) {
            this.departmentId = departmentId;
            this.permissionId = permissionId;
        }
        public Long getDepartmentId() { return departmentId; }
        public Long getPermissionId() { return permissionId; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Id that)) return false;
            return Objects.equals(departmentId, that.departmentId)
                    && Objects.equals(permissionId, that.permissionId);
        }
        @Override
        public int hashCode() { return Objects.hash(departmentId, permissionId); }
    }
}
