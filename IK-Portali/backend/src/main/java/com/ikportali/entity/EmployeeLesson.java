package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "employee_lesson")
@Check(constraints = "(status IN ('ASSIGNED', 'SCHEDULED', 'COMPLETED', 'CANCELLED'))")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_lesson_id", nullable = false)
    private Long employeeLessonId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "training_session_id", nullable = true)
    private TrainingSession trainingSession;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "assigned_at", nullable = false)
    private Instant assignedAt;

    @Column(name = "completed_at", nullable = true)
    private Instant completedAt;

}
