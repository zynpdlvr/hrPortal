package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "task")
@Check(constraints = "(status IN ('OPEN', 'IN_PROGRESS', 'DONE', 'CANCELLED'))")
@Getter
@Setter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Long taskId;

    @Column(name = "title", nullable = false, length = 160)
    private String title;

    @Column(name = "description", nullable = true, columnDefinition = "text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assigned_to_employee_id", nullable = false)
    private Employee assignedToEmployee;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "assigned_by_employee_id", nullable = true)
    private Employee assignedByEmployee;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "due_at", nullable = true)
    private Instant dueAt;

}
