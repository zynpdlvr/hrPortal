package com.ikportali.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "lesson")
@Check(constraints = "(scope IN ('COMPANY', 'DEPARTMENT')) AND (scope <> 'DEPARTMENT' OR department_id IS NOT NULL) AND (duration_minutes BETWEEN 1 AND 1440)")
@Getter
@Setter
@NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id", nullable = false)
    private Long lessonId;

    @Column(name = "title", nullable = false, length = 160)
    private String title;

    @Column(name = "scope", nullable = false, length = 20)
    private String scope;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;

    @Column(name = "is_mandatory", nullable = false)
    private Boolean isMandatory;

    @Column(name = "duration_minutes", nullable = true)
    private Short durationMinutes;

}
